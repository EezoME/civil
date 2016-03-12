package org.rssms.dao.interfaces;

import org.rssms.entity.BudgetItem;
import org.rssms.entity.Project;

import java.util.List;

/**
 * Created by User on 12.03.2016.
 */
public interface BudgetItemDaoInterface extends GenericDao<BudgetItem> {
    List<BudgetItem> findByDesc(String desc);

    List<BudgetItem> findByProject(Project project);

    List<BudgetItem> findByName(String name);


}
