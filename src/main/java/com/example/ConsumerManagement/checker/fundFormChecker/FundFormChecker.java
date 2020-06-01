package com.example.ConsumerManagement.checker.fundFormChecker;

import com.example.ConsumerManagement.checker.CheckerDecorator;
import com.example.ConsumerManagement.models.persistence.entities.Fund;

public abstract class FundFormChecker extends CheckerDecorator {
    protected Fund fund;

    public void setFund(Fund fund) {
        this.fund = fund;
    }

    public Fund getFund() {
        return fund;
    }
}
