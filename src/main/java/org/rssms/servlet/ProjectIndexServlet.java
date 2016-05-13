package org.rssms.servlet;

import org.rssms.entity.Project;
import org.rssms.service.interfaces.ProjectService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by WRKSPACE2 on 5/13/2016.
 */

@WebServlet(urlPatterns = {"/index.html"})
public class ProjectIndexServlet extends HttpServlet {

    @EJB
    private ProjectService projectService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Project> projects = projectService.findAllPopularProjects();
        request.setAttribute("projects", projects);
        request.getRequestDispatcher("/home.jsp").forward(request, response);
    }
}
