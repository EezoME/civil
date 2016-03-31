package org.rssms.servlet;

import org.rssms.entity.User;
import org.rssms.enums.Role;
import org.rssms.exception.InvalidUserException;
import org.rssms.service.UserServiceBean;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by WRKSPACE2 on 3/30/2016.
 */
@WebServlet(urlPatterns = "/signup", loadOnStartup = 1)
@ManagedBean
public class RegistrationServlet extends HttpServlet {

//    UserServiceBean userServiceBean;
//
//    @EJB
//    public void setUserServiceBean(UserServiceBean userServiceBean) {
//        this.userServiceBean = userServiceBean;
//    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullName = request.getParameter("fullName");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String bDate = request.getParameter("bDate");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password_2");
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");

        if (!password.equals(password2)) {
            request.setAttribute("error", "Паролі не співпадають");
            request.getRequestDispatcher("/signup.jsp").forward(request, response);
            return;
        } else {
            User user = new User();
            user.setUsername(username);
            user.setFullName(fullName);
            user.setEmail(email);
            user.setRole(Role.UNCONFIRMED);
            try {
                user.setbDate(dateFormat.parse(bDate));
            } catch (ParseException e) {
                user.setbDate(new Date());
            }
            user.setPassword(password);
            try {
                new UserServiceBean().addUser(user);    // Replace with Inject
            } catch (InvalidUserException e) {
                request.setAttribute("error", e.getMessage());
                request.getRequestDispatcher("/signup.jsp").forward(request, response);
                return;
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/signup.jsp").forward(request, response);
    }
}
