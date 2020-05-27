package com.example.ConsumerManagement.checker.transactionAuthenticityChecker;

import com.example.ConsumerManagement.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionBelongToFundChecker extends TransactionAuthenticityChecker {

    @Autowired
    TransactionService transactionService;

    @Override
    public boolean satisfy() {
        //impl
        return super.satisfy();
    }
}
