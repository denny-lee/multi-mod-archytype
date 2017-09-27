#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.api.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Description: 订单信息
 * On 2017/9/12 15:21 created by LW
 */
public class OrderInfoDTO {
    private String deliveryDistance;
    private String orderNo;//订单No
    private String orderType;//订单类型
    private String orderStatus;//订单状态
    private String orderRemarks;
    private String cancelReason;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date orderTime;//下单时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date cancelTime;//取消时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date closeTime;//关闭时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date expiredTime;//超时时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date grabOrderTime;//接单时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date grabItemTime;//取货时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date finishOrderTime;//送达时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date expectFinishTime;//预计送达时间
    private Double expiredMinute;//超时时间(min)


    public String getDeliveryDistance() {
        return deliveryDistance;
    }

    public void setDeliveryDistance(String deliveryDistance) {
        this.deliveryDistance = deliveryDistance;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderRemarks() {
        return orderRemarks;
    }

    public void setOrderRemarks(String orderRemarks) {
        this.orderRemarks = orderRemarks;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public Date getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(Date expiredTime) {
        this.expiredTime = expiredTime;
    }

    public Date getGrabOrderTime() {
        return grabOrderTime;
    }

    public void setGrabOrderTime(Date grabOrderTime) {
        this.grabOrderTime = grabOrderTime;
    }

    public Date getGrabItemTime() {
        return grabItemTime;
    }

    public void setGrabItemTime(Date grabItemTime) {
        this.grabItemTime = grabItemTime;
    }

    public Date getFinishOrderTime() {
        return finishOrderTime;
    }

    public void setFinishOrderTime(Date finishOrderTime) {
        this.finishOrderTime = finishOrderTime;
    }

    public Date getExpectFinishTime() {
        return expectFinishTime;
    }

    public void setExpectFinishTime(Date expectFinishTime) {
        this.expectFinishTime = expectFinishTime;
    }

    public Double getExpiredMinute() {
        return expiredMinute;
    }

    public void setExpiredMinute(Double expiredMinute) {
        this.expiredMinute = expiredMinute;
    }
}
