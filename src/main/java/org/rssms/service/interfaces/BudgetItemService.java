package org.rssms.service.interfaces;

import org.rssms.entity.BudgetItem;
import org.rssms.entity.Project;
import org.rssms.exception.BudgetItemNotFoundException;
import org.rssms.exception.InvalidBudgetItemException;

import java.util.List;

/**
 * Created by Eezo on 14.04.2016.
 */
public interface BudgetItemService {

    void addBudgetItem(BudgetItem budgetItem) throws InvalidBudgetItemException;

    void removeBudgetItem(int budgetItemId);

    void updateBudgetItem(BudgetItem budgetItem) throws InvalidBudgetItemException;

    BudgetItem findBudgetItem(int id) throws BudgetItemNotFoundException;

    BudgetItem findBudgetItemByName(String name) throws BudgetItemNotFoundException;

    List<BudgetItem> findBudgetItemsByDesc(String description) throws BudgetItemNotFoundException;

    List<BudgetItem> findBudgetItemsByProject(Project project) throws BudgetItemNotFoundException;

}
