#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller;

import ${package}.entity.AccessLog;
import ${package}.entity.TbbOrderStatus;
import ${package}.service.AccessLogService;
import ${package}.service.OrderService;
import ${package}.service.PushedStatusInfoService;
import ${package}.web.entity.ClientResp;
import ${package}.web.entity.EnumRespCode;
import ${groupId}.tubobo.merchant.api.enums.EnumMerchantOrderStatus;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.Null;
import java.util.List;


@Controller
@RequestMapping("/")
public class DemoController {
    @Autowired
    private PushedStatusInfoService pushedStatusInfoService;
    @Autowired
    private AccessLogService accessLogService;
    @Autowired
    private OrderService orderService;

//    @Resource(name = "myHandler")
//    private MyHandler myHandler;

    @RequestMapping(value = "/secretdoor/accesslog", method = RequestMethod.GET)
    public String searchAccess(@RequestParam(required = false) String userId, @RequestParam(required = false) String ip,
                             Model model) {
        model.addAttribute("userId", userId);
        model.addAttribute("ip", ip);
        List<AccessLog> list = accessLogService.listBy(ip, userId);
        if (null != list) {
            model.addAttribute("logs", list);
        }
        return "accessLog";
    }

    @RequestMapping(value = "/secretdoor/statusinfo", method = RequestMethod.GET)
    public String searchStatus(@RequestParam(required = false) Boolean pushed, @RequestParam(required = false) String orderNo,
                               @RequestParam(required = false) String status, @RequestParam(required = false) String userId,
                             Model model) {
        model.addAttribute("pushed", pushed);
        model.addAttribute("orderNo", orderNo);
        model.addAttribute("status", status);
        model.addAttribute("userId", userId);
        List<TbbOrderStatus> list = pushedStatusInfoService.listBy(orderNo, status, pushed, userId);
        if (null != list) {
            model.addAttribute("statusList", list);
        }
        return "statusInfo";
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String hello(Model model) {
        return "welcome";
    }

    @RequestMapping(value = "/demo", method = RequestMethod.GET)
    public String demo(Model model) {
        return "demo";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ClientResp saveLog(AccessLog accessLog) throws Exception {
        ClientResp<Object> resp = null;
        if (null == accessLog || accessLog.getSystemKey() == null) {
            resp = new ClientResp<Object>(EnumRespCode.FAIL.getValue(), EnumRespCode.FAIL.getDesc());
            return resp;
        }
        try {
            accessLogService.save(accessLog);
            resp = new ClientResp<Object>(null);
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ClientResp<Object>(EnumRespCode.FAIL.getValue(), EnumRespCode.FAIL.getDesc());
        }
        return resp;
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public ClientResp list() throws Exception {
        ClientResp<List> resp = null;
        try {
            List<AccessLog> list = accessLogService.list();
            resp = new ClientResp<List>(list);
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ClientResp<List>(EnumRespCode.FAIL.getValue(), EnumRespCode.FAIL.getDesc());
        }
        return resp;
    }

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public ClientResp del(Long id) throws Exception {
        ClientResp<Object> resp = null;
        if (null == id) {
            resp = new ClientResp<Object>(EnumRespCode.FAIL.getValue(), EnumRespCode.FAIL.getDesc());
            resp.setResultDesc("id不能为空");
            return resp;
        }
        try {
            accessLogService.del(id);
            resp = new ClientResp<Object>(null);
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ClientResp<Object>(EnumRespCode.FAIL.getValue(), EnumRespCode.FAIL.getDesc());
        }
        return resp;
    }
    @RequestMapping(value = "/sendMsg", method = RequestMethod.GET)
    @ResponseBody
    public ClientResp sendMsg(String user) throws Exception {
        ClientResp<Object> resp = null;
        try {
            orderService.fireStatusChange(user, "AAA0001111", EnumMerchantOrderStatus.CANCEL);
//            accessLogService.sendMsg(user);
            resp = new ClientResp<Object>(null);
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ClientResp<Object>(EnumRespCode.FAIL.getValue(), EnumRespCode.FAIL.getDesc());
        }
        return resp;
    }

}
