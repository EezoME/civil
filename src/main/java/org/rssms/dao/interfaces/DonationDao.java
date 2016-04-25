package org.rssms.dao.interfaces;

import org.rssms.entity.Donation;
import org.rssms.entity.Project;
import org.rssms.entity.User;

import java.util.List;

/**
 * Created by User on 12.03.2016.
 */
public interface DonationDao extends GenericDao<Donation> {
    List<Donation> getByAmount(int amount);

    List<Donation> getByComment(String comment);

    List<Donation> getByUser(User user);

    List<Donation> getByProject(Project project);

}
