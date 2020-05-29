package com.example.ConsumerManagement.checker.transactionFormChecker;

import org.springframework.stereotype.Component;

@Component
public class TransactionFormValidityChecker extends TransactionFormChecker {

    @Override
    public boolean satisfy() {
        return transaction.getFundId() != null && transaction.getActor() != null &&
                transaction.getAmountOfMoney() != null && transaction.getType() != null &&
                transaction.getName() != null && transaction.getDateOfCreation() != null;
    }
}
