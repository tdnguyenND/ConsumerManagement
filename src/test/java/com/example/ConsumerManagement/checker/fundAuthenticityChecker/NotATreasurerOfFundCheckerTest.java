package com.example.ConsumerManagement.checker.fundAuthenticityChecker;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql("/data.sql")
class NotATreasurerOfFundCheckerTest {

    @Autowired
    NotATreasurerOfFundChecker notATreasurerOfFundChecker;

    @Test
    void satisfy() {
        notATreasurerOfFundChecker.setUsername("tdnguyen.uet");

        notATreasurerOfFundChecker.setFundId(1);
        assertFalse(notATreasurerOfFundChecker.satisfy());

        notATreasurerOfFundChecker.setFundId(3);
        assertTrue(notATreasurerOfFundChecker.satisfy());
    }
}