package fr.eservices.drive.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class StatusHistory {
    @Id
    @GeneratedValue
    private int id;
    @Temporal(value= TemporalType.TIMESTAMP)
    private Date statusDate;
    private Status status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }
}
