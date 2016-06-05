package org.rssms.servlet;

import org.rssms.entity.User;
import org.rssms.enums.Role;
import org.rssms.exception.InvalidUserException;
import org.rssms.exception.UserNotFoundException;
import org.rssms.service.interfaces.UserService;

import javax.ejb.EJB;
import javax.ejb.EJBTransactionRolledbackException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

/**
 * Created by Eezo on 04.06.2016.
 */
@WebServlet(loadOnStartup = 1, urlPatterns = "/init")
public class DatabaseInitServlet extends HttpServlet {
    @EJB
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            userService.findUser("admin");
            System.out.println("Admin user is already exists;");
        } catch (EJBTransactionRolledbackException | UserNotFoundException e) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword("admin");
            admin.setRole(Role.ADMINISTRATOR);
            admin.setbDate(Date.valueOf("1995-01-13"));
            admin.setEmail("admin@gmail.com");
            admin.setFullName("Admin Root Admin");
            try {
                userService.addUser(admin);
            } catch (InvalidUserException e1) {
                System.err.println("Cannot create admin.");
                e1.printStackTrace();
            }
        }
        req.getRequestDispatcher("/home.jsp").forward(req, resp);
    }
}
