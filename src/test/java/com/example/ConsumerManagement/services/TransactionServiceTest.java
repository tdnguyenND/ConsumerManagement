package com.example.ConsumerManagement.services;

import com.example.ConsumerManagement.models.persistence.entities.Transaction;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TransactionServiceTest {

    @Autowired
    TransactionService transactionService;

    private static ObjectMapper mapper;

    @BeforeAll
    static void initAll(){
        mapper = new ObjectMapper();
        mapper.registerModule(new Jdk8Module());
    }

    @BeforeEach
    void init(){}

    @Nested
    class save{
        int fundId = 1;
        String name = "buy";
        String type = "reduce";
        String actor = "actor";
        double amountOfMoney = 132.345;
        String dateOfCreation = "2005-05-05";
        Transaction transaction = new Transaction();

        @BeforeEach
        @Sql("/data.sql")
        void init(){
            transaction.setFundId(fundId);
            transaction.setName(name);
            transaction.setType(type);
            transaction.setActor(actor);
            transaction.setAmountOfMoney(amountOfMoney);
            transaction.setDateOfCreation(dateOfCreation);
        }

        @Test
        void noteNotNull() throws JsonProcessingException {
            String note = "fa";
            transaction.setNote(note);

            transaction = transactionService.save(transaction);
            int id = transaction.getTransactionId();

            assertEquals(mapper.writeValueAsString(transactionService.findById(id)),
                    "{\"transactionId\":" + id + ",\"fundId\":" + fundId + ",\"name\":\""
                            + name + "\",\"type\":\"" + type + "\",\"actor\":\"" + actor + "\",\"amountOfMoney\":"
                            + amountOfMoney + ",\"note\":\"" + note + "\",\"dateOfCreation\":\""
                            + dateOfCreation +"\"}");
        }

        @Test
        void noteIsNull() throws JsonProcessingException {
            transaction.setNote(null);

            transaction = transactionService.save(transaction);
            int id = transaction.getTransactionId();

            assertEquals(mapper.writeValueAsString(transactionService.findById(id)),
                    "{\"transactionId\":" + id + ",\"fundId\":" + fundId + ",\"name\":\""
                            + name + "\",\"type\":\"" + type + "\",\"actor\":\"" + actor + "\",\"amountOfMoney\":"
                            + amountOfMoney + ",\"note\":" + null + ",\"dateOfCreation\":\""
                            + dateOfCreation +"\"}");

        }

    }

    @Test
    @Sql("/data.sql")
    void findById() throws JsonProcessingException {
        assertEquals(mapper.writeValueAsString(transactionService.findById(1)),
                "{\"transactionId\":1,\"fundId\":1,\"name\":\"buy something\",\"type\":\"reduce\",\"actor\":\"tdnguyen.uet\",\"amountOfMoney\":10.0,\"note\":\"buy y * x\",\"dateOfCreation\":\"2005-03-03\"}");

        assertEquals(transactionService.findById(-1), Optional.empty());
    }

    @Test
    @Sql("/data.sql")
    void deleteByFundId() {
        transactionService.deleteByFundId(1);
        assertEquals(transactionService.findById(1), Optional.empty());
    }

    @Test
    @Sql("/data.sql")
    void findAllByFundId() throws JsonProcessingException {
        assertEquals(mapper.writeValueAsString(transactionService.findAllByFundId(1)),
                "[{\"transactionId\":1,\"fundId\":1,\"name\":\"buy something\",\"type\":\"reduce\",\"actor\":\"tdnguyen.uet\",\"amountOfMoney\":10.0,\"note\":\"buy y * x\",\"dateOfCreation\":\"2005-03-03\"}" + ','
                        + "{\"transactionId\":2,\"fundId\":1,\"name\":\"buy x\",\"type\":\"reduce\",\"actor\":\"user3\",\"amountOfMoney\":10.0,\"note\":\"buy x * y\",\"dateOfCreation\":\"2005-04-01\"}" + ','
                        + "{\"transactionId\":3,\"fundId\":1,\"name\":\"nộp tiền\",\"type\":\"increase\",\"actor\":\"user3\",\"amountOfMoney\":1000.0,\"note\":\"nộp tiền hàng tháng\",\"dateOfCreation\":\"2005-04-01\"}" + "]");
    }
}