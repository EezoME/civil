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
import org.rssms.service.interfaces.MailService;
import org.rssms.service.interfaces.UserService;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.soap.SOAPBinding;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Date;
import java.util.Random;
import java.util.Set;

/**
 * Created by WRKSPACE2 on 3/12/2016.
 */

@Stateless
public class UserServiceBean implements UserService {

    @Resource
    Validator validator;
    private UserDao userDao;
    private EmailConfirmationDao emailConfirmationDao;
    private MailService mailService;

    @Inject
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Inject
    public void setEmailConfirmationDao(EmailConfirmationDao emailConfirmationDao) {
        this.emailConfirmationDao = emailConfirmationDao;
    }

    @Inject
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    public void addUser(User user) throws InvalidUserException {
        Set<ConstraintViolation<User>> violationSet =  validator.validate(user);
        for (ConstraintViolation<User> violation : violationSet) {
            throw new InvalidUserException(violation.getPropertyPath() + " " + violation.getMessage());
        }

        EmailConfirmation confirmation = new EmailConfirmation();
        confirmation.setUsername(user.getUsername());
        String confirmationCode = DigestUtils.md5Hex(new Date().toString() + new Random().nextInt());
        confirmation.setConfirmationCode(confirmationCode);
        emailConfirmationDao.persist(confirmation);

        String messageBody = "Для підтвердження реєстрації на rssms.org Вам необхідно перейти за посиланням: <br>"
                                + "<a href='http://rssms.org/emailConfirm?user=" + user.getUsername()
                                + "&code=" + confirmationCode + "'>Підтвердити реєстрацію</a>";
        mailService.sendMail(user.getEmail(), "Підтвердження реєстрації", messageBody);

        String passwordHash = DigestUtils.md5Hex(user.getPassword());
        user.setPassword(passwordHash);
        user.setRole(Role.UNCONFIRMED);
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

    public void verifyUser(String username, String confirmationCode) throws EmailConfirmationNotFoundException, UserNotFoundException {
        EmailConfirmation confirmation = emailConfirmationDao.getByUsername(username);
        if (confirmation == null) {
            throw new EmailConfirmationNotFoundException("Email confirmation not found for user: " + username);
        }
        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("User with username: " + username + " was not found!");
        }

        if (confirmation.equals(confirmationCode)) {
            user.setRole(Role.SIMPLE);
            emailConfirmationDao.remove(confirmation);
        }
    }
}
