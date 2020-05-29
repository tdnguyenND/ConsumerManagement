package com.example.ConsumerManagement.checker.fundAuthenticityChecker;

import com.example.ConsumerManagement.checker.CheckerDecorator;

public abstract class FundAuthenticityChecker extends CheckerDecorator {
    protected int fundId;
    protected String username;

    public int getFundId() {
        return fundId;
    }

    public void setFundId(int fundId) {
        this.fundId = fundId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
