#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.dao;

import ${package}.entity.TbbOrderStatus;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public interface TbbOrderStatusDao {
    void save(TbbOrderStatus orderStatus) throws Exception;

    void saveOrUpdate(TbbOrderStatus orderStatus) throws Exception;

    TbbOrderStatus get(Long id) throws Exception;

    void delete(Long id) throws Exception;

    List<TbbOrderStatus> list() throws Exception;

    List<TbbOrderStatus> listUnPushed(String userId, Integer retryTimes) throws Exception;

    List<TbbOrderStatus> listUnPushedByOrderNo(String userId, String orderNo, Long timestamp) throws Exception;

    CriteriaBuilder getCriteriaBuilder();

    Query<TbbOrderStatus> createQuery(CriteriaQuery<TbbOrderStatus> criteria);
}
