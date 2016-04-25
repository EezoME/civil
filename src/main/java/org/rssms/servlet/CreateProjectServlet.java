package org.rssms.servlet;

import org.rssms.entity.Project;
import org.rssms.entity.User;
import org.rssms.enums.Category;
import org.rssms.enums.Status;
import org.rssms.exception.InvalidProjectException;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by User on 13.04.2016.
 */
@WebServlet(urlPatterns = "/newProject")
public class CreateProjectServlet extends HttpServlet {

    @EJB
    private ProjectService projectService;

    @EJB
    private UserService userService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String title = req.getParameter("title");
        String description = req.getParameter("desc");
        Category category = Category.valueOf(req.getParameter("category"));
        String expirationDate = req.getParameter("date");
        int goalCost = Integer.valueOf(req.getParameter("sum"));
        Status status = Status.OPEN;

        Project project = new Project();
        project.setTitle(title);
        project.setDescription(description);
        project.setCategory(category);
        project.setRegistrationDate(new Date());
        project.setGoalCost(goalCost);
        project.setStatus(status);
        project.setPrivilegedStatus(false);
        try {
            User creator = userService.findUser(req.getRemoteUser());
            project.setCreator(creator);
            project.setExpirationDate(dateFormat.parse(expirationDate));
            projectService.addProject(project);
        } catch (ParseException e) {
            project.setExpirationDate(new Date());
        } catch (InvalidProjectException e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/user/newProject.jsp").forward(req, resp);
            return;
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuffer url = req.getRequestURL();
        String ctx = req.getContextPath();
        String uri = req.getRequestURI();
        String base = url.substring(0, url.length() - uri.length() + ctx.length());
        if (req.getUserPrincipal() != null) req.getRequestDispatcher("/user/newProject.jsp").forward(req, resp);
        else resp.sendRedirect(base + "/login");

    }
}
