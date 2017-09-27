#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service;

import ${package}.entity.TbbOrderStatus;

import java.util.List;

public interface PushedStatusInfoService {

    List<TbbOrderStatus> listBy(String orderNo, String status, Boolean pushed, String userId);
}
