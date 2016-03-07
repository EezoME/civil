package dao;

import entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by User on 01.03.2016.
 */
public class UserDao extends AbstractDAO<User> {
    @PersistenceContext(unitName = "civil")
    private EntityManager em;

    public UserDao(Class<User> entityClass) {
        super(entityClass);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<User> getAll() {
        return namedQuery("User.getAll").getResultList();
    }
}
