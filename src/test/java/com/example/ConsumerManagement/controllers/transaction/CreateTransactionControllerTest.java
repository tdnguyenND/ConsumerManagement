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

import javax.servlet.http.Cookie;
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
    private String uri = "/transaction";

    @Test
    void createTransaction() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        LocalDate dateNow = LocalDate.now();
        String dateOfCreation = dateNow.toString();

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
                .param("fundId", "5")
                .param("name", "tien nha")
                .param("type", "reduce")
                .param("amountOfMoney", "1000000")
                .param("note", "chat vcl")
                .param("dateOfCreation", dateOfCreation)
                .cookie(new Cookie("actor", "Nguyen Quynh")))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertTrue(content.matches("\\{\"transactionId\":\\d+,\"fundId\":5,\"name\":\"tien nha\",\"type\":\"reduce\",\"actor\":\"Trinh Trang\",\"amountOfMoney\":1000000.0,\"note\":\"chat vcl\",\"dateOfCreation\":\"2020-05-29\"}"));
    }

    @Test
    void createTransactionWithoutName() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        LocalDate dateNow = LocalDate.now();
        String dateOfCreation = dateNow.toString();

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
                .param("fundId", "5")
                .param("name", "")
                .param("type", "reduce")
                .param("amountOfMoney", "1000000")
                .param("note", "chat vcl")
                .param("dateOfCreation", dateOfCreation)
                .cookie(new Cookie("actor", "Hoang Thi C")))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Validate failed");
    }

    @Test
    void createTransactionWithoutType() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        LocalDate dateNow = LocalDate.now();
        String dateOfCreation = dateNow.toString();

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
                .param("fundId", "15")
                .param("name", "Duong Thi B")
                .param("type", "")
                .param("note", "")
                .param("amountOfMoney", "1000000")
                .param("dateOfCreation", dateOfCreation)
                .cookie(new Cookie("actor", "Hoang Thi C")))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Validate failed");
    }

    @Test
    void createTransactionWithoutFundId() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        LocalDate dateNow = LocalDate.now();
        String dateOfCreation = dateNow.toString();

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
                .param("fundId", "")
                .param("name", "Duong Thi B")
                .param("type", "increase")
                .param("note", "")
                .param("amountOfMoney", "1000000")
                .param("dateOfCreation", dateOfCreation)
                .cookie(new Cookie("actor", "Hoang Thi C")))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Validate failed");
    }

    @Test
    void createTransactionWithoutAmountOfMoney() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        LocalDate dateNow = LocalDate.now();
        String dateOfCreation = dateNow.toString();

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
                .param("fundId", "5")
                .param("name", "")
                .param("type", "reduce")
                .param("note", "chat vcl")
                .param("amountOfMoney", "")
                .param("dateOfCreation", dateOfCreation)
                .cookie(new Cookie("actor", "Hoang Thi C")))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Validate failed");
    }

    @Test
    void createTransactionWithoutActor() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        LocalDate dateNow = LocalDate.now();
        String dateOfCreation = dateNow.toString();

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
                .param("fundId", "15")
                .param("name", "Duong Thi B")
                .param("type", "increase")
                .param("note", "")
                .param("amountOfMoney", "1000000")
                .param("dateOfCreation", dateOfCreation))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Validate failed");
    }

    @Test
    void createTransactionWithFundNotExist() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        LocalDate dateNow = LocalDate.now();
        String dateOfCreation = dateNow.toString();

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
                .param("fundId", "-1")
                .param("name", "An com")
                .param("type", "reduce")
                .param("note", "chat vcl")
                .param("amountOfMoney", "200000")
                .param("dateOfCreation", dateOfCreation)
                .cookie(new Cookie("actor", "Hoang Thi C")))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Somethings Not Exist");
    }
}


