package com.example.ConsumerManagement.checker.transactionAuthenticityChecker;

import com.example.ConsumerManagement.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class TransactionBelongToFundChecker extends TransactionAuthenticityChecker {

    @Autowired
    TransactionService transactionService;

    @Override
    public boolean satisfy() {
        int realFundId = transactionService.findById(transactionId).get().getFundId();
        return realFundId == fundId && super.satisfy();
    }
}
