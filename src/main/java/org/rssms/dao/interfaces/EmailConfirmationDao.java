package org.rssms.dao.interfaces;

import org.rssms.entity.EmailConfirmation;

/**
 * Created by User on 12.03.2016.
 */
public interface EmailConfirmationDao extends GenericDao<EmailConfirmation> {
    Object getByUsername(String username);

    EmailConfirmation getByConfirmationCode(String confirmationCode);
}
