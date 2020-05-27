package com.example.ConsumerManagement.checker.fundAuthenticityChecker;

import com.example.ConsumerManagement.services.UserFundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FundMemberAuthenticityChecker extends FundAuthenticityChecker {
    @Autowired
    UserFundService userFundService;

    @Override
    public boolean satisfy() {
        //impl
        return super.satisfy();
    }
}
