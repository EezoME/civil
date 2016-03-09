package org.rssms.dao;

import org.rssms.entity.PrivilegedUser;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by User on 02.03.2016.
 */
public class PrivilegedUserDao extends AbstractDAO<PrivilegedUser> {
    @PersistenceContext(unitName = "civil")
    private EntityManager em;

    public PrivilegedUserDao(Class<PrivilegedUser> entityClass) {
        super(entityClass);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<PrivilegedUser> getAll() {
        return namedQuery("PrivilegedUser.getAll").getResultList();
    }
}

