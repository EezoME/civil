package org.rssms.service.interfaces;

import org.rssms.entity.Donation;
import org.rssms.entity.Project;
import org.rssms.entity.User;
import org.rssms.exception.InvalidDonationException;

import java.util.List;

/**
 * Created by User on 16.03.2016.
 */
public interface DonationService {
    void createDonation(User user, int amount, String comment, Project project) throws InvalidDonationException;
    Donation findDonation(int id);

    List<Donation> findDonations(int amount);

    List<Donation> findDonations(User user);

    List<Donation> findDonations(Project project);

}
