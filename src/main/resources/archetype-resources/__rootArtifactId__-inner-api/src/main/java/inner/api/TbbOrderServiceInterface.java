#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.inner.api;


import ${package}.inner.api.entity.OrderStatusInfoDTO;

import java.io.Serializable;

public interface TbbOrderServiceInterface {

    /**
     * 订单状态改变
     * @param userId
     * @param statusInfo
     * @return
     */
    boolean statusChange(String userId, OrderStatusInfoDTO statusInfo);
}
