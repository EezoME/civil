package org.rssms.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by User on 01.03.2016.
 */
@Entity
@Table(name = "Comments")
@NamedQuery(name = "Comment.getAll", query = "SELECT comments from Comment comments")
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "commentId", nullable = false)
    private int commentId;

    @ManyToOne
    @JoinColumn(name = "author", nullable = false)
    private User author;       //??????????????????????

    @ManyToOne
    @JoinColumn(name = "project", nullable = false)
    private Project project;  //??????????????????????

    @NotNull
    @Size(min = 5, max = 1024)
    @Column(name = "content", length = 1024, nullable = false)
    private String content;

    @Past
    @Temporal(TemporalType.DATE)
    @Column(name = "timePosted", nullable = false)
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
