#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.convert;

import ${package}.api.entity.dto.OrderCreateReqDTO;
import ${package}.api.entity.dto.OrderCreateRespDTO;
import ${package}.api.entity.dto.OverFeeInfoDTO;
import ${groupId}.tubobo.merchant.api.dto.AddressInfoDTO;
import ${groupId}.tubobo.merchant.api.dto.MerchantOrderCreateDto;
import ${groupId}.tubobo.merchant.api.dto.MerchantOrderCreateResultDto;

/**
 * Description:
 * On 2017/9/12 16:51 created by LW
 */
public class OrderCreateRespConverter implements Converter<OrderCreateRespDTO, MerchantOrderCreateResultDto> {

    @Override
    public void convert(OrderCreateRespDTO to, MerchantOrderCreateResultDto from) {
        if (null == from) {
            return;
        }
        to.setOrderNo(from.getOrderNo());
        to.setDeliverDistance(from.getDeliverDistance());
        to.setDeliveryFee(from.getDeliveryFee());
        if (null != from.getOverFeeInfo()) {
            OverFeeInfoDTO overFeeInfo=new OverFeeInfoDTO();
            overFeeInfo.setPeekOverFee(from.getOverFeeInfo().getPeekOverFee());
            overFeeInfo.setTotalOverFee(from.getOverFeeInfo().getTotalOverFee());
            overFeeInfo.setWeatherOverFee(from.getOverFeeInfo().getWeatherOverFee());
            to.setOverFeeInfo(overFeeInfo);
        }
    }
}
