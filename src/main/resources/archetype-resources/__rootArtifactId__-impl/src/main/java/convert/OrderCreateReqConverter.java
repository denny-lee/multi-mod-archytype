#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.convert;

import ${package}.api.entity.dto.OrderCreateReqDTO;
import ${groupId}.tubobo.merchant.api.dto.AddressInfoDTO;
import ${groupId}.tubobo.merchant.api.dto.MerchantOrderCreateDto;
import ${groupId}.tubobo.merchant.api.dto.MerchantOrderCreateResultDto;

/**
 * Description:
 * On 2017/9/12 16:51 created by LW
 */
public class OrderCreateReqConverter implements Converter<MerchantOrderCreateDto, OrderCreateReqDTO> {

    @Override
    public void convert(MerchantOrderCreateDto to, OrderCreateReqDTO from) {
        if (null == from) {
            return;
        }
        to.setUserId(from.getUserId());
        to.setOrderRemarks(from.getOrderRemarks());
        to.setUserAppointTime(from.getUserAppointTime());
        AddressInfoDTO consignor = new AddressInfoDTO();
        consignor.setAddressDetail(from.getConsignor().getAddressDetail());
        consignor.setAddressCity(from.getConsignor().getAddressCity());
        consignor.setAddressDistrict(from.getConsignor().getAddressDistrict());
        consignor.setAddressProvince(from.getConsignor().getAddressProvince());
        consignor.setLatitude(from.getConsignor().getLatitude());
        consignor.setLongitude(from.getConsignor().getLongitude());
        consignor.setTelephone(from.getConsignor().getTelephone());
        consignor.setName(from.getConsignor().getName());
        to.setConsignor(consignor);
        AddressInfoDTO receiver = new AddressInfoDTO();
        receiver.setAddressCity(from.getReceiver().getAddressCity());
        receiver.setAddressDetail(from.getReceiver().getAddressDetail());
        receiver.setAddressDistrict(from.getReceiver().getAddressDistrict());
        receiver.setAddressProvince(from.getReceiver().getAddressProvince());
        receiver.setLatitude(from.getReceiver().getLatitude());
        receiver.setLongitude(from.getReceiver().getLongitude());
        receiver.setTelephone(from.getReceiver().getTelephone());
        receiver.setName(from.getReceiver().getName());
        to.setReceiver(receiver);



    }
}
