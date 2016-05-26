package org.rssms.servlet;

import org.rssms.entity.Comment;
import org.rssms.entity.Project;
import org.rssms.entity.User;
import org.rssms.exception.CommentNotFoundException;
import org.rssms.exception.InvalidCommentException;
import org.rssms.exception.ProjectNotFoundException;
import org.rssms.exception.UserNotFoundException;
import org.rssms.service.interfaces.CommentService;
import org.rssms.service.interfaces.LiqPayService;
import org.rssms.service.interfaces.ProjectService;
import org.rssms.service.interfaces.UserService;

import javax.ejb.EJB;
import javax.ejb.EJBTransactionRolledbackException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by WRKSPACE2 on 5/15/2016.
 */
@WebServlet(urlPatterns = "/projects/*")
public class ShowProjectServlet extends HttpServlet {

    @EJB
    private UserService userService;

    @EJB
    private ProjectService projectService;

    @EJB
    private CommentService commentService;

    @EJB
    private LiqPayService liqPayService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Project project = null;
        List<Comment> comments = null;

        try {
            project = projectService.findProject(Integer.parseInt(request.getPathInfo().substring(1)));
            comments = commentService.findCommentsByProject(project);
        } catch (ProjectNotFoundException e) {
            e.printStackTrace();
        } catch (CommentNotFoundException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        request.setAttribute("comments", comments);

        if (project != null) {
            request.setAttribute("project", project);
            request.setAttribute("liqpay_params", liqPayService.generateLiqPayParams(project));
            request.getRequestDispatcher("/project.jsp").forward(request, response);
        } else {
            response.sendRedirect("/");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Comment comment = new Comment();
        try {
            User user = userService.findUser(req.getRemoteUser());
            if (user == null){
                req.setAttribute("error", "Увійдіть до системи.");
                resp.sendRedirect("/login");
                return;
            }
            comment.setAuthor(user);
            comment.setContent(req.getParameter("content"));
            comment.setProject(projectService.findProject(Integer.parseInt(req.getParameter("projectID"))));
            comment.setTimePosted(new Date());
            commentService.addComment(comment);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            req.setAttribute("error", e.getMessage());
        } catch (ProjectNotFoundException e) {
            e.printStackTrace();
            req.setAttribute("error", e.getMessage());
        } catch (InvalidCommentException e) {
            e.printStackTrace();
            req.setAttribute("error", e.getMessage());
        }

        req.getRequestDispatcher("/project.jsp").forward(req, resp);
    }
}
