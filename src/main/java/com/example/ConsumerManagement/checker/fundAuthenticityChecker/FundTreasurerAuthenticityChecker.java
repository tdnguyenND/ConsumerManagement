package com.example.ConsumerManagement.checker.fundAuthenticityChecker;

import com.example.ConsumerManagement.services.FundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class FundTreasurerAuthenticityChecker extends FundAuthenticityChecker {
    @Autowired
    FundService fundService;

    @Override
    public boolean satisfy() {
        String owner = fundService.findById(fundId).get().getOwner();
        return owner.equals(username) && super.satisfy();
    }
}
