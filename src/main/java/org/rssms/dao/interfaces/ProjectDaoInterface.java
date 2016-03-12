package org.rssms.dao.interfaces;

import org.rssms.entity.Project;
import org.rssms.entity.User;
import org.rssms.enums.Category;

import java.util.Date;
import java.util.List;

/**
 * Created by User on 12.03.2016.
 */
public interface ProjectDaoInterface extends GenericDao<Project> {
    Project findByTitle(String title);

    List<Project> findByDesc(String desc);

    List<Project> findByGoalCost(int goalCost);

    List<Project> findByFundedSum(int fundedSum);

    List<Project> findByPrivilegedStatus(boolean privilegedStatus);

    List<Project> findByCreator(User creator);

    List<Project> findByCategory(Category category);

    List<Project> findByRegistrationDate(Date registrationDate);

    List<Project> findByExpirationDate(Date expirationDate);


}
