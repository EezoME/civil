package org.rssms.service;

import org.rssms.dao.interfaces.ProjectDao;
import org.rssms.entity.Project;
import org.rssms.entity.User;
import org.rssms.enums.Category;
import org.rssms.enums.Role;
import org.rssms.enums.Status;
import org.rssms.exception.InvalidProjectException;
import org.rssms.exception.InvalidProjectStatusException;
import org.rssms.exception.InvalidUserRoleException;
import org.rssms.exception.ProjectNotFoundException;
import org.rssms.service.interfaces.ProjectService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 *
 * Created by Eezo on 17.03.2016.
 */
@Stateless
public class ProjectServiceBean extends AbstractService<Project> implements ProjectService {

    private ProjectDao projectDao;

    @EJB
    public void setProjectDao(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    @Override
    public void addProject(Project project) throws InvalidProjectException {
        validateEntity(project);
        if (project.getCategory() == null){
            project.setCategory(Category.OTHER);
        }
        projectDao.persist(project);
    }

    @Override
    public void removeProject(int projectId) {
        projectDao.remove(projectId);
    }

    @Override
    public void updateProject(Project project) throws InvalidProjectException {
        String s = validateEntity(project);
        if (s != null){
            throw new InvalidProjectException(s);
        }
        projectDao.merge(project);
    }

    @Override
    public Project findProject(int id) throws ProjectNotFoundException {
        Project project = projectDao.find(id);
        if (project == null){
            throw new ProjectNotFoundException("Project with id "+id+" was not found.");
        }
        return project;
    }

    @Override
    public Project findProject(String title) throws ProjectNotFoundException {
        Project project = projectDao.findByTitle(title);
        if (project == null){
            throw new ProjectNotFoundException("Project with title '"+title+"' was not found.");
        }
        return project;
    }

    @Override
    public List<Project> findProjectsByCategory(Category category) throws ProjectNotFoundException {
        List<Project> list = projectDao.findByCategory(category);
        if (list == null || list.isEmpty()){
            throw new ProjectNotFoundException("No projects in category '"+category.getUkrainianName()+"'.");
        }
        return list;
    }

    @Override
    public List<Project> findProjectsByUser(User user) throws ProjectNotFoundException {
        List<Project> list = projectDao.findByCreator(user);
        if (list == null || list.isEmpty()){
            throw new ProjectNotFoundException("No projects with user '"+user.getUsername()+"'.");
        }
        return list;
    }

    @Override
    public List<Project> findPrivilegedProjects() throws ProjectNotFoundException {
        List<Project> list = projectDao.findByPrivilegedStatus(true);
        if (list == null || list.isEmpty()){
            throw new ProjectNotFoundException("No projects with privileged status.");
        }
        return list;
    }

    @Override
    public void changeProjectStatus(User person, int projectId, Status status) throws InvalidUserRoleException,
            ProjectNotFoundException, InvalidProjectStatusException {
        Project project = projectDao.find(projectId);
        if (project == null){
            throw new ProjectNotFoundException("No project with id "+projectId);
        }
        if (status == null){
            throw new InvalidProjectStatusException("Project status == null");
        }
        if (status == Status.BANNED) {
            // check user access level ( might worth add integer value for roles? )
            if (person.getRole() != Role.MODERATOR && person.getRole() != Role.ADMINISTRATOR) {
                throw new InvalidUserRoleException("Only administrator and moderator can ban project.");
            }
            if (project.getStatus() == null){
                throw new InvalidProjectStatusException("You can't ban unconfirmed project.");
            }
        }
        project.setStatus(status);
        projectDao.persist(project);
    }

    @Override
    public void makeProjectPrivileged(User person, int projectId) throws InvalidProjectStatusException,
            ProjectNotFoundException, InvalidUserRoleException {
        // check user for privileged status
        if (person.getRole() == null || person.getRole() != Role.PRIVILEGED) {
            throw new InvalidUserRoleException("You (" + person.getUsername() + ") have no privileged level.");
        }
        Project project = projectDao.find(projectId);
        if (project == null) {
            throw new ProjectNotFoundException("No project with id " + projectId);
        }
        if (project.isPrivilegedStatus()) {
            throw new InvalidProjectStatusException("Project with id " + projectId + " already has privileged status.");
        }
        project.setPrivilegedStatus(true);
        projectDao.persist(project);
    }

    @Override
    public List<Project> findAllProjects() {
        List<Project> list = projectDao.findAllProjects();
        return list;
    }

    @Override
    public List<Project> findAllPopularProjects() {
        List<Project> list = projectDao.findAllPopularProjects();
        return list;
    }

    @Override
    public List<Project> findProjectByStatus(Status status) throws ProjectNotFoundException {
        List<Project> list = projectDao.findProjectByStatus(status);
        if (list == null || list.isEmpty()) {
            throw new ProjectNotFoundException("No closed projects");
        }
        return list;
    }

    @Override
    public List<Project> cutListForPage(List<Project> projects, int page, int recordsPerPage) {
        int startIndex = (page - 1) * recordsPerPage;
        int endIndex = page * recordsPerPage > projects.size() ? projects.size() : page * recordsPerPage;
        return projects.subList(startIndex, endIndex);
    }
}
