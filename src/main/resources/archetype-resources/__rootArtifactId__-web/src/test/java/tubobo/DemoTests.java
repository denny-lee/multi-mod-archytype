#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.tubobo;

import com.alibaba.fastjson.JSONObject;
import ${package}.api.entity.dto.AddressInfoDTO;
import ${package}.api.entity.dto.OrderCreateReqDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Description:
 * On 2017/9/5 15:03 created by LW
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration("/spring-context.xml")
public class DemoTests {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void saveLog() throws Exception {
        this.mockMvc.perform(post("/save").param("systemKey", "aha").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("${symbol_dollar}.resultCode").value("0"));
    }

    @Test
    public void createOrder() throws Exception {
        OrderCreateReqDTO orderToCreate = new OrderCreateReqDTO();
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
        String json = JSONObject.toJSONString(orderToCreate);
        this.mockMvc.perform(post("/dak/order/create")
                .param("userId", "I'm user.")
                .param("requestData", json).accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("${symbol_dollar}.resultCode").value("0"));
    }

}
