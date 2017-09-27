#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.dao;

import ${package}.Parameter;
import ${package}.ReflectUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

public class BaseDao<T> {
    @Autowired
    private SessionFactory sessionFactory;

    private Class<?> entityClass;

    public BaseDao() {
        this.entityClass = ReflectUtils.getClassGenricType(getClass());
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public T get(Serializable id) throws Exception {
        Session session = getSession();
        return (T) session.get(entityClass, id);
    }

    public void save(T t) throws Exception {
        Session session = getSession();
        session.save(t);
    }

    public void saveOrUpdate(T t) throws Exception {
        Session session = getSession();
        session.saveOrUpdate(t);
    }

    public void delete(Long id) throws Exception {
        Session session = getSession();
        Object obj = session.get(entityClass, id);
        session.delete(obj);
    }

    public Query createQuery(String qlString, Parameter parameter){
        Query query = getSession().createQuery(qlString);
        setParameter(query, parameter);
        return query;
    }

    public CriteriaBuilder getCriteriaBuilder() {
        Session session = getSession();
        return session.getCriteriaBuilder();
    }

    public Query<T> createQuery(CriteriaQuery<T> criteria) {
        Session session = getSession();
        return session.createQuery(criteria);
    }

    private void setParameter(Query query, Parameter parameter){
        if (parameter != null) {
            Set<String> keySet = parameter.keySet();
            for (String string : keySet) {
                Object value = parameter.get(string);
                //这里考虑传入的参数是什么类型，不同类型使用的方法不同
                if(value instanceof Collection<?>){
                    query.setParameterList(string, (Collection<?>)value);
                }else if(value instanceof Object[]){
                    query.setParameterList(string, (Object[])value);
                }else{
                    query.setParameter(string, value);
                }
            }
        }
    }
}
