package org.rssms.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by User on 02.03.2016.
 */
@Entity
@Table(name = "BudgetItems")
@NamedQuery(name = "BudgetItem.getAll", query = "SELECT BudgetItems from BudgetItem BudgetItems")
public class BudgetItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "budgetItemId")
//    @Column(name = "budgetItemId", nullable = false)
    private int budgetItemId;

    @NotNull
    @Size(min = 2, max = 64)
    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @Size(max = 128)
    @Column(name = "description", nullable = true, length = 128)
    private String description;

    @NotNull
    @Min(1)
    @Column(name = "cost", nullable = false)
    private int cost;

    @ManyToOne
    @JoinColumn(name = "project", nullable = false)
    private Project project;

    public int getBudgetItemId() {
        return budgetItemId;
    }

    public void setBudgetItemId(int budgetItemId) {
        this.budgetItemId = budgetItemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
