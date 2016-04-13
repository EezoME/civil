package org.rssms.servlet;

import org.rssms.entity.Project;
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
        //User creator TODO:user from session
//        User user= (User) req.getSession().getAttribute("user");
//        System.out.println(user.getUserId()+"  "+user.getUsername());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        String title = req.getParameter("title");
        String description = req.getParameter("desc");
        String categorystr = req.getParameter("category");
        Category category = Category.valueOf(req.getParameter("category"));
        String expirationDate = req.getParameter("date");
        int goalCost = Integer.valueOf(req.getParameter("sum"));
        Status status = Status.OPEN;

        Project project = new Project();
        project.setTitle(title);
        project.setDescription(description);
        project.setCategory(category);
        project.setRegistrationDate(new Date());
//        dateFormat.getCalendar().getTime();
        project.setGoalCost(goalCost);
        project.setStatus(status);
        project.setPrivilegedStatus(false);
        try {
            project.setExpirationDate(dateFormat.parse(expirationDate));
            System.out.println();
        } catch (ParseException e) {
            project.setExpirationDate(new Date());
        }
        try {
            projectService.addProject(project);
        } catch (InvalidProjectException e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/newProject.jsp").forward(req, resp);
            return;
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO:check authorization
        req.getRequestDispatcher("/newProject.jsp").forward(req, resp);
//        if (true){
//            req.getRequestDispatcher("/newProject.jsp").forward(req, resp);
//        }else {
//            resp.sendRedirect("/civil-core-1.0-SNAPSHOT/signup");
//        }
    }
}
