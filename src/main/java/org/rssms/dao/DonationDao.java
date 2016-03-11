package org.rssms.dao;

import org.rssms.entity.Donation;

import java.util.List;

/**
 * Created by User on 02.03.2016.
 */
public class DonationDao extends AbstractJpaDao<Donation> {


    public DonationDao(Class<Donation> entityClass) {
        super(entityClass);
    }


    public List<Donation> getAll() {
        return namedQuery("Donation.getAll").getResultList();
    }
}
