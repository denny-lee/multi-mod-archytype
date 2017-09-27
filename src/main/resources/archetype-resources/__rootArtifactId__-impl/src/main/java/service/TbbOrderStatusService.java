#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service;

import ${package}.entity.TbbOrderStatus;

import java.util.List;

public interface TbbOrderStatusService {
    void save(TbbOrderStatus orderStatus) throws Exception;

    void update(TbbOrderStatus orderStatus) throws Exception;

    TbbOrderStatus get(Long id) throws Exception;

    void delete(Long id) throws Exception;

    List<TbbOrderStatus> list() throws Exception;

    /**
     * 查询在重推次数小于retryTimes的所有未推送的
     * @param userId
     * @param retryTimes
     * @return
     * @throws Exception
     */
    List<TbbOrderStatus> listUnPushed(String userId, Integer retryTimes) throws Exception;

    /**
     * 查询该userId,orderNo在timestamp前（包括）状态没推送的列表
     * @param userId
     * @param orderNo
     * @param timestamp
     * @return
     * @throws Exception
     */
    List<TbbOrderStatus> listUnPushedByOrderNo(String userId, String orderNo, Long timestamp) throws Exception;
}
