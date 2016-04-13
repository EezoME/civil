package org.rssms.dao;

import org.rssms.dao.interfaces.GenericDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 02.03.2016.
 */
public abstract class AbstractJpaDao<T extends Serializable> implements GenericDao<T> {
    @PersistenceContext(unitName = "civil")
    private EntityManager em;
    private Class<T> entityClass;

    public AbstractJpaDao() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
    }

//    public AbstractJpaDao(Class<T> entityClass) {
//        this.entityClass = entityClass;
//    }

//    public AbstractJpaDao() {
//
//    }

    public EntityManager getEntityManager() {
        return em;
    }

    public Class<T> getEntityClass() {
        return entityClass;
    }

    @Override
    public void persist(T entity) {
        getEntityManager().persist(entity);
    }

    @Override
    public void merge(T entity) {
        getEntityManager().merge(entity);
    }

    @Override
    public void remove(T entity) {
        getEntityManager().remove(entity);
    }

    @Override
    public void remove(Object id) {
        T entity = find(id);
        remove(entity);
    }
    @Override
    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public TypedQuery<T> namedQuery(String queryName) {
        return getEntityManager().createNamedQuery(queryName, entityClass);
    }

    public TypedQuery<T> namedQuery(String queryName, HashMap<String, String> params) {
        TypedQuery<T> query = getEntityManager().createNamedQuery(queryName, entityClass);
        for (Map.Entry<String, String> parameter : params.entrySet()) {
            query.setParameter(parameter.getKey(), parameter.getValue());
        }
        return query;
    }
}
