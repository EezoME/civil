package org.rssms.service.interfaces;

import org.rssms.entity.Project;
import org.rssms.entity.User;
import org.rssms.enums.Category;
import org.rssms.exception.InvalidProjectException;
import org.rssms.exception.InvalidProjectStatusException;
import org.rssms.exception.InvalidUserRoleException;
import org.rssms.exception.ProjectNotFoundException;

import java.util.List;

/**
 *
 * Created by Eezo on 17.03.2016.
 */
public interface ProjectService {

    void addProject(Project project) throws InvalidProjectException;

    void removeProject(int projectId);

    void updateProject(Project project) throws InvalidProjectException;

    Project findProject(int id) throws ProjectNotFoundException;

    Project findProject(String title) throws ProjectNotFoundException;

    List<Project> findProjectsByCategory(Category category) throws ProjectNotFoundException;

    List<Project> findPrivilegedProjects() throws ProjectNotFoundException;

    /**
     * Confirms project.<br/>
     * <b>NOTE:</b> Administrator or Moderator must confirm project order to it could be available to viewing.
     * @param person a user who confirms
     * @param projectId a project you want to confirm
     * @throws InvalidUserRoleException
     * @throws ProjectNotFoundException
     * @throws InvalidProjectStatusException
     */
    void confirmProject(User person, int projectId) throws InvalidUserRoleException, ProjectNotFoundException, InvalidProjectStatusException;

    /**
     * Marks project as privileged.
     * @param person an privileged user
     * @param projectId a project you want to be privileged
     * @throws InvalidProjectStatusException
     * @throws ProjectNotFoundException
     * @throws InvalidUserRoleException
     */
    void makeProjectPrivileged(User person, int projectId) throws InvalidProjectStatusException, ProjectNotFoundException, InvalidUserRoleException;
}
