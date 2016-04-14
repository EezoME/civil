package org.rssms.servlet;

import org.rssms.entity.Project;
import org.rssms.entity.User;
import org.rssms.enums.Category;
import org.rssms.enums.Status;
import org.rssms.exception.InvalidProjectException;
import org.rssms.service.interfaces.ProjectService;

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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User creator = (User) req.getSession().getAttribute("user");
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
        project.setCreator(creator);
        try {
            project.setExpirationDate(dateFormat.parse(expirationDate));
            projectService.addProject(project);
        } catch (ParseException e) {
            project.setExpirationDate(new Date());
        } catch (InvalidProjectException e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/newProject.jsp").forward(req, resp);
            return;
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            req.getRequestDispatcher("/newProject.jsp").forward(req, resp);
        }
    }
}
