package org.rssms.dao;

import org.rssms.entity.EmailConfirmation;

/**
 * Created by WRKSPACE2 on 3/12/2016.
 */
public class EmailConfirmationDao extends AbstractJpaDao<EmailConfirmation> {

    public EmailConfirmationDao(Class<EmailConfirmation> entityClass) {
        super(entityClass);
    }
}
