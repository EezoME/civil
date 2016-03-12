package org.rssms.dao;

import org.rssms.dao.interfaces.ProjectDaoInterface;
import org.rssms.entity.Project;
import org.rssms.entity.User;
import org.rssms.enums.Category;

import java.util.Date;
import java.util.List;

/**
 * Created by User on 02.03.2016.
 */
public class ProjectDao extends AbstractJpaDao<Project> implements ProjectDaoInterface {

    public ProjectDao(Class<Project> entityClass) {
        super(entityClass);
    }


    public List<Project> getAll() {
        return namedQuery("Project.getAll").getResultList();
    }

    @Override
    public Project findByTitle(String title) {
        return (Project) getEntityManager().createQuery("select p from Project p where p.title=:title").setParameter("title", title).getSingleResult();
    }

    @Override
    public List<Project> findByDesc(String desc) {
        return getEntityManager().createQuery("select p from Project p where p.desc LIKE :desc").setParameter("desc", "%" + desc + "%").getResultList();
    }

    @Override
    public List<Project> findByGoalCost(int goalCost) {
        return getEntityManager().createQuery("select p from Project p where p.goalCost=:goalCost").setParameter("goalCost", goalCost).getResultList();
    }

    @Override
    public List<Project> findByFundedSum(int fundedSum) {
        return getEntityManager().createQuery("select p from Project p where p.fundedSum=:fundedSum").setParameter("fundedSum", fundedSum).getResultList();
    }

    @Override
    public List<Project> findByPrivilegedStatus(boolean privilegedStatus) {
        return getEntityManager().createQuery("select p from Project p where p.privilegedStatus=:privilegedStatus").setParameter("privilegedStatus", privilegedStatus).getResultList();

    }

    @Override
    public List<Project> findByCreator(User creator) {
        return getEntityManager().createQuery("select p from Project p where p.creator=:creator").setParameter("creator", creator).getResultList();
    }

    @Override
    public List<Project> findByCategory(Category category) {
        return getEntityManager().createQuery("select p from Project p where p.category=:category").setParameter("category", category).getResultList();
    }

    @Override
    public List<Project> findByRegistrationDate(Date registrationDate) {
        return getEntityManager().createQuery("select p from Project p where p.registrationDate=:registrationDate").setParameter("registrationDate", registrationDate).getResultList();
    }

    @Override
    public List<Project> findByExpirationDate(Date expirationDate) {
        return getEntityManager().createQuery("select p from Project p where p.expirationDate=:expirationDate").setParameter("expirationDate", expirationDate).getResultList();
    }
}
