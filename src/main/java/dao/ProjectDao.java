package dao;

import entity.Project;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by User on 02.03.2016.
 */
public class ProjectDao extends AbstractDAO<Project> {
    private EntityManager em = Persistence.createEntityManagerFactory("civil").createEntityManager();

    public ProjectDao(Class<Project> entityClass) {
        super(entityClass);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<Project> getAll() {
        return namedQuery("Project.getAll").getResultList();
    }
}
