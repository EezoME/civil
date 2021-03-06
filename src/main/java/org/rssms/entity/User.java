package org.rssms.entity;

import org.rssms.enums.Role;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by User on 01.03.2016.
 */
@Entity
@Table(name = "Users",
uniqueConstraints = @UniqueConstraint(columnNames = "USERNAME"))
@NamedQuery(name = "User.getAll", query = "SELECT users from User users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userId")
    private int userId;

    @NotNull
    @Size( min = 5 )
    @Column(name = "fullName")
    private String fullName;

    @Past
    @Temporal( TemporalType.DATE )
    @Column(name = "bDate")
    private Date bDate;

    @NotNull
    @Size( min = 3, max = 32 )
    @Column(name = "username", length = 32, unique = true)
    private String username;

    @NotNull
    @Column(name = "password")
    private String password;    // Если это хеш пароля то валидировать его исходное значение нужно где-то в другом месте

    @NotNull
    //@Pattern( regexp =
    //        "^[\\\\w!#$%&’*+/=?`{|}~^-]+(?:\\\\.[\\\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,6}$" )
    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany
    @JoinColumn(name = "creator")
    private List<Project> createdProjects;

    @OneToMany(mappedBy = "user")
    @JoinColumn(name = "donated_user")
    private List<Donation> donations;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "author")
    @JoinColumn(name = "author")
    private List<Comment> comments;

    public List<Project> getCreatedProjects() {
        return createdProjects;
    }

    public void setCreatedProjects(List<Project> createdProjects) {
        this.createdProjects = createdProjects;
    }

    public List<Donation> getDonations() {
        return donations;
    }

    public void setDonations(List<Donation> donations) {
        this.donations = donations;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getbDate() {
        return bDate;
    }

    public void setbDate(Date bDate) {
        this.bDate = bDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", fullName='" + fullName + '\'' +
                ", bDate='" + bDate + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
