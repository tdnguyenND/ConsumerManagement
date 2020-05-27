package com.example.ConsumerManagement.checker.transactionFormChecker;

import com.example.ConsumerManagement.models.persistence.entities.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@Component
@Sql("/data.sql")
class TransactionFormPossibilityCheckerTest {
    @Autowired
    TransactionFormPossibilityChecker transactionFormPossibilityChecker;

    Transaction transaction;

    @BeforeEach
    void init(){
        transaction = new Transaction();
        transactionFormPossibilityChecker.setTransaction(transaction);

        transaction.setNote("a");
        transaction.setAmountOfMoney(10);
        transaction.setActor("tdnguyen.uet");
        transaction.setType("reduce");
        transaction.setName("ac");
        transaction.setFundId(1);
        transaction.setDateOfCreation("2020-04-04");
    }

    @Test
    void payMoreMoneyThanFundHas(){
        transaction.setType("reduce");
        transaction.setAmountOfMoney(10000000);

        assertTrue(transactionFormPossibilityChecker.satisfy());
    }

    @Test
    void actorIsNotAvailable(){
        transaction.setActor("x");

        assertFalse(transactionFormPossibilityChecker.satisfy());
    }

    @Test
    void actorIsNotInFund(){
        transaction.setActor("user3");
        transaction.setFundId(4);

        assertFalse(transactionFormPossibilityChecker.satisfy());
    }
}