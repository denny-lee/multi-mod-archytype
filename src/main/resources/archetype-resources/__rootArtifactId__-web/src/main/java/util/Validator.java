#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.util;

import ${package}.api.entity.RespWrapper;
import ${package}.api.entity.dto.*;
import ${package}.web.entity.ClientResp;
import ${package}.web.entity.EnumRespCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

/**
 * Description:
 * On 2017/9/5 17:09 created by LW
 */
public class Validator {
    public static <T> boolean commonValidate(String userId, String requestData, RespWrapper<T> resp) {
        Assert.notNull(resp, "resp should not null");
        if (StringUtils.isBlank(userId)) {
            resp.setResultCode(EnumRespCode.USERID_NULL.getValue());
            resp.setResultDesc(EnumRespCode.USERID_NULL.getDesc());
            return false;
        }
        if (StringUtils.isBlank(requestData)) {
            resp.setResultCode(EnumRespCode.PARAMS_ERROR.getValue());
            resp.setResultDesc(EnumRespCode.PARAMS_ERROR.getDesc());
            return false;
        }
        return true;
    }
    public static <T> boolean commonValidate(String userId, ClientResp<T> resp) {
        Assert.notNull(resp, "resp should not null");
        if (StringUtils.isBlank(userId)) {
            resp.setResultCode(EnumRespCode.USERID_NULL.getValue());
            resp.setResultDesc(EnumRespCode.USERID_NULL.getDesc());
            return false;
        }
        return true;
    }


    public static boolean createOrderValidate(OrderCreateReqDTO order, RespWrapper<OrderCreateRespDTO> resp) {
        Assert.notNull(resp, "resp should not null");
        if (null == order) {
            resp.setResultCode(EnumRespCode.PARAMS_ERROR.getValue());
            resp.setResultDesc(EnumRespCode.PARAMS_ERROR.getDesc());
            return false;
        }
        if (null == order.getConsignor() || StringUtils.isBlank(order.getConsignor().getAddressDetail())
                || StringUtils.isBlank(order.getConsignor().getTelephone())
                || order.getConsignor().getLatitude() == null
                || order.getConsignor().getLongitude() == null) {
            resp.setResultCode(EnumRespCode.PARAMS_ERROR.getValue());
            resp.setResultDesc("参数错误：发货人详细地址，经纬及电话为必填");
            return false;
        }
        if (null == order.getReceiver() || StringUtils.isBlank(order.getReceiver().getAddressDetail())
                || StringUtils.isBlank(order.getReceiver().getTelephone())
                || order.getReceiver().getLatitude() == null
                || order.getReceiver().getLongitude() == null) {
            resp.setResultCode(EnumRespCode.PARAMS_ERROR.getValue());
            resp.setResultDesc("参数错误：收货人详细地址，经纬及电话为必填");
            return false;
        }
        return true;
    }
    public static boolean detailOrderValidate(OrderDetailReqDTO order, RespWrapper<OrderDetailRespDTO> resp) {
        Assert.notNull(resp, "resp should not null");
        if ( null == order||StringUtils.isBlank(order.getOrderNo())) {
            resp.setResultCode(EnumRespCode.PARAMS_ERROR.getValue());
            resp.setResultDesc("参数错误：订单编号为必填");
            return false;
        }

        return true;
    }
    public static boolean cancelOrderValidate(OrderCancelReqDTO order, RespWrapper<Object> resp) {
        Assert.notNull(resp, "resp should not null");
        if (null == order||StringUtils.isBlank(order.getOrderNo())) {
            resp.setResultCode(EnumRespCode.PARAMS_ERROR.getValue());
            resp.setResultDesc("参数错误：订单编号为必填");
            return false;
        }
        return true;
    }
    public static boolean abortConfirmOrderValidate(AbortConfirmReqDTO order, RespWrapper<Object> resp) {
        Assert.notNull(resp, "resp should not null");
        if (null==order||order.getConfirm()==null||StringUtils.isBlank(order.getOrderNo())) {
            resp.setResultCode(EnumRespCode.PARAMS_ERROR.getValue());
            resp.setResultDesc("参数错误：订单编号、是否确认为必填");
            return false;
        }
        return true;
    }
}
