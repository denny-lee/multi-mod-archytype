#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service;

public interface MsgService {
    <T> boolean send(String destination, T t, String user);
}
