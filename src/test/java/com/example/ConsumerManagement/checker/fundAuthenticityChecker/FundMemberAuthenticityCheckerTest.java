package com.example.ConsumerManagement.checker.fundAuthenticityChecker;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql("/data.sql")
class FundMemberAuthenticityCheckerTest {
    @Autowired
    FundMemberAuthenticityChecker fundMemberAuthenticityChecker;

    @Test
    void satisfy() {
        fundMemberAuthenticityChecker.setUsername("tdnguyen.uet");

        fundMemberAuthenticityChecker.setFundId(1);
        assertTrue(fundMemberAuthenticityChecker.satisfy());

        fundMemberAuthenticityChecker.setFundId(3);
        assertFalse(fundMemberAuthenticityChecker.satisfy());
    }
}