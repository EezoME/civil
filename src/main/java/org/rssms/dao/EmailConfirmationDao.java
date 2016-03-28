package org.rssms.dao;

import org.rssms.dao.interfaces.EmailConfirmationdaoInterface;
import org.rssms.entity.EmailConfirmation;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Created by WRKSPACE2 on 3/12/2016.
 */
@Stateless
public class EmailConfirmationDao extends AbstractJpaDao<EmailConfirmation> implements EmailConfirmationdaoInterface {


//    public EmailConfirmationDao(Class<EmailConfirmation> entityClass) {
//        super(entityClass);
//    }

    @Override
    public EmailConfirmation getByUsername(String username) {
        return (EmailConfirmation) getEntityManager().createQuery("select ec from EmailConfirmation ec where ec.username=:username").setParameter("username", username).getSingleResult();
    }

    @Override
    public EmailConfirmation getByConfirmationCode(String confirmationCode) {
        return (EmailConfirmation) getEntityManager().createQuery("select ec from EmailConfirmation ec where ec.confirmationCode=:confirmationCode").setParameter("confirmationCode", confirmationCode).getSingleResult();
    }
}
