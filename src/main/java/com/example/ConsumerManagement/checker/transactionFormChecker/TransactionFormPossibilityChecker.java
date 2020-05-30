package com.example.ConsumerManagement.checker.transactionFormChecker;

import com.example.ConsumerManagement.models.persistence.entities.Fund;
import com.example.ConsumerManagement.services.FundService;
import com.example.ConsumerManagement.services.UserFundService;
import com.example.ConsumerManagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class TransactionFormPossibilityChecker extends TransactionFormChecker {
    @Autowired
    FundService fundService;

    @Autowired
    UserService userService;

    @Autowired
    UserFundService userFundService;

    @Override
    public boolean satisfy() {
        Fund fund = fundService.findById(transaction.getFundId()).get();
        return transaction.getAmountOfMoney() <= fund.getBalance() &&
                userService.findById(transaction.getActor()).isPresent() &&
                userInFund(transaction.getActor(), transaction.getFundId()) && super.satisfy();
    }

    private boolean userInFund(String username, int fundId){
        for (String name: userFundService.findAllMembersByFundId(fundId)){
            if (name.equals(username)) return true;
        }
        return false;
    }
}
