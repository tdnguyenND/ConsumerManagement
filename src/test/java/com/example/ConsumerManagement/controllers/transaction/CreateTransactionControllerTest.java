package com.example.ConsumerManagement.controllers.transaction;

import com.example.ConsumerManagement.models.persistence.entities.Transaction;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.awt.*;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CreateTransactionControllerTest {
    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    protected MockMvc mockMvc;

    @Test
    void create() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        String uri = "/transaction";

        LocalDate dateNow = LocalDate.now();
        String dateOfCreation = dateNow.toString();

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
                .param("fundId", "5")
                .param("name", "tien nha")
                .param("type", "reduce")
                .param("actor", "Trinh Trang")
                .param("amountOfMoney", "1000000")
                .param("note", "chat vcl")
                .param("dateOfCreation", dateOfCreation))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertTrue(content.matches("\\{\"transactionId\":\\d+,\"fundId\":5,\"name\":\"tien nha\",\"type\":\"reduce\",\"actor\":\"Trinh Trang\",\"amountOfMoney\":1000000.0,\"note\":\"chat vcl\",\"dateOfCreation\":\"2020-05-29\"}"));
    }
}
