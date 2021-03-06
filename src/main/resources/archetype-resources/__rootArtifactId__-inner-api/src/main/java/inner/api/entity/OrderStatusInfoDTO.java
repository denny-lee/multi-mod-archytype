#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.inner.api.entity;

import java.io.Serializable;

/**
 * Description:
 * On 2017/9/6 16:50 created by LW
 */
public class OrderStatusInfoDTO implements Serializable {
    private String orderNo;
    private String orderStatus;
    private String riderId;
    private String riderName;
    private String riderPhone;
    private Long timestamp;

    public OrderStatusInfoDTO() {
    }

    public OrderStatusInfoDTO(String orderNo, String orderStatus) {
        this.orderNo = orderNo;
        this.orderStatus = orderStatus;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getRiderId() {
        return riderId;
    }

    public void setRiderId(String riderId) {
        this.riderId = riderId;
    }

    public String getRiderName() {
        return riderName;
    }

    public void setRiderName(String riderName) {
        this.riderName = riderName;
    }

    public String getRiderPhone() {
        return riderPhone;
    }

    public void setRiderPhone(String riderPhone) {
        this.riderPhone = riderPhone;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
