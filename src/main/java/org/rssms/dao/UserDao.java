package org.rssms.dao;

import org.rssms.entity.User;

import java.util.List;

/**
 * Created by User on 01.03.2016.
 */
public class UserDao extends AbstractJpaDao<User> {

    public UserDao(Class<User> entityClass) {
        super(entityClass);
    }

    public List<User> getAll() {
        return namedQuery("User.getAll").getResultList();
    }
}
