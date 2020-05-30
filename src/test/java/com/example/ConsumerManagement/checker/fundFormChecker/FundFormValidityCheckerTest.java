package com.example.ConsumerManagement.checker.fundFormChecker;

import com.example.ConsumerManagement.models.persistence.entities.Fund;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FundFormValidityCheckerTest {
    @Autowired
    FundFormValidityChecker fundFormValidityChecker;

    Fund fund;

    @BeforeEach
    void init(){
        fund = new Fund();
        fundFormValidityChecker.setFund(fund);
        fund.setName("abd");
        fund.setBalance(1.0);
        fund.setOwner("tdnguyen.uet");
        fund.setDateOfCreation("2050-03-01");
    }

    @Test
    void allThingFine(){
        assertTrue(fundFormValidityChecker.satisfy());
    }

    @Test
    void nameIsNull(){
        fund.setName(null);
        assertFalse(fundFormValidityChecker.satisfy());
    }

    @Test
    void balanceIsNull(){
        fund.setBalance(null);
        assertFalse(fundFormValidityChecker.satisfy());
    }

    @Test
    void ownerIsNull(){
        fund.setOwner(null);
        assertFalse(fundFormValidityChecker.satisfy());
    }

    @Test
    void dateIsNull(){
        fund.setDateOfCreation(null);
        assertFalse(fundFormValidityChecker.satisfy());
    }
}