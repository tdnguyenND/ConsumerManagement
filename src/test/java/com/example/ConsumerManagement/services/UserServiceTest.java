package com.example.ConsumerManagement.services;

import com.example.ConsumerManagement.models.persistence.entities.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Nested;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

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
                "{\"username\":\"tdnguyen.uet\",\"firstName\":\"Triệu Đình\",\"lastName\":\"Nguyện\",\"password\":\"12345678\",\"phoneNumber\":\"0971219563\",\"gender\":\"male\",\"email\":\"tdnguyen.uet@gmail.com\"}");
    }


    @Test
    void isExist() {
        assertTrue(userService.isExist("user5"));
    }

    @Test
    void save() throws JsonProcessingException {
        User newUser = new User();
        newUser.setFirstName("user5FirstName");
        newUser.setLastName("user5LastName");
        newUser.setUsername("user5");
        newUser.setEmail("user5@gmail.com");
        newUser.setGender("female");
        newUser.setPhoneNumber("913873123");
        newUser.setPassword("admin1234");
        userService.save(newUser);
        assertEquals(mapper.writeValueAsString(userService.findById("user5")), "{\"username\":\"user5\",\"firstName\":\"user5FirstName\",\"lastName\":\"user5LastName\",\"password\":\"admin1234\",\"phoneNumber\":\"913873123\",\"gender\":\"female\",\"email\":\"user5@gmail.com\"}");
    }
}