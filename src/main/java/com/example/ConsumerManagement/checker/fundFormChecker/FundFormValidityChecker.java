package com.example.ConsumerManagement.checker.fundFormChecker;

import com.example.ConsumerManagement.checker.DateValidityChecker;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class FundFormValidityChecker extends FundFormChecker {

    @Override
    public boolean satisfy() {
        return fund.getOwner() != null && fund.getBalance() != null &&
                fund.getDateOfCreation() != null && DateValidityChecker.isValidDate(fund.getDateOfCreation()) &&
                fund.getName() != null & super.satisfy();
    }
}
