package com.example.ConsumerManagement.checker.fundFormChecker;

import com.example.ConsumerManagement.models.persistence.entities.Fund;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql("/data.sql")
class FundFormPossibilityCheckerTest {
    @Autowired
    FundFormPossibilityChecker checker;
    Fund fund;

    @BeforeEach
    void init(){
        fund = new Fund();
        checker.setFund(fund);

        fund.setName("abd");
        fund.setBalance(1.0);
        fund.setOwner("tdnguyen.uet");
        fund.setDateOfCreation("2050-03-01");
    }
    @Test
    void allThingsFine(){
        assertTrue(checker.satisfy());
    }

    @Test
    void usernameNotAvailable() {
        fund.setOwner("x");
        assertFalse(checker.satisfy());
    }

    @Test
    void balanceIsNegative(){
        fund.setBalance(-1.0);
        assertFalse(checker.satisfy());
    }
}