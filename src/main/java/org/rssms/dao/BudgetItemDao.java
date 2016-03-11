package org.rssms.dao;

import org.rssms.entity.BudgetItem;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by User on 02.03.2016.
 */
public class BudgetItemDao extends AbstractDAO<BudgetItem> {


    public BudgetItemDao(Class<BudgetItem> entityClass) {
        super(entityClass);
    }

    public List<BudgetItem> getAll() {
        return namedQuery("BudgetItem.getAll").getResultList();
    }
}
