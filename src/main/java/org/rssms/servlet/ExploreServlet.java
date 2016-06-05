package org.rssms.servlet;

import org.rssms.entity.Project;
import org.rssms.enums.Category;
import org.rssms.exception.ProjectNotFoundException;
import org.rssms.service.interfaces.ProjectService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by WRKSPACE2 on 5/16/2016.
 */
@WebServlet(urlPatterns = "/explore")
public class ExploreServlet extends HttpServlet {

    @EJB
    private ProjectService projectService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String category = request.getParameter("category");
        String sortBy = request.getParameter("sort");
        List<Project> projects = null;
        int page = 1;
        int recordsPerPage = 3;//////////////
        int noOfPages = 1;
        if (request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));

        if (category != null) {
            try {
                projects = projectService.findProjectsByCategory(Category.valueOf(category));
            } catch (ProjectNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            projects = projectService.findAllPopularProjects();
        }
        if (projects != null && sortBy != null) {
            switch (sortBy) {
                case "popularity":
                    Collections.sort(projects, new Comparator<Project>() {
                        @Override
                        public int compare(Project o1, Project o2) {
                            return (int) (o1.getFundedSum() - o2.getFundedSum());
                        }
                    });
                    break;
                case "date":
                    Collections.sort(projects, new Comparator<Project>() {
                        @Override
                        public int compare(Project o1, Project o2) {
                            return o1.getExpirationDate().compareTo(o2.getExpirationDate());
                        }
                    });
                    break;
                case "goal":
                    Collections.sort(projects, new Comparator<Project>() {
                        @Override
                        public int compare(Project o1, Project o2) {
                            return o1.getGoalCost() - o2.getGoalCost();
                        }
                    });
                    break;
            }

        }
        if (projects == null || projects.isEmpty()) {
            request.setAttribute("error", "У цій категорії немає жодного проекту.\nВи можете <a href='/newProject'>додати свій</a>.");
        }
        if (projects != null) {
            int noOfRecords = projects.size();
            noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            projects = projectService.cutListForPage(projects, page, recordsPerPage);
            request.setAttribute("projects", projects);
        }
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        request.setAttribute("sort", sortBy);
        request.setAttribute("category", category);
        request.setAttribute("categories", Category.values());
        request.getRequestDispatcher("/explore.jsp").forward(request, response);
    }
}