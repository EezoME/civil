package org.rssms.service;

import org.rssms.dao.ProjectDao;
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

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

/**
 *
 * Created by Eezo on 17.03.2016.
 */
@Stateless
public class ProjectServiceBean implements ProjectService {

    @Resource
    Validator validator;
    private ProjectDao projectDao;

    @Inject
    public void setProjectDao(ProjectDao projectDao){
        this.projectDao = projectDao;
    }

    @Override
    public void addProject(Project project) throws InvalidProjectException {
        validateProject(project);
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
        validateProject(project);
        projectDao.persist(project);
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
    public List<Project> findPrivilegedProjects() throws ProjectNotFoundException {
        List<Project> list = projectDao.findByPrivilegedStatus(true);
        if (list == null || list.isEmpty()){
            throw new ProjectNotFoundException("No projects with privileged status.");
        }
        return list;
    }

    @Override
    public void changeProjectStatus(User person, int projectId, Status status) throws InvalidUserRoleException, ProjectNotFoundException, InvalidProjectStatusException {
        Project project = projectDao.find(projectId);
        if (project == null){
            throw new ProjectNotFoundException("No project with id "+projectId);
        }
        if (status == null){
            throw new InvalidProjectStatusException("status = null");
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
        if (status == project.getStatus()){
            throw new InvalidProjectStatusException("Project already has '"+status.getUkrainianName()+"' status.");
        }
        project.setStatus(status);
        projectDao.persist(project);
    }

    @Override
    public void makeProjectPrivileged(User person, int projectId) throws InvalidProjectStatusException, ProjectNotFoundException, InvalidUserRoleException {
        // check user for privileged status
        if (person.getRole() != Role.PRIVILEGED){
            throw new InvalidUserRoleException("You ("+person.getUsername()+") have no privileged level.");
        }
        Project project = projectDao.find(projectId);
        if (project == null){
            throw new ProjectNotFoundException("No project with id "+projectId);
        }
        if (project.isPrivilegedStatus()){
            throw new InvalidProjectStatusException("Project with id "+projectId+" already has privileged status.");
        }
        project.setPrivilegedStatus(true);
        projectDao.persist(project);
    }

    private void validateProject(Project project) throws InvalidProjectException {
        // validate project with entity validations
        Set<ConstraintViolation<Project>> violationSet = validator.validate(project);
        for (ConstraintViolation<Project> violation : violationSet){
            if (violation.getInvalidValue().equals("something wrong")){ // may be not
                throw new InvalidProjectException(violation.getPropertyPath() + " " + violation.getMessage());
            }
        }
    }
}
