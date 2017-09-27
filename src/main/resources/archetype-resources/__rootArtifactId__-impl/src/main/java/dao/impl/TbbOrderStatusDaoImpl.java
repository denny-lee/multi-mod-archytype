#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.dao.impl;

import ${package}.Parameter;
import ${package}.dao.BaseDao;
import ${package}.dao.TbbOrderStatusDao;
import ${package}.entity.AccessLog;
import ${package}.entity.TbbOrderStatus;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Description:
 * On 2017/9/15 11:00 created by LW
 */
@Repository
public class TbbOrderStatusDaoImpl extends BaseDao<TbbOrderStatus> implements TbbOrderStatusDao {
    @Override
    public List<TbbOrderStatus> list() throws Exception {
        Query<TbbOrderStatus> query = createQuery("from TbbOrderStatus", new Parameter());
        return query.list();
    }

    @Override
    public TbbOrderStatus get(Long id) throws Exception {
        return super.get(id);
    }

    @Override
    public List<TbbOrderStatus> listUnPushed(String userId, Integer retryTimes) throws Exception {
        if (null == userId) {
            return null;
        }
        Query<TbbOrderStatus> query = null;
        if (null == retryTimes) {
            query = createQuery("from TbbOrderStatus os " +
                    "where os.pushed = :p1 and os.userId = :p2 order by os.timestamp desc", new Parameter(false, userId));
        } else {
            query = createQuery("from TbbOrderStatus os " +
                    "where os.retryTimes < :p1 and os.pushed = :p2 and os.userId = :p3 order by os.timestamp desc", new Parameter(retryTimes, false, userId));
        }
        return query.list();
    }

    @Override
    public List<TbbOrderStatus> listUnPushedByOrderNo(String userId, String orderNo, Long timestamp) throws Exception {
        if (null == userId || null == orderNo || null == timestamp) {
            return null;
        }
        Query<TbbOrderStatus> query = createQuery("from TbbOrderStatus os " +
                "where os.orderNo = :p1 and os.pushed = :p2 and os.timestamp <= :p3 and os.userId = :p4", new Parameter(orderNo, false, timestamp, userId));
        return query.list();
    }
}
