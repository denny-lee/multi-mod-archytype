#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.dao;

import ${package}.entity.AccessLog;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public interface AccessLogDao {
    void save(AccessLog accessLog) throws Exception;

    AccessLog get(Long id) throws Exception;

    void delete(Long id) throws Exception;

    List<AccessLog> list() throws Exception;

    CriteriaBuilder getCriteriaBuilder();

    Query<AccessLog> createQuery(CriteriaQuery<AccessLog> criteria);
}
