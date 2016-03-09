package org.rssms.dao;

import org.rssms.entity.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by User on 02.03.2016.
 */
public class CommentDao extends AbstractDAO<Comment> {
    @PersistenceContext(unitName = "civil")
    private EntityManager em;

    public CommentDao(Class<Comment> entityClass) {
        super(entityClass);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<Comment> getAll() {
        return namedQuery("Comment.getAll").getResultList();
    }
}
