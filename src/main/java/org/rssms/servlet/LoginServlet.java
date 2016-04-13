package org.rssms.servlet;

import org.rssms.entity.User;
import org.rssms.enums.Role;
import org.rssms.exception.InvalidUserException;
import org.rssms.service.interfaces.UserService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by WRKSPACE2 on 4/13/2016.
 */

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @EJB
    private UserService userServiceBean;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
}
