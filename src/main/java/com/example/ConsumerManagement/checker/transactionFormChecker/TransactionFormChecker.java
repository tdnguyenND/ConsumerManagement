package com.example.ConsumerManagement.checker.transactionFormChecker;

import com.example.ConsumerManagement.checker.CheckerDecorator;
import com.example.ConsumerManagement.models.persistence.entities.Transaction;

public abstract class TransactionFormChecker extends CheckerDecorator {
    protected Transaction transaction;

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
