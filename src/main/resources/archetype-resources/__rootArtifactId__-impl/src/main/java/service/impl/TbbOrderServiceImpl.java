#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.impl;

import com.alibaba.fastjson.JSONObject;
import ${package}.entity.TbbOrderStatus;
import ${package}.inner.api.TbbOrderServiceInterface;
import ${package}.inner.api.entity.OrderStatusInfoDTO;
import ${package}.service.MsgService;
import ${package}.service.TbbOrderStatusService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;

/**
 * Description:
 * On 2017/9/5 9:26 created by LW
 */
public class TbbOrderServiceImpl implements TbbOrderServiceInterface {

    private static final Logger logger = LoggerFactory.getLogger(TbbOrderServiceImpl.class);

    @Autowired
    private MsgService msgService;
    @Autowired
    private TbbOrderStatusService orderStatusService;

    @Override
    public boolean statusChange(String userId, OrderStatusInfoDTO statusInfo) {
        logger.info("-----------dubbo statusChange-----");
        if (StringUtils.isBlank(userId)) {
            logger.debug("statusChange, userId is empty.");
            return false;
        }
        if (null == statusInfo) {
            logger.debug("statusChange, statusInfo is null.");
            return false;
        }
        Long timestamp = Calendar.getInstance().getTimeInMillis();
        statusInfo.setTimestamp(timestamp);
        logger.info("params: userId: {}, statusInfo: {}", userId, JSONObject.toJSONString(statusInfo));
        TbbOrderStatus orderStatus = new TbbOrderStatus();
        orderStatus.setOrderNo(statusInfo.getOrderNo());
        orderStatus.setPushed(false);
        orderStatus.setRetryTimes(0);
        orderStatus.setStatus(statusInfo.getOrderStatus());
        orderStatus.setRiderId(statusInfo.getRiderId());
        orderStatus.setRiderName(statusInfo.getRiderName());
        orderStatus.setRiderPhone(statusInfo.getRiderPhone());
        orderStatus.setTimestamp(timestamp);
        orderStatus.setUserId(userId);
        try {
            orderStatusService.save(orderStatus);
        } catch (Exception e) {
            logger.error("statusChange save error.");
        }
        String dest = "/queue/" + userId + "/orderStatus";
        logger.debug("invoke msgService send message to: {}", dest);
        return msgService.send(dest, statusInfo, null);
    }
}
