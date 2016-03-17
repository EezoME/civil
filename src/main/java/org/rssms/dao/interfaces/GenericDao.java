package org.rssms.dao.interfaces;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by User on 12.03.2016.
 */
public interface GenericDao <T extends Serializable> {

    void persist(T entity);

    void merge(T entity);

    void remove(T entity);

    void remove(Object id);

    T find(Object id);
}
