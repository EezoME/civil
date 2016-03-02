package dao;

import entity.BudgetItem;
import entity.User;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by User on 02.03.2016.
 */
public class BudgetItemDao extends AbstractDAO<BudgetItem> {
    private EntityManager em = Persistence.createEntityManagerFactory("civil").createEntityManager();

    public BudgetItemDao(Class<BudgetItem> entityClass) {
        super(entityClass);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<BudgetItem> getAll() {
        return namedQuery("BudgetItem.getAll").getResultList();
    }
}
