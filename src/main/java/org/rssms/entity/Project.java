package org.rssms.entity;


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
@NamedQuery(name = "Project.getAll", query = "SELECT Projects from Project Projects")
public class Project implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int projectId;

    @NotNull
    @Size(min = 5, max = 64)
    private String title;

    @NotNull
    @Size(min = 15)
    private String desc;

    @NotNull
    private Category category;

    @Past
    @Temporal(TemporalType.DATE)
    private Date registrationDate;

    @Future
    @Temporal(TemporalType.DATE)
    private Date expirationDate;

    @NotNull
    @Min(1)
    private int goalCost;

    private int fundedSum;
    private Status status;

    @NotNull
    private boolean privilegedStatus;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "project")  // Если Project удален - удаляем все его BudgetItems
    private List<BudgetItem> budgetItems;

    @ManyToOne
    private User creator;

    @ManyToMany
    private List<User> team;    // Один проект - один или несколько создателей (команда)

    @ManyToMany
    private List<User> donors;   // Один проект - несколько доноров

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "project")
    private List<Comment> comments;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "project")
    private List<Donation> donations;


    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public List<User> getTeam() {
        return team;
    }

    public void setTeam(List<User> team) {
        this.team = team;
    }

    public List<User> getDonors() {
        return donors;
    }

    public void setDonors(List<User> donors) {
        this.donors = donors;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    public int getFundedSum() {
        return fundedSum;
    }

    public void setFundedSum(int fundedSum) {
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
