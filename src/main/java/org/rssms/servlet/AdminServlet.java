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
        String status = req.getParameter("status");
        int page = 1;
        int recordsPerPage = 3;//////////////
        int noOfPages = 1;
        if(req.getParameter("page")!=null){
            page = Integer.parseInt(req.getParameter("page"));
        }
        if (action == null) {
            resp.sendRedirect("/admin?action=users");
        } else {
            List<User> users;
            switch (action) {
                case "users":
                    users = userService.findAllUsers();
                    if (users != null) {
                        int noOfRecords = users.size();
                        noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
                        users = userService.cutListForPage(users, page, recordsPerPage);
                        req.setAttribute("list", users);
                    }
                    req.setAttribute("noOfPages", noOfPages);
                    req.setAttribute("currentPage", page);
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
                        req.setAttribute("error", e.getMessage());
                        req.setAttribute("noOfPages", noOfPages);
                        req.setAttribute("currentPage", page);
                    }
                    resp.sendRedirect("/admin?action=users");
                    break;
                case "projects":
                    List<Project> projects = null;
                    if (status != null) {
                        try {
                            projects = projectService.findProjectByStatus(Status.valueOf(status));
                        } catch (ProjectNotFoundException e) {
                            req.setAttribute("error", e.getMessage());
                            req.setAttribute("noOfPages", noOfPages);
                            req.setAttribute("currentPage", page);
                            req.setAttribute("status",status);
                            req.getRequestDispatcher("/admin/index.jsp").forward(req, resp);
                        }
                    } else {
                        projects = projectService.findAllProjects();
                    }
                    if (projects != null) {
                        int noOfRecords = projects.size();
                        noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
                        projects = projectService.cutListForPage(projects, page, recordsPerPage);
                        req.setAttribute("list", projects);
                    }
                    req.setAttribute("noOfPages", noOfPages);
                    req.setAttribute("currentPage", page);
                    req.setAttribute("status",status);
                    req.getSession().setAttribute("lastAction", action);
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
                        req.setAttribute("error", e.getMessage());

                    }
                    resp.sendRedirect("/admin?action=projects");
                    break;
                case "closedProjects":
                    try {
                        List<Project> closedProjects = projectService.findProjectByStatus(Status.CLOSED);
                        req.setAttribute("list", closedProjects);
                        req.getSession().setAttribute("lastAction", action);
                        req.getRequestDispatcher("/admin/index.jsp").forward(req, resp);
                    } catch (ProjectNotFoundException e) {
                        req.setAttribute("error", e.getMessage());
                        req.getRequestDispatcher("/admin/index.jsp").forward(req, resp);
                    }
                    break;
            }
        }
    }
}
