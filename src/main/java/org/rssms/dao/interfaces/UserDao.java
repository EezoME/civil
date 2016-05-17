package org.rssms.dao.interfaces;

import org.rssms.entity.User;

import java.util.Date;
import java.util.List;

/**
 * Created by User on 12.03.2016.
 */
public interface UserDao extends GenericDao<User> {
    User findByUsername(String username);

    List<User> findAll();

    List<String> findAllEmails();

    List<User> findByRole(String role);

    List<User> findByBDate(Date bDate);

    User findByEmail(String email);
}
