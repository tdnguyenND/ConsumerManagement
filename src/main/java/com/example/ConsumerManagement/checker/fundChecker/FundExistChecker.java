package com.example.ConsumerManagement.checker.fundChecker;

import com.example.ConsumerManagement.services.FundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class FundExistChecker extends FundChecker {
    @Autowired
    FundService fundService;

    @Override
    public boolean satisfy() {
        return fundService.isExist(fundId) && super.satisfy();
    }
}
