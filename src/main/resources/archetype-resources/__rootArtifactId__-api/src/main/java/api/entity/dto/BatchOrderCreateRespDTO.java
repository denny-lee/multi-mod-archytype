#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.api.entity.dto;

import java.util.List;

/**
 * Description: 批量订单创建响应
 * On 2017/9/11 17:11 created by LW
 */
public class BatchOrderCreateRespDTO {
    private List<OrderCreateRespDTO> list;

    public List<OrderCreateRespDTO> getList() {
        return list;
    }

    public void setList(List<OrderCreateRespDTO> list) {
        this.list = list;
    }

    public BatchOrderCreateRespDTO() {

    }
}
