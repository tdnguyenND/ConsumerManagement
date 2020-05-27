package com.example.ConsumerManagement.checker.fundAuthenticityChecker;

import com.example.ConsumerManagement.services.FundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FundTreasurerAuthenticityChecker extends FundAuthenticityChecker {
    @Autowired
    FundService fundService;

    @Override
    public boolean satisfy() {
        //impl
        return super.satisfy();
    }
}
