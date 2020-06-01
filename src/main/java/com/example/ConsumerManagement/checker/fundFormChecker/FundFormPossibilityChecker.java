package com.example.ConsumerManagement.checker.fundFormChecker;

import com.example.ConsumerManagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class FundFormPossibilityChecker extends FundFormChecker {

    @Autowired
    UserService userService;

    @Override
    public boolean satisfy() {
        return userService.findById(fund.getOwner()).isPresent() && fund.getBalance() >= 0 && super.satisfy();
    }
}
