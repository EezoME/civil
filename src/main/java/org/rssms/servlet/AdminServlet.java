package org.rssms.servlet;

import org.rssms.entity.Project;
import org.rssms.entity.User;
import org.rssms.enums.Category;
import org.rssms.enums.Role;
import org.rssms.enums.Status;
import org.rssms.exception.InvalidProjectException;
import org.rssms.exception.InvalidUserException;
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
 * Created by User on 17.05.2016.
 */
@WebServlet(urlPatterns = "/admin")
public class AdminServlet extends HttpServlet {
    @EJB
    UserService userService;
    @EJB
    ProjectService projectService;

    @Override

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            resp.sendRedirect("/admin?action=users");
        } else {
            List<User> users;
            switch (action) {
                case "users":
                    users = userService.findAllUsers();
                    req.setAttribute("list", users);
                    req.getRequestDispatcher("/admin/index.jsp").forward(req, resp);
                    break;
                case "deleteUser":
                    userService.removeUser(Integer.parseInt(req.getParameter("id")));
                    resp.sendRedirect("/admin?action=users");
                    break;
                case "updateUser":
                    try {
                        int id = Integer.parseInt(req.getParameter("id"));
                        User user = userService.findUser(id);
                        user.setRole(Role.valueOf(req.getParameter(id + "_role")));
                        userService.updateUser(user);
                    } catch (UserNotFoundException | InvalidUserException e) {
                        e.printStackTrace();
                    }
                    resp.sendRedirect("/admin?action=users");
                    break;
                case "projects":
                    List<Project> projects = projectService.findAllProjects();
                    req.setAttribute("list", projects);
                    req.getRequestDispatcher("/admin/index.jsp").forward(req, resp);
                    break;
                case "deleteProject":
                    projectService.removeProject(Integer.parseInt(req.getParameter("id")));
                    resp.sendRedirect("/admin?action=projects");
                    break;
                case "updateProject":
                    try {
                        int id = Integer.parseInt(req.getParameter("id"));
                        Project project = projectService.findProject(id);
                        project.setCategory(Category.valueOf(req.getParameter(id + "_category")));
                        project.setStatus(Status.valueOf(req.getParameter(id + "_status")));
                        projectService.updateProject(project);
                    } catch (ProjectNotFoundException | InvalidProjectException e) {
                        e.printStackTrace();
                    }
                    resp.sendRedirect("/admin?action=projects");
                    break;

            }
        }
    }
}
