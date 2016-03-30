package org.rssms.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by WRKSPACE2 on 3/12/2016.
 */

@Entity
@Table(name = "EmailConfirmations")
public class EmailConfirmation implements Serializable {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "confirmationCode")
    private String confirmationCode;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getConfirmationCode() {
        return confirmationCode;
    }

    public void setConfirmationCode(String confirmationCode) {
        this.confirmationCode = confirmationCode;
    }
}
