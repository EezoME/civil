package dao;

import entity.Comment;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by User on 02.03.2016.
 */
public class CommentDao extends AbstractDAO<Comment> {
    private EntityManager em = Persistence.createEntityManagerFactory("civil").createEntityManager();

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
