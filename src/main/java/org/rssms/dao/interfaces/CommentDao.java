package org.rssms.dao.interfaces;

import org.rssms.entity.Comment;
import org.rssms.entity.Project;
import org.rssms.entity.User;

import java.util.Date;
import java.util.List;

/**
 * Created by User on 12.03.2016.
 */
public interface CommentDao extends GenericDao<Comment> {
    List<Comment> findByAutor(User author);

    List<Comment> findByProject(Project project);

    List<Comment> findByTimePosted(Date time);

    List<Comment> findByContent(String content);
}
