#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.api.entity.dto;

/**
 * Description: 订单详情响应
 * On 2017/9/12 15:08 created by LW
 */
public class OrderDetailRespDTO {
    private AddressInfoDTO consignor;
    private AddressInfoDTO receiver;
    private PayInfoDTO payInfo;
    private OverFeeInfoDTO overFeeInfo;
    private OrderInfoDTO orderInfo;
    private DriverInfoDTO driverInfo;


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

    public PayInfoDTO getPayInfo() {
        return payInfo;
    }

    public void setPayInfo(PayInfoDTO payInfo) {
        this.payInfo = payInfo;
    }

    public OverFeeInfoDTO getOverFeeInfo() {
        return overFeeInfo;
    }

    public void setOverFeeInfo(OverFeeInfoDTO overFeeInfo) {
        this.overFeeInfo = overFeeInfo;
    }

    public OrderInfoDTO getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfoDTO orderInfo) {
        this.orderInfo = orderInfo;
    }

    public DriverInfoDTO getDriverInfo() {
        return driverInfo;
    }

    public void setDriverInfo(DriverInfoDTO driverInfo) {
        this.driverInfo = driverInfo;
    }
}
