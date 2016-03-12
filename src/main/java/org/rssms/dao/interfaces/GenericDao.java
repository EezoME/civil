package org.rssms.dao.interfaces;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by User on 12.03.2016.
 */
public interface GenericDao <T extends Serializable> {
    EntityManager getEntityManager();

    Class<T> getEntityClass();

    void persist(T entity);

    void merge(T entity);

    void remove(T entity);

    void remove(Object id);

    T find(Object id);

    TypedQuery<T> namedQuery(String queryName);

    TypedQuery<T> namedQuery(String queryName, HashMap<String, String> parameters);
}
