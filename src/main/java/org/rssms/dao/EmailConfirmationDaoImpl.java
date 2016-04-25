package org.rssms.dao;

import org.rssms.dao.interfaces.EmailConfirmationDao;
import org.rssms.entity.EmailConfirmation;

import javax.ejb.Stateless;

/**
 * Created by WRKSPACE2 on 3/12/2016.
 */
@Stateless
public class EmailConfirmationDaoImpl extends AbstractJpaDao<EmailConfirmation> implements EmailConfirmationDao {

    @Override
    public EmailConfirmation getByUsername(String username) {
        return (EmailConfirmation) getEntityManager().createQuery("select ec from EmailConfirmation ec where ec.username=:username").setParameter("username", username).getSingleResult();
    }

    @Override
    public EmailConfirmation getByConfirmationCode(String confirmationCode) {
        return (EmailConfirmation) getEntityManager().createQuery("select ec from EmailConfirmation ec where ec.confirmationCode=:confirmationCode").setParameter("confirmationCode", confirmationCode).getSingleResult();
    }
}
