package org.rssms.servlet;

import org.rssms.entity.Project;
import org.rssms.exception.ProjectNotFoundException;
import org.rssms.service.interfaces.ProjectService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by WRKSPACE2 on 5/15/2016.
 */
@WebServlet(urlPatterns = "/projects/*")
public class ShowProjectServlet extends HttpServlet {

    @EJB
    private ProjectService projectService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Project project = null;
        try {
            project = projectService.findProject(Integer.parseInt(request.getPathInfo().substring(1)));
        } catch (ProjectNotFoundException e) {
            e.printStackTrace();
        }
        if(project != null) {
            request.setAttribute("project", project);
            request.getRequestDispatcher("/project.jsp").forward(request, response);
        } else {
            response.sendRedirect("/");
        }
    }
}
