package com.example.ConsumerManagement.services;


import com.example.ConsumerManagement.models.persistence.entities.Account;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AccountServiceTest {

    @Autowired
    AccountService accountService;

    private static ObjectMapper mapper;

    @BeforeAll
    static void init(){
        mapper = new ObjectMapper();
        mapper.registerModule(new Jdk8Module());
    }

    @Test
    public void findById() throws JsonProcessingException {
        Optional<Account> optional = accountService.findById("tdnguyen.uet");
        assertEquals(mapper.writeValueAsString(optional),
                "{\"username\":\"tdnguyen.uet\",\"password\":\"12345678\"}");
    }

    @Test
    public void findById1() throws JsonProcessingException {
        Optional<Account> optional = accountService.findById("user10");
        assertEquals(null,mapper.writeValueAsString(optional));
    }


}
