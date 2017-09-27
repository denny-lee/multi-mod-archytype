#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller;

import ${package}.common.config.Config;
import ${package}.entity.TbbOrderStatus;
import ${package}.inner.api.entity.OrderStatusInfoDTO;
import ${package}.service.MsgService;
import ${package}.service.TbbOrderStatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
public class WsController {

    private static final Logger logger = LoggerFactory.getLogger(WsController.class);

    private static final Executor executor = Executors.newFixedThreadPool(2); // 2个线程的池
    private static final Integer RETRY_TIME = 1;

    @Autowired
    private TbbOrderStatusService orderStatusService;

    @Autowired
    private MsgService msgService;
    @Autowired
    private Config config;

    /**
     * 心跳,回声
     * @param userId
     * @param ping
     * @return
     * @throws Exception
     */
    @MessageMapping("/heartbeat/{userId}")
    @SendTo("/queue/heartbeat/{userId}")
    public String heartbeat(@DestinationVariable("userId") final String userId, String ping) throws Exception {
        logger.info("usreId: {} --------------------- i'm alive. {}", userId, ping);
        executor.execute(new Runnable() {
            @Override
            public void run() {
                retryPush(userId);
            }
        });
        return "pong";
    }

    /**
     * 状态推送回执
     * @param userId
     * @param orderStatus
     * @throws Exception
     */
    @MessageMapping("/echo/{userId}")
    public void statusEcho(@DestinationVariable("userId") final String userId, final OrderStatusInfoDTO orderStatus) throws Exception {
        logger.info("statusEcho, userId: {}------------", userId);
        if (null == orderStatus) {
            logger.error("orderStatus is null!!!");
            return;
        }
        logger.info("statusEcho  orderNO: {} , orderStatue: {} , timestamp: {} ------------", orderStatus.getOrderNo(), orderStatus.getOrderStatus(), orderStatus.getTimestamp());
        executor.execute(new Runnable() {
            @Override
            public void run() {
                updatePushedStatus(userId, orderStatus);
            }
        });
    }

    /**
     * 收到回执后，该订单该状态以前的都置为已推送
     * @param userId
     * @param orderStatus
     */
    private void updatePushedStatus(String userId, OrderStatusInfoDTO orderStatus) {
        logger.info("updatePushedStatus userId: {}, orderNo: {}, timestamp: {}, status: {}", userId, orderStatus.getOrderNo(), orderStatus.getTimestamp(), orderStatus.getOrderStatus());
        try {
            List<TbbOrderStatus> list = orderStatusService.listUnPushedByOrderNo(userId, orderStatus.getOrderNo(), orderStatus.getTimestamp());
            for (TbbOrderStatus status : list) {
                if (null == status) {
                    continue;
                }
                status.setPushed(true); //已推送
                orderStatusService.update(status);
            }
            logger.debug("---------updatePushedStatus success.");
        } catch (Exception e) {
            logger.error("update pushed status error.", e);
        }
    }

    private void retryPush(String userId) {
        List<TbbOrderStatus> list = null;
        int retryTimes = config.getRetryTimes() > 0 ? config.getRetryTimes() : RETRY_TIME;
        try {
            list = orderStatusService.listUnPushed(userId, retryTimes);
        } catch (Exception e) {
            logger.error("getList error, userId: " + userId, e);
        }
        if (null == list) {
            return;
        }
        logger.debug("retryPush list size: {}, userId: {}", list.size(), userId);
        AtomicInteger retry = null;
        for (TbbOrderStatus status : list) {
            retry = new AtomicInteger(status.getRetryTimes());
            if (null == retry) {
                continue;
            }
            logger.debug("begin retry push.userId:{} . orderstatus : orderNo: {}, orderStatus: {}, timestamp: {}", userId, status.getOrderNo(), status.getStatus(), status.getTimestamp());
            OrderStatusInfoDTO dto = new OrderStatusInfoDTO();
            dto.setOrderNo(status.getOrderNo());
            dto.setOrderStatus(status.getStatus());
            dto.setRiderId(status.getRiderId());
            dto.setRiderName(status.getRiderName());
            dto.setRiderPhone(status.getRiderPhone());
            dto.setTimestamp(status.getTimestamp());
            String dest = "/queue/" + userId + "/orderStatus";
            try {
                msgService.send(dest, dto, null);
                logger.info("retry pushed. userId: {}", userId);
                status.setRetryTimes(retry.incrementAndGet());
                orderStatusService.update(status);
                logger.debug("update retry times success.userId: {}", userId);
            } catch (Exception e) {
                logger.error("send and update retrytimes error. userId: " + userId, e);
            }
        }
    }
}
