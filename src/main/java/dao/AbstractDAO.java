package dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.Serializable;

/**
 * Created by User on 02.03.2016.
 */
public abstract class AbstractDAO<T extends Serializable> {
    private Class<T> entityClass;

    public AbstractDAO() {
    }

    public AbstractDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public Class<T> getEntityClass() {
        return entityClass;
    }

    public void persist(T entity) {
        getEntityManager().persist(entity);
    }

    public void merge(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) { getEntityManager().remove(entity); }

    public void remove(Object id) {
        T entity = find(id);
        remove(entity);
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public TypedQuery<T> namedQuery(String queryName) {
        return getEntityManager().createNamedQuery(queryName, entityClass);
    }
}
