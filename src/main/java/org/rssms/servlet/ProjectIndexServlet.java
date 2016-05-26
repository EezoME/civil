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
        int page = 1;
        int recordsPerPage = 3;/////////////////////////////////////
        int noOfPages=1;
        if (request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));

        List<Project> projects = projectService.findAllPopularProjects();
        if (projects != null) {
            int noOfRecords = projects.size();
            noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            projects = projectService.cutListForPage(projects, page, recordsPerPage);
            request.setAttribute("projects", projects);
        }
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        request.getRequestDispatcher("/home.jsp").forward(request, response);
    }
}
