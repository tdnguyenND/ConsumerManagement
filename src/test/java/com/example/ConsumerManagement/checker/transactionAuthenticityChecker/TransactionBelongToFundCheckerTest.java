package com.example.ConsumerManagement.checker.transactionAuthenticityChecker;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql("/data.sql")
class TransactionBelongToFundCheckerTest {
    @Autowired
    TransactionBelongToFundChecker transactionBelongToFundChecker;

    @Test
    void satisfy() {
        transactionBelongToFundChecker.setFundId(1);

        transactionBelongToFundChecker.setTransactionId(1);
        assertTrue(transactionBelongToFundChecker.satisfy());

        transactionBelongToFundChecker.setTransactionId(4);
        assertFalse(transactionBelongToFundChecker.satisfy());
    }
}