#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.impl;

import ${package}.dao.TbbOrderStatusDao;
import ${package}.entity.TbbOrderStatus;
import ${package}.entity.TbbOrderStatus_;
import ${package}.service.PushedStatusInfoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Description:
 * On 2017/9/27 10:56 created by LW
 */
@Service
@Transactional
public class PushedStatusInfoServiceImpl implements PushedStatusInfoService {
    @Autowired
    private TbbOrderStatusDao orderStatusDao;

    @Override
    public List<TbbOrderStatus> listBy(String orderNo, String status, Boolean pushed, String userId) {
        CriteriaBuilder builder = orderStatusDao.getCriteriaBuilder();
        CriteriaQuery<TbbOrderStatus> criteria = builder.createQuery(TbbOrderStatus.class);
        Root<TbbOrderStatus> root = criteria.from(TbbOrderStatus.class);
        criteria.select(root);
        Predicate orderNoCondition = null;
        Predicate userCondition = null;
        Predicate statusCondition = null;
        Predicate pushedCondition = null;
        if (StringUtils.isNotBlank(orderNo)) {
            orderNoCondition = builder.equal( root.get(TbbOrderStatus_.orderNo), orderNo);
        } else {
            orderNoCondition = builder.isNotNull(root.get(TbbOrderStatus_.orderNo));
        }
        if (StringUtils.isNotBlank(status)) {
            statusCondition = builder.equal( root.get(TbbOrderStatus_.status), status);
        } else {
            statusCondition = builder.isNotNull(root.get(TbbOrderStatus_.status));
        }
        if (null != pushed) {
            pushedCondition = builder.equal( root.get(TbbOrderStatus_.pushed), pushed);
        } else {
            pushedCondition = builder.isNotNull(root.get(TbbOrderStatus_.pushed));
        }
        if (StringUtils.isNotBlank(userId)) {
            userCondition = builder.equal( root.get(TbbOrderStatus_.userId), userId);
        }
        Predicate allCondition = null;
        if (null != userCondition) {
            criteria.where(builder.and(orderNoCondition, userCondition, statusCondition, pushedCondition));
        } else {
            criteria.where(builder.and(orderNoCondition, statusCondition, pushedCondition));
        }

        return orderStatusDao.createQuery(criteria).getResultList();
    }
}
