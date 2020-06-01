package com.example.ConsumerManagement.checker.fundChecker;

import com.example.ConsumerManagement.checker.CheckerDecorator;

public class FundChecker extends CheckerDecorator {
    protected Integer fundId;

    public Integer getFundId() {
        return fundId;
    }

    public void setFundId(Integer fundId) {
        this.fundId = fundId;
    }
}
