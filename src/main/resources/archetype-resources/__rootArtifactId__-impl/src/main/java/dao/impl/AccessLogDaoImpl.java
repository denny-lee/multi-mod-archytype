#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.dao.impl;

import ${package}.Parameter;
import ${package}.dao.AccessLogDao;
import ${package}.dao.BaseDao;
import ${package}.entity.AccessLog;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccessLogDaoImpl extends BaseDao<AccessLog> implements AccessLogDao {
    @Override
    public List<AccessLog> list() throws Exception {
        Query<AccessLog> query = createQuery("from AccessLog", new Parameter());
        return query.list();
    }

    @Override
    public AccessLog get(Long id) throws Exception {
        return super.get(id);
    }
}
