package com.example.ConsumerManagement.checker.transactionChecker;

import com.example.ConsumerManagement.checker.CheckerDecorator;

public class TransactionChecker extends CheckerDecorator {
    Integer transactionId;

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }
}
