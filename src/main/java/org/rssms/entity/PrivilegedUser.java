package org.rssms.entity;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;

/**
 * Created by User on 02.03.2016.
 */
@Entity
@NamedQuery(name = "PrivilegedUser.getAll", query = "SELECT PrivilegedUsers from PrivilegedUser PrivilegedUsers")
public class PrivilegedUser extends User {
    private String post;

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }
}
