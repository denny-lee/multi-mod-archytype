#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.api.entity.dto;

import java.util.Date;

/**
 * Description: 订单创建请求
 * On 2017/9/11 17:00 created by LW
 */
public class OrderCreateReqDTO {
    private String userId;
    private Date userAppointTime;
    private String orderRemarks;
    private AddressInfoDTO consignor;
    private AddressInfoDTO receiver;

    public OrderCreateReqDTO() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getUserAppointTime() {
        return userAppointTime;
    }

    public void setUserAppointTime(Date userAppointTime) {
        this.userAppointTime = userAppointTime;
    }

    public String getOrderRemarks() {
        return orderRemarks;
    }

    public void setOrderRemarks(String orderRemarks) {
        this.orderRemarks = orderRemarks;
    }

    public AddressInfoDTO getConsignor() {
        return consignor;
    }

    public void setConsignor(AddressInfoDTO consignor) {
        this.consignor = consignor;
    }

    public AddressInfoDTO getReceiver() {
        return receiver;
    }

    public void setReceiver(AddressInfoDTO receiver) {
        this.receiver = receiver;
    }
}
