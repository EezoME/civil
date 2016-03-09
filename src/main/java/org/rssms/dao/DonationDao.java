package org.rssms.dao;

import org.rssms.entity.Donation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by User on 02.03.2016.
 */
public class DonationDao extends AbstractDAO<Donation> {
    @PersistenceContext(unitName = "civil")
    private EntityManager em;

    public DonationDao(Class<Donation> entityClass) {
        super(entityClass);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<Donation> getAll() {
        return namedQuery("Donation.getAll").getResultList();
    }
}
