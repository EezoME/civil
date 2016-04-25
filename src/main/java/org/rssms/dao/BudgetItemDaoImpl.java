package org.rssms.dao;

import org.rssms.dao.interfaces.BudgetItemDao;
import org.rssms.entity.BudgetItem;
import org.rssms.entity.Project;

import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by User on 02.03.2016.
 */
@Stateless
public class BudgetItemDaoImpl extends AbstractJpaDao<BudgetItem> implements BudgetItemDao {

    public List<BudgetItem> getAll() {
        return namedQuery("BudgetItem.getAll").getResultList();
    }


    @Override
    public List<BudgetItem> findByDesc(String desc) {
        return getEntityManager().createQuery("select bi from BudgetItem bi where bi.description like :desc").setParameter("desc", "%"+desc+"%").getResultList();
    }

    @Override
    public List<BudgetItem> findByProject(Project project) {
        return getEntityManager().createQuery("select bi from BudgetItem bi where bi.project=:project").setParameter("project", project).getResultList();

    }

    @Override
    public BudgetItem findByName(String name) {
        return (BudgetItem) getEntityManager().createQuery("select bi from BudgetItem bi where bi.name=:name").setParameter("name", name).getSingleResult();
    }
}
