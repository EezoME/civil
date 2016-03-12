package org.rssms.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.rssms.dao.UserDao;
import org.rssms.entity.User;
import org.rssms.exception.InvalidUserException;
import org.rssms.exception.UserNotFoundException;
import org.rssms.service.interfaces.UserService;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

/**
 * Created by WRKSPACE2 on 3/12/2016.
 */

@Stateless
public class UserServiceBean implements UserService {

    @Resource
    Validator validator;
    private UserDao userDao;

    public UserServiceBean(UserDao userDao) {
        this.userDao = userDao;
    }

    public void addUser(User user) throws InvalidUserException {
        Set<ConstraintViolation<User>> violationSet =  validator.validate(user);
        for (ConstraintViolation<User> violation : violationSet) {
            throw new InvalidUserException(violation.getPropertyPath() + " " + violation.getMessage());
        }

        String passwordHash = DigestUtils.md5Hex(user.getPassword());
        user.setPassword(passwordHash);
        userDao.persist(user);
    }

    public void removeUser(int userId) {
        userDao.remove(userId);
    }

    public void updateUser(User user) throws InvalidUserException {
        Set<ConstraintViolation<User>> violationSet = validator.validate(user);
        for (ConstraintViolation<User> violation : violationSet) {
            throw new InvalidUserException(violation.getPropertyPath() + " " + violation.getMessage());
        }
        userDao.persist(user);
    }

    public User findUserById(int id) throws UserNotFoundException {
        User user = userDao.find(id);
        if (user == null) {
            throw new UserNotFoundException("User with ID: " + id + " was not found!");
        }
        return user;
    }

    public User findUser(String username) throws UserNotFoundException {

        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("User with username: " + username + " was not found!");
        }
        return user;
    }

    public void verifyUser(String username, String verificationCode) {
        // TODO
    }
}
