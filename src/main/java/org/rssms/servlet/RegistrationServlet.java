package org.rssms.servlet;

import org.rssms.entity.User;
import org.rssms.enums.Role;
import org.rssms.exception.InvalidUserException;
import org.rssms.service.interfaces.UserService;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by WRKSPACE2 on 3/30/2016.
 */
@WebServlet(urlPatterns = "/signup")
public class RegistrationServlet extends HttpServlet {

    @EJB
    private UserService userServiceBean;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullName = request.getParameter("fullName");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String bDate = request.getParameter("bDate");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password_2");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Map<String, String[]> savedValues = request.getParameterMap();
        request.setAttribute("savedValues", savedValues);

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
                userServiceBean.addUser(user);
            } catch (InvalidUserException e) {
                e.printStackTrace();
                request.setAttribute("error", e.getMessage());
                request.getRequestDispatcher("/signup.jsp").forward(request, response);
                return;
            } catch (EJBException e) {
                e.printStackTrace();
                request.setAttribute("error", "Користувач з таким логіном вже існує.");
                request.getRequestDispatcher("/signup.jsp").forward(request, response);
                return;
            }
        }
        response.sendRedirect("/");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/signup.jsp").forward(request, response);
    }
}
