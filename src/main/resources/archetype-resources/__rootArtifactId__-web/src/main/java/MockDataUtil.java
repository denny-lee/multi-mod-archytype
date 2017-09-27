#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import ${package}.api.entity.dto.*;

import java.util.Calendar;

/**
 * Description:
 * On 2017/9/15 9:27 created by LW
 */
public class MockDataUtil {

    public static OrderCreateRespDTO mockOrderCreateResp() {
        OrderCreateRespDTO dto = new OrderCreateRespDTO();
        dto.setOrderNo("A00000001");
        dto.setDeliverDistance(1000.0);
        dto.setDeliveryFee(20.0);
        OverFeeInfoDTO overFeeInfo=new OverFeeInfoDTO();
        overFeeInfo.setPeekOverFee(10.0);
        overFeeInfo.setTotalOverFee(20.0);
        overFeeInfo.setWeatherOverFee(10.0);
        dto.setOverFeeInfo(overFeeInfo);
        return dto;
    }

    public static OrderDetailRespDTO mockOrderDetailResp() {
        OrderDetailRespDTO dto = new OrderDetailRespDTO();
        AddressInfoDTO consignor=new AddressInfoDTO();
        consignor.setAddressProvince("浙江省");
        consignor.setAddressCity("杭州");
        consignor.setAddressDistrict("西湖区");
        consignor.setAddressDetail("留和路288号");
        consignor.setLatitude(116.40741300000002);
        consignor.setLongitude(39.904214);
        consignor.setName("黄豆酱");
        consignor.setTelephone("12345678901");
        dto.setConsignor(consignor);
        AddressInfoDTO receiver=new AddressInfoDTO();
        receiver.setAddressProvince("浙江省");
        receiver.setAddressCity("杭州");
        receiver.setAddressDistrict("上城区");
        receiver.setAddressDetail("婺江路217号");
        receiver.setLatitude(116.40741300000002);
        receiver.setLongitude(39.904214);
        receiver.setName("XGHL");
        receiver.setTelephone("98765432109");
        dto.setReceiver(receiver);
        PayInfoDTO payInfoDTO=new PayInfoDTO();
        payInfoDTO.setPayStatus("已支付");
        payInfoDTO.setPayAmount(20.001);
        dto.setPayInfo(payInfoDTO);
        OverFeeInfoDTO overFeeInfoDTO=new OverFeeInfoDTO();
        overFeeInfoDTO.setTotalOverFee(3.401);
        dto.setOverFeeInfo(overFeeInfoDTO);
        OrderInfoDTO orderInfoDTO=new OrderInfoDTO();
        orderInfoDTO.setFinishOrderTime(Calendar.getInstance().getTime());
        orderInfoDTO.setExpiredMinute(2.21214);
        orderInfoDTO.setCancelReason("不是变态辣");
        dto.setOrderInfo(orderInfoDTO);

        DriverInfoDTO driverInfoDTO=new DriverInfoDTO();
        driverInfoDTO.setName("马云");
        dto.setDriverInfo(driverInfoDTO);

        return dto;
    }
    public static boolean mockOrderCancelResp() {

        boolean isCancel=true;
        return isCancel;
    }
    public static boolean mockAbortConfirmResp(){
        boolean isConfirm=false;
        return isConfirm;

    }

//    public static void main(String[] args) {
//        String str=JSONObject.toJSONString(mockOrderDetailResp());
//        System.out.println(str);
//
//    }

    public static void main(String[] args) {
        OrderDetailRespDTO orderDetailRespDTO=mockOrderDetailResp();
//        String str=JSONObject.toJSONString(mockOrderDetailResp());
//        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
//        System.out.println(JSON.toJSONString(orderDetailRespDTO, SerializerFeature.WriteDateUseDateFormat));
        System.out.println(JSON.toJSONStringWithDateFormat(orderDetailRespDTO, "yyyy-MM-dd HH:mm:ss"));
    }


}
