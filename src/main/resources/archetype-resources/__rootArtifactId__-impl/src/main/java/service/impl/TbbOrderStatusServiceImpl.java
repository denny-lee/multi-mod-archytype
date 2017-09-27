#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.impl;

import ${package}.dao.TbbOrderStatusDao;
import ${package}.entity.TbbOrderStatus;
import ${package}.service.TbbOrderStatusService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Description:
 * On 2017/9/15 11:05 created by LW
 */
@Service
@Transactional
public class TbbOrderStatusServiceImpl implements TbbOrderStatusService {
    @Autowired
    private TbbOrderStatusDao orderStatusDao;

    @Override
    public void save(TbbOrderStatus orderStatus) throws Exception {
        if (null != orderStatus) {
            orderStatusDao.save(orderStatus);
        }
    }

    @Override
    public void update(TbbOrderStatus orderStatus) throws Exception {
        if (null != orderStatus) {
            orderStatusDao.saveOrUpdate(orderStatus);
        }
    }

    @Override
    public TbbOrderStatus get(Long id) throws Exception {
        if (null != id) {
            return orderStatusDao.get(id);
        }
        return null;
    }

    @Override
    public void delete(Long id) throws Exception {
        if (null != id) {
            orderStatusDao.delete(id);
        }
    }

    @Override
    public List<TbbOrderStatus> list() throws Exception {
        return orderStatusDao.list();
    }

    @Override
    public List<TbbOrderStatus> listUnPushed(String userId, Integer retryTimes) throws Exception {
        if (null == userId) {
            return null;
        }
        return orderStatusDao.listUnPushed(userId, retryTimes);
    }

    @Override
    public List<TbbOrderStatus> listUnPushedByOrderNo(String userId, String orderNo, Long timestamp) throws Exception {
        if (StringUtils.isBlank(userId) || StringUtils.isBlank(orderNo)) {
            return null;
        }
        return orderStatusDao.listUnPushedByOrderNo(userId, orderNo, timestamp);
    }
}
