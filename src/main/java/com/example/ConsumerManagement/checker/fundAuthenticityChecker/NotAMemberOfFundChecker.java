package com.example.ConsumerManagement.checker.fundAuthenticityChecker;

import com.example.ConsumerManagement.services.UserFundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class NotAMemberOfFundChecker extends FundAuthenticityChecker {
    @Autowired
    UserFundService userFundService;

    @Override
    public boolean satisfy() {
        return !isMember(this.username, this.fundId) && super.satisfy();
    }

    boolean isMember(String username, int fundId){
        for(String user: userFundService.findAllMembersByFundId(fundId)){
            if (user.equals(username)) return true;
        }
        return false;
    }
}
