package com.example.ConsumerManagement.checker.fundAuthenticityChecker;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql("/data.sql")
class FundTreasurerAuthenticityCheckerTest {
    @Autowired
    FundTreasurerAuthenticityChecker fundTreasurerAuthenticityChecker;

    @Test
    void satisfy() {
        fundTreasurerAuthenticityChecker.setUsername("tdnguyen.uet");

        fundTreasurerAuthenticityChecker.setFundId(1);
        assertTrue(fundTreasurerAuthenticityChecker.satisfy());

        fundTreasurerAuthenticityChecker.setFundId(3);
        assertFalse(fundTreasurerAuthenticityChecker.satisfy());
    }
}