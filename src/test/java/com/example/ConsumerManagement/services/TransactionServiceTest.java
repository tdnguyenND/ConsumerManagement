package com.example.ConsumerManagement.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TransactionServiceTest {

    @Autowired
    TransactionService transactionService;

    private static ObjectMapper mapper;

    @BeforeAll
    static void init(){
        mapper = new ObjectMapper();
        mapper.registerModule(new Jdk8Module());
    }

    @Test
    void save() {
    }

    @Test
    void findById() {
    }

    @Test
    void deleteByFundId() {
    }

    @Test
    void findAllByFundId() {
    }
}