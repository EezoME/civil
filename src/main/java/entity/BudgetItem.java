package entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by User on 02.03.2016.
 */
@Entity
@NamedQuery(name = "BudgetItem.getAll", query = "SELECT BudgetItems from BudgetItem BudgetItems")
public class BudgetItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int budgetItemId;

    @NotNull
    @Size(min = 2, max = 64)
    private String name;

    @Size(max = 128)
    private String desc;

    @NotNull
    @Min(1)
    private int cost;

    @ManyToOne
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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
