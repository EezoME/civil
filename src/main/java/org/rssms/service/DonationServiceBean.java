package org.rssms.service;

import org.rssms.dao.DonationDao;
import org.rssms.entity.Donation;
import org.rssms.entity.Project;
import org.rssms.entity.User;
import org.rssms.exception.InvalidDonationException;
import org.rssms.service.interfaces.DonationService;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

/**
 * Created by User on 16.03.2016.
 */
@Stateless
public class DonationServiceBean implements DonationService {

    @Resource
    Validator validator;
    private DonationDao donationDao;

    @Inject
    public void setDonationDao(DonationDao donationDao) {
        this.donationDao = donationDao;
    }


    @Override
    public void createDonation(User user, int amount, String comment, Project project) throws InvalidDonationException {
        Donation donation = new Donation();
        donation.setUser(user);
        donation.setAmount(amount);
        donation.setComment(comment);
        donation.setProject(project);
        Set<ConstraintViolation<Donation>> violationSet = validator.validate(donation);
        for (ConstraintViolation<Donation> violation : violationSet) {
            throw new InvalidDonationException(violation.getPropertyPath() + " " + violation.getMessage());
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
