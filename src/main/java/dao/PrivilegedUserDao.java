package dao;

import entity.PrivilegedUser;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by User on 02.03.2016.
 */
public class PrivilegedUserDao extends AbstractDAO<PrivilegedUser> {
    private EntityManager em = Persistence.createEntityManagerFactory("civil").createEntityManager();

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

