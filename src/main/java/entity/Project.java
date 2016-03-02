package entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by User on 01.03.2016.
 */
@Entity
@NamedQuery(name = "Project.getAll", query = "SELECT Projects from Project Projects")
public class Project implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int projectId;
    private String title;
    private String desc;
    private String category;
    private Date registrationDate;
    private Date expirationDate;
    private int goalCost;
    private int fundedSum;
    private String status;
    private boolean privilegedStatus;
    @ManyToOne
    private User creator;    //????????????????????????
    @ManyToOne
    private User donator;      //????????????????????????

    public User getDonator() {
        return donator;
    }

    public void setDonator(User donator) {
        this.donator = donator;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isPrivilegedStatus() {
        return privilegedStatus;
    }

    public void setPrivilegedStatus(boolean privilegedStatus) {
        this.privilegedStatus = privilegedStatus;
    }
}
