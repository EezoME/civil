package dao;

import entity.Donation;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by User on 02.03.2016.
 */
public class DonationDao extends AbstractDAO<Donation> {
    private EntityManager em = Persistence.createEntityManagerFactory("civil").createEntityManager();

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
