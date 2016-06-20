package org.rssms.servlet;

import org.apache.commons.codec.binary.Base64;
import org.rssms.entity.Project;
import org.rssms.entity.User;
import org.rssms.enums.Category;
import org.rssms.enums.Role;
import org.rssms.enums.Status;
import org.rssms.exception.InvalidProjectException;
import org.rssms.exception.ProjectNotFoundException;
import org.rssms.exception.UserNotFoundException;
import org.rssms.service.interfaces.ProjectService;
import org.rssms.service.interfaces.UserService;

import javax.ejb.EJB;
import javax.ejb.EJBTransactionRolledbackException;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.awt.*;
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
        if (title == null || title.trim().isEmpty()){
            req.setAttribute("error", "Вкажіть назву проекту.");
            req.getRequestDispatcher("/user/newProject.jsp").forward(req, resp);
            return;
        }
        if (title.length() < 5 || title.length() > 64){
            req.setAttribute("error", "Назва проекту повина бути від 5 до 64 символів.");
            req.getRequestDispatcher("/user/newProject.jsp").forward(req, resp);
            return;
        }
//        try {
//            projectService.findProject(title);
//            req.setAttribute("error", "Проект з такою назвою вже існує.");
//            req.getRequestDispatcher("/user/newProject.jsp").forward(req, resp);
//            return;
//        } catch (ProjectNotFoundException e) {
//            // this is fine
//        }
        String description = req.getParameter("desc");
        Category category = Category.valueOf(req.getParameter("category"));
        String expirationDate = req.getParameter("date");
        try {
            int goalCost = 0;
            goalCost = Integer.valueOf(req.getParameter("sum"));
            Status status = Status.CLOSED;
            Part filePart = req.getPart("img");
            InputStream is = filePart.getInputStream();
            BufferedImage originalImage = ImageIO.read(is);
            byte[] imageData = null;

            Image resizedImage = originalImage.getScaledInstance(-1, 500, Image.SCALE_SMOOTH);
            BufferedImage bufferedThumbnail = new BufferedImage(resizedImage.getWidth(null),
                    resizedImage.getHeight(null),
                    BufferedImage.TYPE_INT_RGB);
            bufferedThumbnail.getGraphics().drawImage(resizedImage, 0, 0, null);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedThumbnail, "png", baos);
            baos.flush();
            imageData = baos.toByteArray();
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

            User creator = userService.findUser(req.getRemoteUser());
            project.setCreator(creator);
            project.setExpirationDate(dateFormat.parse(expirationDate));
            projectService.addProject(project);
        } catch (InvalidProjectException e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/user/newProject.jsp").forward(req, resp);
        } catch (EJBTransactionRolledbackException e) {
            req.setAttribute("error", "Перевірте правильність вводу даних: назва проекту від 5 до 64 символів, опис - не менше 15 символів.");
            req.getRequestDispatcher("/user/newProject.jsp").forward(req, resp);
        } catch (UserNotFoundException e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/user/newProject.jsp").forward(req, resp);
        } catch (NullPointerException e) {
            req.setAttribute("error", "Додайте зображення проекту");
            req.getRequestDispatcher("/user/newProject.jsp").forward(req, resp);
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Поле 'Необхідна сума' пусте");
            req.getRequestDispatcher("/user/newProject.jsp").forward(req, resp);
        } catch (ParseException e) {
            req.setAttribute("error", "Не вказана дата завершення");
            req.getRequestDispatcher("/user/newProject.jsp").forward(req, resp);
        }
        req.setAttribute("info", "Ваш проект успішно додано! Після проходження модерації він буде доступний для перегляду");
        req.getRequestDispatcher("/user/newProject.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuffer url = req.getRequestURL();
        String ctx = req.getContextPath();
        String uri = req.getRequestURI();
        String base = url.substring(0, url.length() - uri.length() + ctx.length());
        if (req.getUserPrincipal() != null && !req.isUserInRole(Role.UNCONFIRMED.toString())) req.getRequestDispatcher("/user/newProject.jsp").forward(req, resp);
        else resp.sendRedirect(base + "/login");

    }
}
