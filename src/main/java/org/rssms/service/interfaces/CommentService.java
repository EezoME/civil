package org.rssms.service.interfaces;

import org.rssms.entity.Comment;
import org.rssms.entity.Project;
import org.rssms.entity.User;
import org.rssms.exception.CommentNotFoundException;
import org.rssms.exception.InvalidCommentException;

import java.util.Date;
import java.util.List;

/**
 * Created by Eezo on 13.04.2016.
 */
public interface CommentService {

    void addComment(Comment comment) throws InvalidCommentException;

    void removeComment(int commentId);

    void updateComment(Comment comment) throws InvalidCommentException;

    Comment findComment(int id) throws CommentNotFoundException;

    List<Comment> findCommentsByUser(User user) throws CommentNotFoundException;

    List<Comment> findCommentsByProject(Project project) throws CommentNotFoundException;

    List<Comment> findCommentsByDate(Date date) throws CommentNotFoundException;

    /**
     * Returns a list of comments that contain the entry of this string in its content.
     *
     * @param content this string
     * @return a list of comments
     * @throws CommentNotFoundException
     */
    List<Comment> findCommentsByContent(String content) throws CommentNotFoundException;

}
