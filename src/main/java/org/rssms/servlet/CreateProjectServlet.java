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
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.awt.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by User on 13.04.2016.
 */
@WebServlet(urlPatterns = "/newProject")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10,    // 10 MB
        maxFileSize = 1024 * 1024 * 50,          // 50 MB
        maxRequestSize = 1024 * 1024 * 100,      // 100 MB
        location = "/")
public class CreateProjectServlet extends HttpServlet {

    @EJB
    private ProjectService projectService;

    @EJB
    private UserService userService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> savedValues = req.getParameterMap();
        req.setAttribute("savedValues", savedValues);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String title = req.getParameter("title");
        String description = req.getParameter("desc");
        Category category = Category.valueOf(req.getParameter("category"));
        String expirationDate = req.getParameter("date");
        int goalCost = Integer.valueOf(req.getParameter("sum"));
        Status status = Status.OPEN;


        Part filePart = req.getPart("img");
        InputStream is = filePart.getInputStream();
        BufferedImage originalImage = ImageIO.read(is);
        Image resizedImage = originalImage.getScaledInstance(-1, 500, Image.SCALE_SMOOTH);

        BufferedImage bufferedThumbnail = new BufferedImage(resizedImage.getWidth(null),
                resizedImage.getHeight(null),
                BufferedImage.TYPE_INT_RGB);
        bufferedThumbnail.getGraphics().drawImage(resizedImage, 0, 0, null);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedThumbnail, "png", baos);
        baos.flush();
        byte[] imageData = baos.toByteArray();
        baos.close();


        Project project = new Project();
        project.setTitle(title);
        project.setDescription(description);
        project.setCategory(category);
        project.setRegistrationDate(new Date());
        project.setGoalCost(goalCost);
        project.setStatus(status);
        project.setPrivilegedStatus(false);
        project.setAvatar(imageData);
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
        resp.sendRedirect("/");
        //req.getRequestDispatcher("/explore.html").forward(req, resp);
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
