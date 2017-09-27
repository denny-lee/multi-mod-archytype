#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.impl;

import ${package}.entity.TbbOrderStatus;
import ${package}.inner.api.entity.OrderStatusInfoDTO;
import ${package}.service.MsgService;
import ${package}.service.OrderService;
import ${package}.service.TbbOrderStatusService;
import ${groupId}.tubobo.merchant.api.enums.EnumMerchantOrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

/**
 * Description:
 * On 2017/9/14 17:24 created by LW
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private MsgService msgService;
    @Autowired
    private TbbOrderStatusService orderStatusService;

    @Override
    public void fireStatusChange(String userId, String orderNo, EnumMerchantOrderStatus status) throws Exception {
        String dest = "/queue/" + userId + "/orderStatus";
        OrderStatusInfoDTO orderStatusDto = new OrderStatusInfoDTO();
        orderStatusDto.setOrderNo(orderNo);
        orderStatusDto.setOrderStatus(status.getValue());
        orderStatusDto.setTimestamp(Calendar.getInstance().getTimeInMillis());

        TbbOrderStatus orderStatus = new TbbOrderStatus();
        orderStatus.setOrderNo(orderNo);
        orderStatus.setPushed(false);
        orderStatus.setRetryTimes(0);
        orderStatus.setStatus(status.getValue());
        orderStatus.setTimestamp(orderStatusDto.getTimestamp());
        orderStatus.setUserId(userId);
        orderStatusService.save(orderStatus);
        msgService.send(dest, orderStatusDto, null);
    }
}
