package org.rssms.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import java.io.Serializable;

/**
 * Created by WRKSPACE2 on 3/12/2016.
 */

@Entity
public class EmailConfirmation implements Serializable {

    @Id
    private int id;

    private String username;

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
