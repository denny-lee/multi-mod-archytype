#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import com.alibaba.fastjson.JSONObject;
import ${package}.api.entity.dto.AddressInfoDTO;
import ${package}.api.entity.dto.OrderCreateReqDTO;
import ${package}.api.entity.dto.OrderCreateRespDTO;
import ${package}.util.HttpUtil;
import ${package}.web.entity.TwoTuple;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * On 2017/9/6 11:48 created by LW
 */
public class Demo {

    private static final String SUCCESS = "0";

    public static void main(String[] args) throws Exception {
        // POST
        /*OrderCreateReqDTO orderToCreate = new OrderCreateReqDTO();
        AddressInfoDTO congnisor = new AddressInfoDTO();
        congnisor.setAddressDetail("XX路");
        congnisor.setTelephone("13999999999");
        congnisor.setLatitude(2.0001);
        congnisor.setLongitude(3.0004);
        orderToCreate.setConsignor(congnisor);
        AddressInfoDTO receiver = new AddressInfoDTO();
        receiver.setAddressDetail("XXXXX路");
        receiver.setTelephone("13998888999");
        receiver.setLatitude(2.00771);
        receiver.setLongitude(3.704);
        orderToCreate.setReceiver(receiver);

        Map<String, Object> param = new HashMap<>();
        param.put("userId", "your user id");
        param.put("token", Calendar.getInstance().getTimeInMillis());
        param.put("requestData", orderToCreate);
        String json = JSONObject.toJSONString(param);

        TwoTuple<Integer, String> response = HttpUtil.getPostResponse("http://127.0.0.1:8080/dak/order/create", null, json);
        int statusCode = response.getFirst();
        String data = response.getSecond();

        System.out.println(data);
        JSONObject resp = JSONObject.parseObject(data);
        String code = resp.getString("resultCode");
        String desc = resp.getString("resultDesc");

        System.out.println(code);
        System.out.println(desc);
        if (SUCCESS.equals(code)) {
            JSONObject resultData = resp.getJSONObject("resultData");
            OrderCreateRespDTO respDTO = JSONObject.parseObject(resultData.toJSONString(), OrderCreateRespDTO.class);
            System.out.println("订单号为：" + respDTO.getOrderNo());
            System.out.println("配送距离为：" + respDTO.getDeliverDistance());
            System.out.println("配送费为：" + respDTO.getDeliveryFee());
        } else {
            System.out.println("订单创建失败");
        }*/

        // GET

        /*String url = "http://127.0.0.1:8080/dak/order/list?userId=yourUserId&token="+Calendar.getInstance().getTimeInMillis();

        TwoTuple<Integer, String> response = HttpUtil.getGetResponse(url, null);
        int statusCode = response.getFirst();
        String data = response.getSecond();

        System.out.println(data);*/


        // WebSocketClient

    }
}
