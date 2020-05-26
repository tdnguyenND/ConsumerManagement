package com.example.ConsumerManagement.models.persistence.entities;

import javax.persistence.*;

@Entity
@Table(name = "fund")
public class Fund {
    @Id
    @Column(name = "fund_id")
    private int fundId;

    private String name;
    private String owner;

    @Column(name = "date_of_creation")
    private String dateOfCreation;
    private double balance;

    public int getFundId() {
        return fundId;
    }

    public void setFundId(int fundId) {
        this.fundId = fundId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(String dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
