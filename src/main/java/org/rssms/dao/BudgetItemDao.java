package org.rssms.dao;

import org.rssms.dao.interfaces.BudgetItemDaoInterface;
import org.rssms.entity.BudgetItem;
import org.rssms.entity.Project;

import java.util.List;

/**
 * Created by User on 02.03.2016.
 */
public class BudgetItemDao extends AbstractJpaDao<BudgetItem> implements BudgetItemDaoInterface {


    public BudgetItemDao(Class<BudgetItem> entityClass) {
        super(entityClass);
    }


    public List<BudgetItem> getAll() {
        return namedQuery("BudgetItem.getAll").getResultList();
    }


    @Override
    public List<BudgetItem> findByDesc(String desc) {
        return getEntityManager().createQuery("select bi from BudgetItem bi where bi.desc=:desc").setParameter("desc", desc).getResultList();
    }

    @Override
    public List<BudgetItem> findByProject(Project project) {
        return getEntityManager().createQuery("select bi from BudgetItem bi where bi.project=:project").setParameter("project", project).getResultList();

    }

    @Override
    public List<BudgetItem> findByName(String name) {
        return getEntityManager().createQuery("select bi from BudgetItem bi where bi.name=:name").setParameter("name", name).getResultList();
    }
}