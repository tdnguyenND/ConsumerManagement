package com.example.ConsumerManagement.models.persistence.entities;

import javax.persistence.*;

@Entity
@Table(name = "fund")
public class Fund {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fund_id", nullable = false)
    private Integer fundId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String owner;

    @Column(name = "date_of_creation", nullable = false)
    private String dateOfCreation;

    @Column(nullable = false)
    private Double balance;

    public Integer getFundId() {
        return fundId;
    }

    public void setFundId(Integer fundId) {
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

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
