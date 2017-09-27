#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller;

import com.alibaba.fastjson.JSONObject;
import ${package}.api.entity.RespWrapper;
import ${package}.web.entity.ClientResp;
import ${package}.util.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description:
 * On 2017/9/6 10:29 created by LW
 */
@Controller
@RequestMapping("/dak/setting")
public class SettingController {
    private static final Logger logger = LoggerFactory.getLogger(SettingController.class);

    /**
     * 设置websocket通道
     * @param userId
     * @return
     */
    @RequestMapping(value = "/ws/edit", method = RequestMethod.GET)
    @ResponseBody
    public RespWrapper<Object> editWsKey(@RequestParam("userId") String userId,
                                           @RequestParam("requestData") String requestData) {
        RespWrapper<Object> resp = null;
        if (!Validator.commonValidate(userId, requestData, resp)) {
            return resp;
        }


        //TODO invoke dubbo
        logger.info("------------");
        logger.info("------------");
        return new RespWrapper<Object>(null);
    }

    @RequestMapping(value = "/ws/detail", method = RequestMethod.GET)
    @ResponseBody
    public RespWrapper<Object> queryWsKey(@RequestParam("userId") String userId) {
        RespWrapper<Object> resp = null;

        //TODO invoke dubbo
        logger.info("------------");
        logger.info("------------");
        String orderNo = "";
        return new RespWrapper<Object>(null);
    }
}
