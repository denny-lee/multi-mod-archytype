#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import ${package}.api.entity.RespWrapper;
import ${package}.api.entity.dto.*;
import ${package}.api.enums.EnumRespCode;
import ${package}.cache.TokenCache;
import ${package}.convert.Converter;
import ${package}.convert.OrderCreateReqConverter;
import ${package}.convert.OrderCreateRespConverter;
import ${package}.convert.OrderDetailRespConverter;
import ${package}.util.Validator;
import ${package}.web.entity.CommonReq;
import ${groupId}.tubobo.merchant.api.MerchantToThirdPartyServiceInterface;
import ${groupId}.tubobo.merchant.api.TbbMerchantResponse;
import ${groupId}.tubobo.merchant.api.dto.MerchantAbortConfirmDTO;
import ${groupId}.tubobo.merchant.api.dto.MerchantOrderCreateDto;
import ${groupId}.tubobo.merchant.api.dto.MerchantOrderCreateResultDto;
import ${groupId}.tubobo.merchant.api.dto.MerchantOrderDetailDTO;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Description:
 * On 2017/9/5 16:21 created by LW
 */
@Controller
@RequestMapping("/dak/order")
public class TbbOrderController {

    private static final Logger logger = LoggerFactory.getLogger(TbbOrderController.class);

    @Autowired
    private MerchantToThirdPartyServiceInterface merchantToThirdPartyService;
//    @Autowired
//    private OrderService orderService;

    /**
     * 订单创建
     * @param req
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public RespWrapper<OrderCreateRespDTO> createOrder(@RequestBody CommonReq req) {
        String userId = req.getUserId();
        String token = req.getToken();
        String requestData = req.getRequestData();
        RespWrapper<OrderCreateRespDTO> resp = new RespWrapper<OrderCreateRespDTO>(EnumRespCode.FAIL.getValue(), EnumRespCode.FAIL.getDesc());
        logger.debug("createOrder , userId: {}, requestData: {}", userId, requestData);
        if (!Validator.commonValidate(userId, requestData, resp)) {
            return resp;
        }
        // 验证是否重复提交
        if (validateRepeatRequest(token, requestData)) {
            logger.info("createOrder, repeat submit userId: {}, requestData: {}", userId, requestData);
            resp.setResultCode(EnumRespCode.REPREAT_REQUEST.getValue());
            resp.setResultDesc(EnumRespCode.REPREAT_REQUEST.getDesc());
            return resp;
        }
        OrderCreateReqDTO order = null;
        try {
            order = JSONObject.parseObject(requestData, OrderCreateReqDTO.class);
        } catch (Exception e) {
            logger.error("createOrder, json parse error. userId" + userId, e);
            return resp;
        }
        if (!Validator.createOrderValidate(order, resp)) {
            return resp;
        }
        order.setUserId(userId);
        Converter sendConverter = new OrderCreateReqConverter();
        MerchantOrderCreateDto dto = new MerchantOrderCreateDto();
        sendConverter.convert(dto, order);
        logger.debug("createOrder, req convert to:{}", JSONObject.toJSONString(dto));

        TbbMerchantResponse<MerchantOrderCreateResultDto> resultDto = null;
        OrderCreateRespDTO resultData = null;
        try {
            resultDto = merchantToThirdPartyService.createOrder(dto);
            logger.debug("createOrder, resp :{}", JSONObject.toJSONString(resultDto));
            if (null == resultDto || !resultDto.isSucceeded()) {
                if (StringUtils.isNotBlank(resultDto.getMessage())) {
                    resp.setResultDesc(resultDto.getMessage());
                }
                return resp;
            }
            Converter converter = new OrderCreateRespConverter();
            resultData = new OrderCreateRespDTO();
            converter.convert(resultData, resultDto.getData());
            resp.setResultCode(EnumRespCode.SUCCESS.getValue());
            resp.setResultDesc(EnumRespCode.SUCCESS.getDesc());
            resp.setResultData(resultData);
            logger.debug("createOrder, resp convert to:{}", JSONObject.toJSONString(resultData));
        } catch (Exception e) {
            logger.error("createOrder error. userId: " + userId, e);
        }
        return resp;
    }

    /**
     * 批量订单创建
     * @param req
     * @return
     */
    @RequestMapping(value = "/batch-create", method = RequestMethod.POST)
    @ResponseBody
    public RespWrapper<JSONArray> batchCreate(@RequestBody CommonReq req) {
        String userId = req.getUserId();
        String token = req.getToken();
        String requestData = req.getRequestData();
        logger.debug("batchCreate, userId: {}, requestData: {}", userId, requestData);
        int successCount = 0;
        RespWrapper<JSONArray> resp = new RespWrapper<JSONArray>(EnumRespCode.FAIL.getValue(),EnumRespCode.FAIL.getDesc());
        if (!Validator.commonValidate(userId, requestData, resp)) {
            return resp;
        }
        // 验证是否重复提交
        if (validateRepeatRequest(token, requestData)) {
            logger.info("batchCreate, repeat submit userId: {}, requestData: {}", userId, requestData);
            resp.setResultCode(EnumRespCode.REPREAT_REQUEST.getValue());
            resp.setResultDesc(EnumRespCode.REPREAT_REQUEST.getDesc());
            return resp;
        }
        JSONArray array = null;
        try {
            array = JSONObject.parseArray(requestData);
        } catch (Exception e) {
            logger.error("batchCreate, json parse error. userId" + userId, e);
        }
        if (null == array || array.isEmpty()) {
            resp.setResultCode(EnumRespCode.PARAMS_ERROR.getValue());
            resp.setResultDesc(EnumRespCode.PARAMS_ERROR.getDesc());
            return resp;
        }

        int size = array.size();
        if (size > 10) {
            logger.info("batchCreate, exceed 10 orders. reject. userId: {}", userId);
            resp.setResultCode(EnumRespCode.PARAMS_ERROR.getValue());
            resp.setResultDesc("批量创建订单每次应不超过10单");
            return resp;
        }

        JSONArray jsonArray = new JSONArray();

        RespWrapper<OrderCreateRespDTO> errorMsg = null;

        Converter sendConverter = null;
        MerchantOrderCreateDto dto = null;
        OrderCreateReqDTO order = null;
        for (int i = 0; i < size; i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("index", i);
            JSONObject eachObj = array.getJSONObject(i);
            try {
                order = JSONObject.parseObject(eachObj.toJSONString(), OrderCreateReqDTO.class);
            } catch (Exception e) {
                logger.error("batchCreate, json parse error. userId: " + userId, e);
                jsonObject.put("resultCode", EnumRespCode.FAIL.getValue());
                jsonObject.put("resultDesc", EnumRespCode.FAIL.getDesc());
                jsonArray.add(jsonObject);
                continue;
            }
            errorMsg = new RespWrapper<OrderCreateRespDTO>(EnumRespCode.FAIL.getValue(),EnumRespCode.FAIL.getDesc());
            if (!Validator.createOrderValidate(order, errorMsg)) {
                jsonObject.put("resultCode", EnumRespCode.FAIL.getValue());
                jsonObject.put("resultDesc", errorMsg.getResultDesc());
                jsonArray.add(jsonObject);
                continue;
            }
            order.setUserId(userId);
            sendConverter = new OrderCreateReqConverter();
            dto = new MerchantOrderCreateDto();
            sendConverter.convert(dto, order);
            logger.debug("batchCreate, userId: {}, req convert to: {}", userId, JSONObject.toJSONString(dto));
            TbbMerchantResponse<MerchantOrderCreateResultDto> resultDto = null;
            OrderCreateRespDTO resultData = null;
            try {
                resultDto = merchantToThirdPartyService.createOrder(dto);
                logger.debug("batchCreate, userId: {}, resp: {}", userId, JSONObject.toJSONString(resultDto));
                if (null == resultDto || !resultDto.isSucceeded()) {
                    jsonObject.put("resultCode", EnumRespCode.FAIL.getValue());
                    if (StringUtils.isNotBlank(resultDto.getMessage())) {
                        jsonObject.put("resultDesc", resultDto.getMessage());
                    } else {
                        jsonObject.put("resultDesc", EnumRespCode.FAIL.getDesc());
                    }
                    jsonArray.add(jsonObject);
                    continue;
                }
                Converter converter = new OrderCreateRespConverter();
                resultData = new OrderCreateRespDTO();
                converter.convert(resultData, resultDto.getData());
                logger.debug("batchCreate, userId: {}, resp convert to: {}", userId, JSONObject.toJSONString(resultData));
                jsonObject.put("resultCode", EnumRespCode.SUCCESS.getValue());
                jsonObject.put("resultDesc", EnumRespCode.SUCCESS.getDesc());
                jsonObject.put("resultData", resultData);
                jsonArray.add(jsonObject);
                successCount++;
            } catch (Exception e) {
                logger.error("batchCreate, exception , userId: " + userId + " index " + i, e);
                jsonObject.put("resultCode", EnumRespCode.FAIL.getValue());
                jsonObject.put("resultDesc", EnumRespCode.FAIL.getDesc());
                jsonArray.add(jsonObject);
            }
        }

        if (successCount == size) {
            resp.setResultCode(EnumRespCode.SUCCESS.getValue());
            resp.setResultDesc(EnumRespCode.SUCCESS.getDesc());
        } else if (successCount != 0) {
            resp.setResultCode(EnumRespCode.SUCCESS.getValue());
            resp.setResultDesc("部分创建成功");
        } else {
            resp.setResultCode(EnumRespCode.FAIL.getValue());
            resp.setResultDesc(EnumRespCode.FAIL.getDesc());
        }
        resp.setResultData(jsonArray);
        logger.debug("batchCreate, userId: {}, final resp: {}", userId, jsonArray.toJSONString());
        return resp;
    }

    /**
     * 订单列表查询
     * @param userId
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public RespWrapper<OrderListRespDTO> list(@RequestParam("userId") String userId,
                                                       @RequestParam(value = "requestData", required = false) String requestData) {
        logger.info(JSONObject.toJSONString(requestData));

        // TODO limitation of pageNo,pageSize
        // TODO invoke dubbo
        return new RespWrapper<OrderListRespDTO>(EnumRespCode.UNSUPORTTED.getValue(), EnumRespCode.UNSUPORTTED.getDesc());
    }

    /**
     * 订单详细
     * @param userId
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody
    public RespWrapper<OrderDetailRespDTO> detail(@RequestParam("userId") String userId,
                                                 @RequestParam("requestData") String requestData) {
        logger.debug("detail, userId: {}, requestData: {}", userId, requestData);
        RespWrapper<OrderDetailRespDTO> resp = new RespWrapper<OrderDetailRespDTO>(EnumRespCode.FAIL.getValue(),EnumRespCode.FAIL.getDesc());
        if (!Validator.commonValidate(userId, requestData, resp)) {
            return resp;
        }
        OrderDetailReqDTO order = null;
        try {
            order = JSONObject.parseObject(requestData, OrderDetailReqDTO.class);
        } catch (Exception e) {
            logger.error("detail, json parse error. userId: " + userId, e);
            return resp;
        }
        if (!Validator.detailOrderValidate(order, resp)) {
            return resp;
        }
        String orderNo = order.getOrderNo();
        MerchantOrderDetailDTO resultDto = null;
        OrderDetailRespDTO resultData = null;
        try {
            resultDto = merchantToThirdPartyService.orderDetail(orderNo,userId);
            Converter converter = new OrderDetailRespConverter();
            resultData = new OrderDetailRespDTO();
            converter.convert(resultData, resultDto);
            resp.setResultCode(EnumRespCode.SUCCESS.getValue());
            resp.setResultDesc(EnumRespCode.SUCCESS.getDesc());
            resp.setResultData(resultData);
            logger.debug("detail, userId: {}, resp: {}", userId, JSONObject.toJSONString(resultData));
        } catch (Exception e) {
            logger.error("etail, error. userId: " + userId, e);
        }
        return resp;
    }

    /**
     * 订单取消
     * @param userId
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/cancel", method = RequestMethod.GET)
    @ResponseBody
    public RespWrapper<Object> cancel(@RequestParam("userId") String userId,
                                      @RequestParam(name = "token", required = false) String token,
                                      @RequestParam("requestData") String requestData) {
        logger.debug("cancel, userId: {}, requestData: {}", userId, requestData);
        RespWrapper<Object> resp =new RespWrapper<Object>(EnumRespCode.FAIL.getValue(),EnumRespCode.FAIL.getDesc());
        if (!Validator.commonValidate(userId, requestData, resp)) {
            return resp;
        }
        // 验证是否重复提交
        if (validateRepeatRequest(token, requestData)) {
            resp.setResultCode(EnumRespCode.REPREAT_REQUEST.getValue());
            resp.setResultDesc(EnumRespCode.REPREAT_REQUEST.getDesc());
            return resp;
        }
        OrderCancelReqDTO order = null;
        try {
            order = JSONObject.parseObject(requestData, OrderCancelReqDTO.class);
        } catch (Exception e) {
            logger.error("cancel, json parse error. userId: " + userId, e);
            return resp;
        }
        if (!Validator.cancelOrderValidate(order, resp)) {
            return resp;
        }
        String orderNo=order.getOrderNo();
        TbbMerchantResponse<Boolean> resultDto = null;
        try{
            resultDto = merchantToThirdPartyService.orderCancel(orderNo,userId);
            if (null == resultDto || !resultDto.isSucceeded()) {
                resp.setResultCode(EnumRespCode.FAIL.getValue());
                resp.setResultDesc(EnumRespCode.FAIL.getDesc());
                if (StringUtils.isNotBlank(resultDto.getMessage())) {
                    resp.setResultDesc(resultDto.getMessage());
                }
            } else {
//                logger.info("cancel, fireStatusChange . userId:{}", userId);
                // 状态改变由商家端发起
//                orderService.fireStatusChange(userId, orderNo, EnumMerchantOrderStatus.CANCEL);
                resp.setResultCode(EnumRespCode.SUCCESS.getValue());
                resp.setResultDesc(EnumRespCode.SUCCESS.getDesc());
            }
        } catch (Exception e){
            logger.error("cancel, error. userId: " + userId, e);
            resp.setResultCode(EnumRespCode.FAIL.getValue());
            resp.setResultDesc(EnumRespCode.FAIL.getDesc());
        }
        return resp;
    }

    /**
     * 未妥投确认
     * @param req
     * @return
     */
    @RequestMapping(value = "/abort-confirm", method = RequestMethod.POST)
    @ResponseBody
    public RespWrapper<Object> abortConfirm(@RequestBody CommonReq req) {
        String userId = req.getUserId();
        String token = req.getToken();
        String requestData = req.getRequestData();
        logger.debug("abortConfirm, userId: {}, requestData: {}", userId, requestData);
        RespWrapper<Object> resp = new RespWrapper<Object>(EnumRespCode.FAIL.getValue(),EnumRespCode.FAIL.getDesc());
        if (!Validator.commonValidate(userId, requestData, resp)) {
            return resp;
        }
        // 验证是否重复提交
        if (validateRepeatRequest(token, requestData)) {
            resp.setResultCode(EnumRespCode.REPREAT_REQUEST.getValue());
            resp.setResultDesc(EnumRespCode.REPREAT_REQUEST.getDesc());
            return resp;
        }
        AbortConfirmReqDTO order = null;
        try {
            order = JSONObject.parseObject(requestData, AbortConfirmReqDTO.class);
        } catch (Exception e) {
            logger.error("abortConfirm, json parse error.userId: " + userId, e);
            return resp;
        }

        if (!Validator.abortConfirmOrderValidate(order, resp)) {
            return resp;
        }

        MerchantAbortConfirmDTO dto = new MerchantAbortConfirmDTO();
        dto.setUserId(userId);
        dto.setOrderNo(order.getOrderNo());
        dto.setConfirm(order.getConfirm());
        dto.setMessage(order.getMessage());
        logger.debug("abortConfirm, .userId: {}, req convert to: {}", userId, JSONObject.toJSONString(dto));
        TbbMerchantResponse<Boolean> tbbResp = null;

        try {
            tbbResp = merchantToThirdPartyService.abortConfirm(dto);
            logger.debug("abortConfirm, .userId: {}, req convert to: {}", userId, tbbResp);
            if (null == tbbResp || !tbbResp.isSucceeded()) {
                resp.setResultCode(EnumRespCode.FAIL.getValue());
                resp.setResultDesc(EnumRespCode.FAIL.getDesc());
                if (StringUtils.isNotBlank(tbbResp.getMessage())) {
                    resp.setResultDesc(tbbResp.getMessage());
                }
            } else {
                Boolean realSuccess = tbbResp.getData();
                if (null == realSuccess || !realSuccess.booleanValue()) {
                    resp.setResultCode(EnumRespCode.FAIL.getValue());
                    resp.setResultDesc(EnumRespCode.FAIL.getDesc());
                } else {
                    // 状态改变由商家端发起
//                    orderService.fireStatusChange(userId, order.getOrderNo(), EnumMerchantOrderStatus.FINISH);
                    resp.setResultCode(EnumRespCode.SUCCESS.getValue());
                    resp.setResultDesc(EnumRespCode.SUCCESS.getDesc());
                }
            }
        } catch (Exception e) {
            logger.error("abortConfirm, error. userId: " + userId, e);
            resp.setResultCode(EnumRespCode.FAIL.getValue());
            resp.setResultDesc(EnumRespCode.FAIL.getDesc());
        }
        return resp;
    }

    private boolean validateRepeatRequest(String token, String requestData) {
        // 验证是否重复提交
        if (StringUtils.isNotBlank(token)) {
            TokenCache tokenCache = TokenCache.getInstance();
            String value = tokenCache.get(token);
            if (null != value && value.equals(requestData)) {
                //时间戳相同，具提交数据相同，认定为重复提交
                return true;
            }
            tokenCache.put(token, requestData); // 放入缓存
        }
        return false;
    }
}
