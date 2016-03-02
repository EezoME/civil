package entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by User on 01.03.2016.
 */
@Entity
@NamedQuery(name = "Comment.getAll", query = "SELECT comments from Comment comments")
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int commentId;
    @ManyToOne
    private User author;       //??????????????????????
    @ManyToOne
    private Project project;  //??????????????????????
    private String content;
    private Date timePosted;


    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTimePosted() {
        return timePosted;
    }

    public void setTimePosted(Date timePosted) {
        this.timePosted = timePosted;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", author=" + author +
                ", content='" + content + '\'' +
                ", timePosted=" + timePosted +
                '}';
    }
}
