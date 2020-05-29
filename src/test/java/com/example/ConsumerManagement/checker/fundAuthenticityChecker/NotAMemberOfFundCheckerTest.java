package com.example.ConsumerManagement.checker.fundAuthenticityChecker;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql("/data.sql")
class NotAMemberOfFundCheckerTest {

    @Autowired
    NotAMemberOfFundChecker notAMemberOfFundChecker;

    @Test
    void satisfy() {

        notAMemberOfFundChecker.setUsername("tdnguyen.uet");

        notAMemberOfFundChecker.setFundId(1);
        assertFalse(notAMemberOfFundChecker.satisfy());

        notAMemberOfFundChecker.setFundId(3);
        assertTrue(notAMemberOfFundChecker.satisfy());
    }
}