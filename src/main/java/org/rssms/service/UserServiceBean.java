package org.rssms.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.rssms.dao.EmailConfirmationDao;
import org.rssms.dao.UserDao;
import org.rssms.entity.EmailConfirmation;
import org.rssms.entity.User;
import org.rssms.enums.Role;
import org.rssms.exception.EmailConfirmationNotFoundException;
import org.rssms.exception.InvalidUserException;
import org.rssms.exception.UserNotFoundException;
import org.rssms.service.interfaces.UserService;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;
import java.util.Date;
import java.util.Random;

/**
 * Created by WRKSPACE2 on 3/12/2016.
 */

@Stateless
public class UserServiceBean extends AbstractService<User> implements UserService {

    // Is this correct way of getting validator?
    // http://www.thejavageek.com/2014/05/27/jpa-bean-validation/
    @Resource
    Validator validator;
    private UserDao userDao;
    private EmailConfirmationDao emailConfirmationDao;
    private MailService mailService;

    @EJB
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @EJB
    public void setEmailConfirmationDao(EmailConfirmationDao emailConfirmationDao) {
        this.emailConfirmationDao = emailConfirmationDao;
    }

    @EJB
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    public void addUser(User user) throws InvalidUserException {

        //Validate user with Entity validations (@NotNull, @Size, etc.)
        String s = validateEntity(user);
        if (s != null){
            throw new InvalidUserException(s);
        }
        //Create new EmailConfirmation entity and set randomly generated confirmationCode
        EmailConfirmation confirmation = new EmailConfirmation();
        confirmation.setUsername(user.getUsername());
        String confirmationCode = DigestUtils.md5Hex(new Date().toString() + new Random().nextInt());
        confirmation.setConfirmationCode(confirmationCode);
        emailConfirmationDao.persist(confirmation);

        //Send confirmation email using MailService
        String messageBody = "Для підтвердження реєстрації на rssms.org Вам необхідно перейти за посиланням: <br>"
                                + "<a href='http://rssms.org/emailConfirm?user=" + user.getUsername()
                                + "&code=" + confirmationCode + "'>Підтвердити реєстрацію</a>";
        mailService.sendMail(user.getEmail(), "Підтвердження реєстрації", messageBody);

        //Generate password hash and set user role to Role.UNCONFIRMED
        String passwordHash = DigestUtils.md5Hex(user.getPassword());
        user.setPassword(passwordHash);
        user.setRole(Role.UNCONFIRMED);
        userDao.persist(user);
    }

    public void removeUser(int userId) {
        userDao.remove(userId);
    }

    public void updateUser(User user) throws InvalidUserException {
        //Validate user with Entity validations (@NotNull, @Size, etc.)
        String s = validateEntity(user);
        if (s != null){
            throw new InvalidUserException(s);
        }
        userDao.persist(user);
    }

    public User findUser(int id) throws UserNotFoundException {
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

    // Method called when user confirms his email
    public void verifyUser(String username, String confirmationCode) throws EmailConfirmationNotFoundException, UserNotFoundException {
        // Get EmailConfirmation record by username
        EmailConfirmation confirmation = emailConfirmationDao.getByUsername(username);
        if (confirmation == null) {
            throw new EmailConfirmationNotFoundException("Email confirmation not found for user: " + username);
        }
        // Get User record by username
        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("User with username: " + username + " was not found!");
        }
        // If confirmation code from DB matches code from parameters - set user role to Role.SIMPLE
        // and remove EmailConfirmation record associated with this user
        if (confirmation.getConfirmationCode().equals(confirmationCode)) {
            user.setRole(Role.SIMPLE);
            userDao.persist(user);
            emailConfirmationDao.remove(confirmation);
        }
    }
}
