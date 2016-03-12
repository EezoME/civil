package org.rssms.dao.interfaces;

import org.rssms.entity.PrivilegedUser;

/**
 * Created by User on 12.03.2016.
 */
public interface PrivilegedUserDaoInterface extends GenericDao<PrivilegedUser> {
    PrivilegedUser getByPost(String post);
}
