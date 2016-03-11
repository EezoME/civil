package org.rssms.dao;

import org.rssms.entity.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by User on 02.03.2016.
 */
public class CommentDao extends AbstractDAO<Comment> {

    public CommentDao(Class<Comment> entityClass) {
        super(entityClass);
    }

    public List<Comment> getAll() {
        return namedQuery("Comment.getAll").getResultList();
    }
}
