package org.rssms.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by WRKSPACE2 on 4/13/2016.
 */

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> savedValues = req.getParameterMap();
        req.setAttribute("savedValues", savedValues);

        req.setAttribute("error", "Невірний логін або пароль");
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }
}
