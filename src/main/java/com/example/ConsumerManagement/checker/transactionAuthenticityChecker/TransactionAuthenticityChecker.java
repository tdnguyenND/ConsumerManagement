package com.example.ConsumerManagement.checker.transactionAuthenticityChecker;

import com.example.ConsumerManagement.checker.CheckerDecorator;

public abstract class TransactionAuthenticityChecker extends CheckerDecorator {
    protected int fundId;
    protected int transactionId;

    public int getFundId() {
        return fundId;
    }

    public void setFundId(int fundId) {
        this.fundId = fundId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }
}
