package org.rssms.dao;

import org.rssms.dao.interfaces.GenericDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.Serializable;

/**
 * Created by User on 02.03.2016.
 */
public abstract class AbstractJpaDao<T extends Serializable> implements GenericDao<T> {
    @PersistenceContext(unitName = "civil")
    private EntityManager em;
    private Class<T> entityClass;

    public AbstractJpaDao() {
    }

    public AbstractJpaDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
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

    @Override
    public TypedQuery<T> namedQuery(String queryName) {
        return getEntityManager().createNamedQuery(queryName, entityClass);
    }
}
