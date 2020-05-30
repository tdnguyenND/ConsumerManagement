package com.example.ConsumerManagement.checker.fundChecker;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql("/data.sql")
class FundExistCheckerTest {

    @Autowired
    FundExistChecker fundExistChecker;

    @Test
    void satisfy() {
        fundExistChecker.setFundId(1);
        assertTrue(fundExistChecker.satisfy());

        fundExistChecker.setFundId(-1);
        assertFalse(fundExistChecker.satisfy());
    }
}