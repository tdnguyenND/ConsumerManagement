package com.example.ConsumerManagement.checker.transactionChecker;

import com.example.ConsumerManagement.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class TransactionExistChecker extends TransactionChecker {
    @Autowired
    TransactionService transactionService;

    @Override
    public boolean satisfy() {
        return transactionService.existById(transactionId);
    }
}
