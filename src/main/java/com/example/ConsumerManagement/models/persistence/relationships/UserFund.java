package com.example.ConsumerManagement.models.persistence.relationships;

import javax.persistence.*;

@Entity
@Table(name = "user_fund")
@IdClass(UserFundKey.class)
public class UserFund {
    @Id
    private String username;

    @Id
    @Column(name = "fund_id")
    private int fundId;

    @Column(name = "date_of_participant")
    private String dateOfParticipant;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getFundId() {
        return fundId;
    }

    public void setFundId(int fundId) {
        this.fundId = fundId;
    }

    public String getDateOfParticipant() {
        return dateOfParticipant;
    }

    public void setDateOfParticipant(String dateOfParticipant) {
        this.dateOfParticipant = dateOfParticipant;
    }
}
