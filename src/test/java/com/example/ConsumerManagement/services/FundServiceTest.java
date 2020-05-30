package com.example.ConsumerManagement.services;

import com.example.ConsumerManagement.models.persistence.entities.Fund;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FundServiceTest {
    @Autowired
    private FundService fundService;

    private static ObjectMapper mapper;

    @BeforeAll
    static void initAll(){
        mapper = new ObjectMapper();
        mapper.registerModule(new Jdk8Module());
    }

    @Test
    @Sql("/data.sql")
    void findById() throws JsonProcessingException {
        assertEquals(mapper.writeValueAsString(fundService.findById(1)),
                "{\"fundId\":1,\"name\":\"p501\",\"owner\":\"tdnguyen.uet\",\"dateOfCreation\":\"2005-02-28\",\"balance\":1000000.0}");
        assertEquals(fundService.findById(-1), Optional.empty());
    }

    @Test
    @Sql("/data.sql")
    void deleteById() {
        fundService.deleteById(1);
        assertEquals(fundService.findById(1), Optional.empty());
    }

    @Test
    @Sql("/data.sql")
    void save() throws JsonProcessingException {
        String name = "fundName";
        String dateOfCreation = "2020-05-04";
        String owner = "tdnguyen.uet";
        double balance = 123.456;

        Fund fund = new Fund();
        fund.setName(name);
        fund.setDateOfCreation(dateOfCreation);
        fund.setOwner(owner);
        fund.setBalance(balance);

        fund = fundService.save(fund);
        int id = fund.getFundId();

        assertEquals(mapper.writeValueAsString(fundService.findById(id)),
                "{\"fundId\":" + id + ",\"name\":\"" + name + "\",\"owner\":\"" + owner
                        + "\",\"dateOfCreation\":\"" + dateOfCreation + "\",\"balance\":" + balance + "}");
    }

    @Test
    @Sql("/data.sql")
    void findOwnerByFundId() {
        assertEquals(fundService.findOwnerByFundId(1), "tdnguyen.uet");
    }

    @Test
    @Sql("/data.sql")
    void changeOwner() {
        String newOwner = "user1";
        fundService.changeOwner(1, newOwner);
        assertEquals(fundService.findOwnerByFundId(1), newOwner);
<<<<<<< HEAD
=======
    }

    @Test
    @Sql("/data.sql")
    void isExist() {
        assertTrue(fundService.isExist(1));
        assertFalse(fundService.isExist(-1));
>>>>>>> master
    }
}