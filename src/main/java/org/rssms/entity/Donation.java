package org.rssms.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by User on 01.03.2016.
 */
@Entity
@NamedQuery(name = "Donation.getAll", query = "SELECT donations from Donation donations")
public class Donation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int donationId;

    @NotNull
    @Min(1)
    private int amount;
    private String comment;

    @ManyToOne
    private User user;      //?????????????????????????

    @ManyToOne
    private Project project;   //?????????????????????????

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public int getDonationId() {
        return donationId;
    }

    public void setDonationId(int donationId) {
        this.donationId = donationId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Donation{" +
                "donationId=" + donationId +
                ", amount=" + amount +
                ", comment='" + comment + '\'' +
                '}';
    }
}
