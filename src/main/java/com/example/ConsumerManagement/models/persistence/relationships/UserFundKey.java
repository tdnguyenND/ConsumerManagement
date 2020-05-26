package com.example.ConsumerManagement.models.persistence.relationships;

import java.io.Serializable;

public class UserFundKey implements Serializable {
    private String username;
    private int fundId;

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
}
