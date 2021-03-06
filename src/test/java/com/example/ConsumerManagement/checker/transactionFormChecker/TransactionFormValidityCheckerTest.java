package com.example.ConsumerManagement.checker.transactionFormChecker;

import com.example.ConsumerManagement.models.persistence.entities.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TransactionFormValidityCheckerTest {
    @Autowired
    TransactionFormValidityChecker transactionFormValidityChecker;

    Transaction transaction;

    @BeforeEach
    void init(){
        transaction = new Transaction();
        transactionFormValidityChecker.setTransaction(transaction);

        transaction.setNote("a");
        transaction.setAmountOfMoney(10.0);
        transaction.setActor("tdnguyen.uet");
        transaction.setType("reduce");
        transaction.setName("ac");
        transaction.setFundId(1);
        transaction.setDateOfCreation("2020-04-04");
    }

    @Test
    void noteIsNull(){
        transaction.setNote(null);
        assertTrue(transactionFormValidityChecker.satisfy());
    }

    @Test
    void actorIsNull(){
        transaction.setActor(null);
        assertFalse(transactionFormValidityChecker.satisfy());
    }

    @Test
    void amountOfMoneyIsNull(){
        transaction.setAmountOfMoney(null);
        assertFalse(transactionFormValidityChecker.satisfy());
    }

    @Test
    void typeIsNull(){
        transaction.setType(null);
        assertFalse(transactionFormValidityChecker.satisfy());
    }

    @Test
    void nameIsNull(){
        transaction.setName(null);
        assertFalse(transactionFormValidityChecker.satisfy());
    }

    @Test
    void dateIsNull(){
        transaction.setDateOfCreation(null);
        assertFalse(transactionFormValidityChecker.satisfy());
    }
}