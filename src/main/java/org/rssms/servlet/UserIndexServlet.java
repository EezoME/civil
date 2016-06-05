package org.rssms.servlet;

import org.rssms.entity.Project;
import org.rssms.entity.User;
import org.rssms.exception.ProjectNotFoundException;
import org.rssms.exception.UserNotFoundException;
import org.rssms.service.interfaces.ProjectService;
import org.rssms.service.interfaces.UserService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *
 * Created by Eezo on 17.05.2016.
 */
@WebServlet(urlPatterns = "/user/profile")
public class UserIndexServlet extends HttpServlet {

    @EJB
    private ProjectService projectService;
    @EJB
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Project> projects = null;

        try {
            User user = userService.findUser(req.getRemoteUser());
            projects = projectService.findProjectsByUser(user);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        } catch (ProjectNotFoundException e) {
            e.printStackTrace();
        }

        req.setAttribute("projects", projects);
        req.getRequestDispatcher("/user/profile.jsp").forward(req, resp);
    }

}
