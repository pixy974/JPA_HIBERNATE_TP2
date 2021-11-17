package fr.eservices.drive.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Order
{
    @Id
    private int id;
    @Temporal(value=TemporalType.TIMESTAMP)
    private Date createdOn;
    @Temporal(value=TemporalType.TIMESTAMP)
    private Date deliveryDate;
    private int amount;
    @ManyToMany
    private List<Article> articles = new ArrayList<>();
    @OneToMany
    private List<StatusHistory> history = new ArrayList<>();
    @ManyToOne
    private Customer customer;
    private Status currentStatus;

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
