#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.api.entity.dto;

import java.util.List;

/**
 * Description: 订单列表查询响应
 * On 2017/9/12 14:54 created by LW
 */
public class OrderListRespDTO {
    private List<OrderListRespDTO> list;

    public List<OrderListRespDTO> getList() {
        return list;
    }

    public void setList(List<OrderListRespDTO> list) {
        this.list = list;
    }
}
