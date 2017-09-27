#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.impl;

import ${package}.dao.AccessLogDao;
import ${package}.entity.AccessLog;
import ${package}.entity.AccessLog_;
import ${package}.service.AccessLogService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;


@Service
@Transactional
public class AccessLogServiceImpl implements AccessLogService {
    @Autowired
    private AccessLogDao accessLogDao;

    @Override
    public Long save(AccessLog gf) throws Exception {
        accessLogDao.save(gf);
        return gf.getId();
    }

    @Override
    public void del(Long id) throws Exception {
        accessLogDao.delete(id);
    }

    @Override
    public List<AccessLog> list() throws Exception {
        return accessLogDao.list();
    }

    @Override
    public AccessLog get(Long id) throws Exception {
        return accessLogDao.get(id);
    }

    @Override
    public List<AccessLog> listBy(String ip, String userId) {
        CriteriaBuilder builder = accessLogDao.getCriteriaBuilder();
        CriteriaQuery<AccessLog> criteria = builder.createQuery(AccessLog.class);
        Root<AccessLog> root = criteria.from(AccessLog.class);
        criteria.select(root);
        Predicate ipCondition = null;
        Predicate userCondition = null;
        if (StringUtils.isNotBlank(ip)) {
            ipCondition = builder.equal( root.get(AccessLog_.ip), ip);
        }
        if (StringUtils.isNotBlank(userId)) {
            ipCondition = builder.equal( root.get(AccessLog_.userId), userId);
        }
        Predicate allCondition = null;
        if (null != ipCondition && null != userCondition) {
            criteria.where(builder.and(ipCondition, userCondition));
        } else if (null != ipCondition) {
            criteria.where(ipCondition);
        } else if (null != userCondition) {
            criteria.where(userCondition);
        }

        return accessLogDao.createQuery(criteria).getResultList();
    }
}
