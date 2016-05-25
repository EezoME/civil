package org.rssms.entity;


import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.rssms.enums.Category;
import org.rssms.enums.Status;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by User on 01.03.2016.
 */
@Entity
@Table(name = "Projects")
@NamedQuery(name = "Project.getAll", query = "SELECT Projects from Project Projects")
public class Project implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "projectId")
    private int projectId;

    @NotNull
    @Size(min = 5, max = 64)
    @Column(name = "title", nullable = false, length = 64)
    private String title;

    @Column(name = "avatar")
    private byte[] avatar;

    @NotNull
    @Size(min = 15)
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @Column(name = "category", nullable = false)
    private Category category;

    @Past
    @Temporal(TemporalType.DATE)
    @Column(name = "registrationDate")
    private Date registrationDate;

    @Future
    @Temporal(TemporalType.DATE)
    @Column(name = "expirationDate")
    private Date expirationDate;

    @NotNull
    @Min(1)
    @Column(name = "goalCost")
    private int goalCost;

    @Column(name = "fundedSum")
    private double fundedSum;

    @Column(name = "status")
    private Status status;

    //@NotNull
    @Column(name = "privilegedStatus")
    private boolean privilegedStatus;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "project")  // Если Project удален - удаляем все его BudgetItems
    @JoinColumn(name = "project")
    private List<BudgetItem> budgetItems;

    @ManyToOne
    @JoinColumn(name = "creator")
    private User creator;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "project")
    @JoinColumn(name = "project")
    private List<Comment> comments;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "project")
    @JoinColumn(name = "project")
    private List<Donation> donations;

    public String getAvatar() {
        return Base64.encode(avatar);
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Donation> getDonations() {
        return donations;
    }

    public void setDonations(List<Donation> donations) {
        this.donations = donations;
    }

    public List<BudgetItem> getBudgetItems() {
        return budgetItems;
    }

    public void setBudgetItems(List<BudgetItem> budgetItems) {
        this.budgetItems = budgetItems;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getGoalCost() {
        return goalCost;
    }

    public void setGoalCost(int goalCost) {
        this.goalCost = goalCost;
    }

    public double getFundedSum() {
        return fundedSum;
    }

    public void setFundedSum(double fundedSum) {
        this.fundedSum = fundedSum;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isPrivilegedStatus() {
        return privilegedStatus;
    }

    public void setPrivilegedStatus(boolean privilegedStatus) {
        this.privilegedStatus = privilegedStatus;
    }


}
