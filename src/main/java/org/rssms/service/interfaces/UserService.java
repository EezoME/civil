package org.rssms.service.interfaces;

import org.rssms.entity.User;
import org.rssms.exception.InvalidUserException;
import org.rssms.exception.UserNotFoundException;

/**
 * Created by WRKSPACE2 on 3/12/2016.
 */
public interface UserService {

    void addUser(User user) throws InvalidUserException;

    void removeUser(int userId);

    void updateUser(User user) throws InvalidUserException;

    User findUserById(int id) throws UserNotFoundException;

    User findUser(String username) throws UserNotFoundException;

    void verifyUser(String username, String verificationCode);
}
