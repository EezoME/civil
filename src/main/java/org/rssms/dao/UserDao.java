package org.rssms.dao;

import org.rssms.dao.interfaces.UserDaoInterface;
import org.rssms.entity.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;

/**
 * Created by User on 01.03.2016.
 */
@Stateless
public class UserDao extends AbstractJpaDao<User> implements UserDaoInterface {

//    public UserDao(Class<User> entityClass) {
//        super(entityClass);
//    }



    public List<User> getAll() {
        return namedQuery("User.getAll").getResultList();
    }

    @Override
    public User findByUsername(String username) {
        return (User) getEntityManager().createQuery("select u from User u where u.username=:username").setParameter("username", username).getSingleResult();
    }

    @Override
    public List<String> findAllEmails() {
        return getEntityManager().createQuery("select u.email from User u").getResultList();
    }

    @Override
    public List<User> findByRole(String role) {
        return getEntityManager().createQuery("select u from User u where u.role=:role").setParameter("role", role).getResultList();
    }

    @Override
    public List<User> findByBDate(Date bDate) {
        return getEntityManager().createQuery("select u from User u where u.bDate=:bDate").setParameter("bDate", bDate).getResultList();
    }

    @Override
    public User findByEmail(String email) {
        return (User) getEntityManager().createQuery("select u from User u where u.email=:email").setParameter("email", email).getSingleResult();
    }
}
