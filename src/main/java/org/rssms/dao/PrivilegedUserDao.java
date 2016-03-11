package org.rssms.dao;

import org.rssms.entity.PrivilegedUser;

import java.util.List;

/**
 * Created by User on 02.03.2016.
 */
public class PrivilegedUserDao extends AbstractJpaDao<PrivilegedUser> {

    public PrivilegedUserDao(Class<PrivilegedUser> entityClass) {
        super(entityClass);
    }

    public List<PrivilegedUser> getAll() {
        return namedQuery("PrivilegedUser.getAll").getResultList();
    }
}

