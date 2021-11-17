package fr.eservices.drive.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


@Entity
public class Product extends Article {
    public Product() {
        super();
    }
}
