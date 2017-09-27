#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service;

import ${package}.entity.AccessLog;

import java.util.List;

public interface AccessLogService {
    Long save(AccessLog accessLog) throws Exception;

    AccessLog get(Long id) throws Exception;

    void del(Long id) throws Exception;

    List<AccessLog> list() throws Exception;

    List<AccessLog> listBy(String ip, String userId);
}
