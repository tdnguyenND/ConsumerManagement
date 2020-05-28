package com.example.ConsumerManagement.services;

import com.example.ConsumerManagement.models.persistence.relationships.UserFund;
import com.example.ConsumerManagement.models.persistence.relationships.UserFundKey;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserFundServiceTest {

    @Autowired
    UserFundService userFundService;

    private static ObjectMapper mapper;

    @BeforeAll
    static void initAll(){
        mapper = new ObjectMapper();
        mapper.registerModule(new Jdk8Module());
    }


    @Test
    @Sql("/data.sql")
    void findAllMembersByFundId() throws JsonProcessingException {
        assertEquals(mapper.writeValueAsString(userFundService.findAllMembersByFundId(1)),
                "[\"tdnguyen.uet\",\"user1\",\"user3\"]");
    }

    @Test
    @Sql("/data.sql")
    void findAllFundIdByUsername() throws JsonProcessingException {
        assertEquals(mapper.writeValueAsString(userFundService.findAllFundIdByUsername("tdnguyen.uet")),
                "[1,2]");
    }

    @Test
    @Sql("/data.sql")
    void save() throws JsonProcessingException {
        UserFund userFund = new UserFund();
        userFund.setFundId(3);
        userFund.setUsername("tdnguyen.uet");
        userFund.setDateOfParticipant("2020-01-05");

        userFundService.save(userFund);

        assertEquals(mapper.writeValueAsString(userFundService.findAllFundIdByUsername("tdnguyen.uet")),
                "[1,2,3]");
    }

    @Test
    @Sql("/data.sql")
    void deleteById() throws JsonProcessingException {
        UserFundKey userFundKey = new UserFundKey();
        userFundKey.setFundId(1);
        userFundKey.setUsername("user3");

        userFundService.deleteById(userFundKey);

        assertEquals(mapper.writeValueAsString(userFundService.findAllFundIdByUsername("user3")),
                "[2]");
    }

    @Test
    @Sql("/data.sql")
    void deleteAllByFundId() throws JsonProcessingException {
        userFundService.deleteAllByFundId(1);
        assertEquals(mapper.writeValueAsString(userFundService.findAllMembersByFundId(1)),
                "[]");
    }
}