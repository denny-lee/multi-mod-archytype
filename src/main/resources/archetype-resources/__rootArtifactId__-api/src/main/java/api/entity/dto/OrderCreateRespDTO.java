#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.api.entity.dto;

/**
 * Description: 订单创建响应
 * On 2017/9/11 17:08 created by LW
 */
public class OrderCreateRespDTO {
    private String orderNo;
    private Double deliverDistance;
    private Double deliveryFee;
    private OverFeeInfoDTO overFeeInfo;

    public OrderCreateRespDTO() {
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Double getDeliverDistance() {
        return deliverDistance;
    }

    public void setDeliverDistance(Double deliverDistance) {
        this.deliverDistance = deliverDistance;
    }

    public Double getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(Double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public OverFeeInfoDTO getOverFeeInfo() {
        return overFeeInfo;
    }

    public void setOverFeeInfo(OverFeeInfoDTO overFeeInfo) {
        this.overFeeInfo = overFeeInfo;
    }
}
