package org.rssms.dao;

import org.rssms.dao.interfaces.PrivilegedUserDao;
import org.rssms.entity.PrivilegedUser;

import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by User on 02.03.2016.
 */
@Stateless
public class PrivilegedUserDaoImpl extends AbstractJpaDao<PrivilegedUser> implements PrivilegedUserDao {

    public List<PrivilegedUser> getAll() {
        return namedQuery("PrivilegedUser.getAll").getResultList();
    }

    @Override
    public PrivilegedUser getByPost(String post) {
        return (PrivilegedUser) getEntityManager().createQuery("select pu from PrivilegedUser pu where pu.post=:post").setParameter("post", post).getSingleResult();
    }
}

