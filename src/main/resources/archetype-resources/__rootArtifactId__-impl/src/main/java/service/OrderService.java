#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service;

import ${groupId}.tubobo.merchant.api.enums.EnumMerchantOrderStatus;

public interface OrderService {
    void fireStatusChange(String userId, String orderNo, EnumMerchantOrderStatus status) throws Exception;
}
