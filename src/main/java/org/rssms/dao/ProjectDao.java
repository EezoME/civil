package org.rssms.dao;

import org.rssms.entity.Project;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by User on 02.03.2016.
 */
public class ProjectDao extends AbstractDAO<Project> {
    @PersistenceContext(unitName = "civil")
    private EntityManager em;

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
