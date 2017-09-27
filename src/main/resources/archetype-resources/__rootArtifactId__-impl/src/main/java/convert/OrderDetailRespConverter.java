#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.convert;



import ${package}.api.entity.dto.*;
import ${groupId}.tubobo.merchant.api.condition.OrderInfo;
import ${groupId}.tubobo.merchant.api.condition.OverFeeInfo;
import ${groupId}.tubobo.merchant.api.dto.MerchantOrderDetailDTO;

public class OrderDetailRespConverter implements Converter<OrderDetailRespDTO, MerchantOrderDetailDTO>{

    @Override
    public void convert(OrderDetailRespDTO to, MerchantOrderDetailDTO from) {
        if (null == from) {
            return;
        }
        if (null != from.getConsignor()) {
            AddressInfoDTO consignor=new AddressInfoDTO();
            consignor.setAddressCity(from.getConsignor().getAddressCity());
            consignor.setAddressDetail(from.getConsignor().getAddressDetail());
            consignor.setAddressDistrict(from.getConsignor().getAddressDistrict());
            consignor.setAddressProvince(from.getConsignor().getAddressProvince());
            consignor.setLatitude(from.getConsignor().getLatitude());
            consignor.setLongitude(from.getConsignor().getLongitude());
            consignor.setName(from.getConsignor().getName());
            consignor.setTelephone(from.getConsignor().getTelephone());
            to.setConsignor(consignor);
        }

        if (null != from.getReceiver()) {
            AddressInfoDTO receiver=new AddressInfoDTO();
            receiver.setAddressCity(from.getReceiver().getAddressCity());
            receiver.setAddressDetail(from.getReceiver().getAddressDetail());
            receiver.setAddressDistrict(from.getReceiver().getAddressDistrict());
            receiver.setAddressProvince(from.getReceiver().getAddressProvince());
            receiver.setLatitude(from.getReceiver().getLatitude());
            receiver.setLongitude(from.getReceiver().getLongitude());
            receiver.setName(from.getReceiver().getName());
            receiver.setTelephone(from.getReceiver().getTelephone());
            to.setReceiver(receiver);
        }


        if (null != from.getPayInfoDto()) {
            PayInfoDTO payInfoDTO=new PayInfoDTO();
            payInfoDTO.setDeliveryFee(from.getPayInfoDto().getDeliveryFee());
            payInfoDTO.setPayAmount(from.getPayInfoDto().getPayAmount());
            payInfoDTO.setPayStatus(from.getPayInfoDto().getPayStatus());
            payInfoDTO.setTipFee(from.getPayInfoDto().getTipFee());
            to.setPayInfo(payInfoDTO);
        }

        if (null != from.getOverFeeInfo()) {
            OverFeeInfoDTO overFeeInfoDTO=new OverFeeInfoDTO();
            overFeeInfoDTO.setWeatherOverFee(from.getOverFeeInfo().getWeatherOverFee());
            overFeeInfoDTO.setTotalOverFee(from.getOverFeeInfo().getTotalOverFee());
            overFeeInfoDTO.setPeekOverFee(from.getOverFeeInfo().getPeekOverFee());
            to.setOverFeeInfo(overFeeInfoDTO);
        }

        if (null != from.getOrderInfo()) {
            OrderInfoDTO orderInfoDTO=new OrderInfoDTO();
            orderInfoDTO.setCancelReason(from.getOrderInfo().getCancelReason());
            orderInfoDTO.setCancelTime(from.getOrderInfo().getCancelTime());
            orderInfoDTO.setDeliveryDistance(from.getOrderInfo().getCancelReason());
            orderInfoDTO.setExpectFinishTime(from.getOrderInfo().getExpectFinishTime());
            orderInfoDTO.setExpiredMinute(from.getOrderInfo().getExpiredMinute());
            orderInfoDTO.setFinishOrderTime(from.getOrderInfo().getFinishOrderTime());
            orderInfoDTO.setGrabItemTime(from.getOrderInfo().getGrabItemTime());
            orderInfoDTO.setGrabOrderTime(from.getOrderInfo().getGrabOrderTime());
            orderInfoDTO.setOrderNo(from.getOrderInfo().getOrderNo());
            orderInfoDTO.setOrderRemarks(from.getOrderInfo().getOrderRemarks());
            orderInfoDTO.setOrderStatus(from.getOrderInfo().getOrderStatus());
            orderInfoDTO.setOrderTime(from.getOrderInfo().getOrderTime());
            to.setOrderInfo(orderInfoDTO);
        }

        if (null != from.getRiderInfo()) {
            DriverInfoDTO driverInfoDTO=new DriverInfoDTO();
            driverInfoDTO.setName(from.getRiderInfo().getName());
            driverInfoDTO.setTelephone(from.getRiderInfo().getTelephone());
            to.setDriverInfo(driverInfoDTO);
        }

    }
}
