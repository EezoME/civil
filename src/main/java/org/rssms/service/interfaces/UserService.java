package org.rssms.service.interfaces;

import org.rssms.entity.User;
import org.rssms.exception.EmailConfirmationNotFoundException;
import org.rssms.exception.InvalidUserException;
import org.rssms.exception.UserNotFoundException;

import java.util.List;

/**
 * Created by WRKSPACE2 on 3/12/2016.
 */
public interface UserService {

    List<User> findAllUsers();

    void addUser(User user) throws InvalidUserException;

    void removeUser(int userId);

    void updateUser(User user) throws InvalidUserException;

    User findUser(int id) throws UserNotFoundException;

    User findUser(String username) throws UserNotFoundException;

    User findUserByEmail(String email) throws UserNotFoundException;

    void verifyUser(String username, String confirmationCode) throws EmailConfirmationNotFoundException, UserNotFoundException;

    boolean authUser(String username, String password) throws UserNotFoundException;

    List<User> cutListForPage(List<User> users, int page, int recordsPerPage);
}
