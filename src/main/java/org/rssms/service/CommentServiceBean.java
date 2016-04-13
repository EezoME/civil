package org.rssms.service;

import org.rssms.dao.CommentDao;
import org.rssms.dao.interfaces.CommentDaoInterface;
import org.rssms.entity.Comment;
import org.rssms.entity.Project;
import org.rssms.entity.User;
import org.rssms.exception.CommentNotFoundException;
import org.rssms.exception.InvalidCommentException;
import org.rssms.service.interfaces.CommentService;

import javax.ejb.EJB;
import java.util.Date;
import java.util.List;

/**
 * Created by Eezo on 13.04.2016.
 */
public class CommentServiceBean extends AbstractService<Comment> implements CommentService {

    private CommentDaoInterface commentDao;

    @EJB
    public void setCommentDao(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Override
    public void addComment(Comment comment) throws InvalidCommentException {
        String s = validateEntity(comment);

        if (s != null) {
            throw new InvalidCommentException(s);
        }

        commentDao.persist(comment);
    }

    @Override
    public void removeComment(int commentId) {
        commentDao.remove(commentId);
    }

    @Override
    public void updateComment(Comment comment) throws InvalidCommentException {
        String s = validateEntity(comment);

        if (s != null) {
            throw new InvalidCommentException(s);
        }

        commentDao.persist(comment);
    }

    @Override
    public Comment findComment(int id) throws CommentNotFoundException {
        Comment comment = commentDao.find(id);
        if (comment == null) {
            throw new CommentNotFoundException("id:" + id);
        }
        return comment;
    }

    @Override
    public List<Comment> findCommentsByUser(User user) throws CommentNotFoundException {
        List<Comment> list = commentDao.findByAutor(user);
        if (list == null || list.isEmpty()) {
            throw new CommentNotFoundException("author:" + user.getUsername());
        }
        return list;
    }

    @Override
    public List<Comment> findCommentsByProject(Project project) throws CommentNotFoundException {
        List<Comment> list = commentDao.findByProject(project);
        if (list == null || list.isEmpty()) {
            throw new CommentNotFoundException("project:" + project.getTitle());
        }
        return list;
    }

    @Override
    public List<Comment> findCommentsByDate(Date date) throws CommentNotFoundException {
        List<Comment> list = commentDao.findByTimePosted(date);
        if (list == null || list.isEmpty()) {
            throw new CommentNotFoundException("date-time:" + date.toString());
        }
        return list;
    }

    @Override
    public List<Comment> findCommentsByContent(String content) throws CommentNotFoundException {
        List<Comment> list = commentDao.findByContent(content);
        if (list == null || list.isEmpty()) {
            throw new CommentNotFoundException("content:" + content);
        }
        return list;
    }
}
