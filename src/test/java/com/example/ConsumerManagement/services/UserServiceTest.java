package com.example.ConsumerManagement.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {
    @Autowired
    UserService userService;

    private static ObjectMapper mapper;

    @BeforeAll
    static void init(){
        mapper = new ObjectMapper();
        mapper.registerModule(new Jdk8Module());
    }

    @Test
    void findById() throws JsonProcessingException {
        assertEquals(mapper.writeValueAsString(userService.findById("tdnguyen.uet")),
                "");
    }

    @Test
    void save() {
    }

    @Test
    void isExist() {
    }
}