package org.rssms.dao;

import org.rssms.dao.interfaces.DonationDao;
import org.rssms.entity.Donation;
import org.rssms.entity.Project;
import org.rssms.entity.User;

import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by User on 02.03.2016.
 */
@Stateless
public class DonationDaoImpl extends AbstractJpaDao<Donation> implements DonationDao {

    public List<Donation> getAll() {
        return namedQuery("Donation.getAll").getResultList();
    }

    @Override
    public List<Donation> getByAmount(int amount) {
        return getEntityManager().createQuery("select d from Donation d where d.amount=:amount").setParameter("amount", amount).getResultList();
    }

    @Override
    public List<Donation> getByComment(String comment) {
        return getEntityManager().createQuery("select d from Donation d where d.comment LIKE :comment").setParameter("comment", "%" + comment + "%").getResultList();

    }

    @Override
    public List<Donation> getByUser(User user) {
        return getEntityManager().createQuery("select d from Donation d where d.user=:user").setParameter("user", user).getResultList();

    }

    @Override
    public List<Donation> getByProject(Project project) {
        return getEntityManager().createQuery("select d from Donation d where d.project=:project").setParameter("project", project).getResultList();
    }
}
