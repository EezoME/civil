package org.rssms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Created by User on 02.03.2016.
 */
@Entity
@Table(name = "PrivilegedUsers")
@NamedQuery(name = "PrivilegedUser.getAll", query = "SELECT PrivilegedUsers from PrivilegedUser PrivilegedUsers")
public class PrivilegedUser extends User {

    @NotNull
    @Column(name = "post")
    private String post;

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }
}
