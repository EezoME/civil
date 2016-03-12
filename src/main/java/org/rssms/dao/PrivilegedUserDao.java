package org.rssms.dao;

import org.rssms.dao.interfaces.PrivilegedUserDaoInterface;
import org.rssms.entity.PrivilegedUser;

import java.util.List;

/**
 * Created by User on 02.03.2016.
 */
public class PrivilegedUserDao extends AbstractJpaDao<PrivilegedUser> implements PrivilegedUserDaoInterface {

    public PrivilegedUserDao(Class<PrivilegedUser> entityClass) {
        super(entityClass);
    }

    public List<PrivilegedUser> getAll() {
        return namedQuery("PrivilegedUser.getAll").getResultList();
    }

    @Override
    public PrivilegedUser getByPost(String post) {
        return (PrivilegedUser) getEntityManager().createQuery("select pu from PrivilegedUser pu where pu.post=:post").setParameter("post", post).getSingleResult();
    }
}

