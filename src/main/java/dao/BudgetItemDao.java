package dao;

import entity.BudgetItem;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by User on 02.03.2016.
 */
public class BudgetItemDao extends AbstractDAO<BudgetItem> {
    @PersistenceContext(unitName = "civil")
    private EntityManager em;

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
