package org.rssms.service;

import org.rssms.dao.interfaces.DonationDaoInterface;
import org.rssms.entity.Donation;
import org.rssms.entity.Project;
import org.rssms.entity.User;
import org.rssms.exception.InvalidDonationException;
import org.rssms.service.interfaces.DonationService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by User on 16.03.2016.
 */
@Stateless
public class DonationServiceBean extends AbstractService<Donation> implements DonationService {

    private DonationDaoInterface donationDao;

    @EJB
    public void setDonationDao(DonationDaoInterface donationDao) {
        this.donationDao = donationDao;
    }


    @Override
    public void createDonation(User user, int amount, String comment, Project project) throws InvalidDonationException {
        Donation donation = new Donation();
        donation.setUser(user);
        donation.setAmount(amount);
        donation.setComment(comment);
        donation.setProject(project);
        String s = validateEntity(donation);
        if (s != null){
            throw new InvalidDonationException(s);
        }
        donationDao.persist(donation);
    }


    @Override
    public Donation findDonation(int id) {
        return donationDao.find(id);
    }

    @Override
    public List<Donation> findDonations(int amount) {
        return donationDao.getByAmount(amount);
    }

    @Override
    public List<Donation> findDonations(User user) {
        return donationDao.getByUser(user);
    }

    @Override
    public List<Donation> findDonations(Project project) {
        return donationDao.getByProject(project);
    }
}
