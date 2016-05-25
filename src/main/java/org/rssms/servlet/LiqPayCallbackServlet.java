package org.rssms.servlet;

import org.rssms.entity.Project;
import org.rssms.entity.User;
import org.rssms.exception.ProjectNotFoundException;
import org.rssms.exception.UserNotFoundException;
import org.rssms.service.interfaces.LiqPayService;
import org.rssms.service.interfaces.ProjectService;
import org.rssms.service.interfaces.UserService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by WRKSPACE2 on 5/25/2016.
 */
@WebServlet(urlPatterns = "/liqpay_callback")
public class LiqPayCallbackServlet extends HttpServlet {

    @EJB
    private LiqPayService liqPayService;

    @EJB
    private UserService userService;

    @EJB
    private ProjectService projectService;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int projectId = Integer.parseInt(req.getParameter("projectId"));
        String orderId = req.getParameter("orderId");
        User user = null;
        Project project = null;
        try {
            if(req.getRemoteUser() != null) {
                user = userService.findUser(req.getRemoteUser());
            }
            project = projectService.findProject(projectId);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        } catch (ProjectNotFoundException e) {
            e.printStackTrace();
        }
        try {
            liqPayService.checkPayment(orderId, user, project);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(project != null) {
            req.setAttribute("project", project);
            req.setAttribute("liqpay_params", liqPayService.generateLiqPayParams(project));
            req.getRequestDispatcher("partial/project-funds.jsp").forward(req, resp);
        }
    }
}
