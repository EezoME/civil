package org.rssms.dao;

import org.rssms.dao.interfaces.CommentDaoInterface;
import org.rssms.entity.Comment;
import org.rssms.entity.Project;
import org.rssms.entity.User;

import java.util.Date;
import java.util.List;

/**
 * Created by User on 02.03.2016.
 */
public class CommentDao extends AbstractJpaDao<Comment> implements CommentDaoInterface {

    public CommentDao(Class<Comment> entityClass) {
        super(entityClass);
    }

    public List<Comment> getAll() {
        return namedQuery("Comment.getAll").getResultList();
    }

    @Override
    public List<Comment> findByAutor(User author) {
        return getEntityManager().createQuery("select c from Comment c where c.author=:author").setParameter("author", author).getResultList();
    }

    @Override
    public List<Comment> findByProject(Project project) {
        return getEntityManager().createQuery("select c from Comment c where c.project=:project").setParameter("project", project).getResultList();

    }

    @Override
    public List<Comment> findByTimePosted(Date time) {
        return getEntityManager().createQuery("select c from Comment c where c.timePosted=:time").setParameter("time", time).getResultList();

    }

    @Override
    public List<Comment> findByContent(String content) {
        return getEntityManager().createQuery("select c from Comment c where c.content LIKE :content").setParameter("content", "%" + content + "%").getResultList();
    }
}
