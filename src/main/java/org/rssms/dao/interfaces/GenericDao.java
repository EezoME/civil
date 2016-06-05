package org.rssms.dao.interfaces;

import java.io.Serializable;

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
