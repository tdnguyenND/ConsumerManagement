package com.example.ConsumerManagement.checker.transactionFormChecker;

import com.example.ConsumerManagement.models.persistence.entities.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.junit.jupiter.api.Assertions.*;

@Component
class TransactionFormValidityCheckerTest {
    @Autowired
    TransactionFormValidityChecker transactionFormValidityChecker;

    Transaction transaction;

    @BeforeEach
    void init(){
        transaction = new Transaction();
        transactionFormValidityChecker.setTransaction(transaction);

        transaction.setNote("a");
        transaction.setAmountOfMoney(10);
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
    void amountOfMoneyIs0(){
        transaction.setAmountOfMoney(0);
        assertTrue(transactionFormValidityChecker.satisfy());
    }

    @Test
    void typeIsNull(){
        transaction.setType(null);
        assertFalse(transactionFormValidityChecker.satisfy());
    }

    @Test
    void nameIsNull(){
        transaction.setNote(null);
        assertFalse(transactionFormValidityChecker.satisfy());
    }

    @Test
    void dateIsNull(){
        transaction.setDateOfCreation(null);
        assertFalse(transactionFormValidityChecker.satisfy());
    }
}