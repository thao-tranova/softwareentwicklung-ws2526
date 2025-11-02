package de.othr.persistence.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Address implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "house_number")
    private String number;
    private String street;
    private String ZPL;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZPL() {
        return ZPL;
    }

    public void setZPL(String ZPL) {
        this.ZPL = ZPL;
    }
}
