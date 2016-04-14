package org.rssms.service;

import org.rssms.dao.interfaces.BudgetItemDaoInterface;
import org.rssms.entity.BudgetItem;
import org.rssms.entity.Project;
import org.rssms.exception.BudgetItemNotFoundException;
import org.rssms.exception.InvalidBudgetItemException;
import org.rssms.service.interfaces.BudgetItemService;

import javax.ejb.EJB;
import java.util.List;

/**
 * Created by Eezo on 14.04.2016.
 */
public class BudgetItemServiceBean extends AbstractService<BudgetItem> implements BudgetItemService {

    private BudgetItemDaoInterface budgetItemDao;

    @EJB
    public void setBudgetItemDao(BudgetItemDaoInterface budgetItemDao) {
        this.budgetItemDao = budgetItemDao;
    }

    @Override
    public void addBudgetItem(BudgetItem budgetItem) throws InvalidBudgetItemException {
        String s = validateEntity(budgetItem);

        if (s != null) {
            throw new InvalidBudgetItemException(s);
        }

        budgetItemDao.persist(budgetItem);
    }

    @Override
    public void removeBudgetItem(int budgetItemId) {
        budgetItemDao.remove(budgetItemId);
    }

    @Override
    public void updateBudgetItem(BudgetItem budgetItem) throws InvalidBudgetItemException {
        String s = validateEntity(budgetItem);

        if (s != null) {
            throw new InvalidBudgetItemException(s);
        }

        budgetItemDao.persist(budgetItem);
    }

    @Override
    public BudgetItem findBudgetItem(int id) throws BudgetItemNotFoundException {
        BudgetItem budgetItem = budgetItemDao.find(id);
        if (budgetItem == null) {
            throw new BudgetItemNotFoundException("id:" + id);
        }
        return budgetItem;
    }

    @Override
    public BudgetItem findBudgetItemByName(String name) throws BudgetItemNotFoundException {
        BudgetItem budgetItem = budgetItemDao.findByName(name);
        if (budgetItem == null) {
            throw new BudgetItemNotFoundException("name:" + name);
        }
        return budgetItem;
    }

    @Override
    public List<BudgetItem> findBudgetItemsByDesc(String description) throws BudgetItemNotFoundException {
        List<BudgetItem> list = budgetItemDao.findByDesc(description);
        if (list == null || list.isEmpty()) {
            throw new BudgetItemNotFoundException("description:" + description);
        }
        return list;
    }

    @Override
    public List<BudgetItem> findBudgetItemsByProject(Project project) throws BudgetItemNotFoundException {
        List<BudgetItem> list = budgetItemDao.findByProject(project);
        if (list == null || list.isEmpty()) {
            throw new BudgetItemNotFoundException("project:" + project.getTitle());
        }
        return list;
    }

}
