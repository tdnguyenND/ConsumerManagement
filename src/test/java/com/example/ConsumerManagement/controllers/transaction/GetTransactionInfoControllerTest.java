package com.example.ConsumerManagement.controllers.transaction;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GetTransactionInfoControllerTest {
    @Autowired
    WebApplicationContext webApplicationContext;

    protected MockMvc mock;
    private String uri = "/transaction/{transactionId}";

    @Test
    void getInfo() throws Exception {
        mock = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        MvcResult mvcResult = mock.perform(MockMvcRequestBuilders.get(uri, "6"))
                .andReturn();

        int statusCode = mvcResult.getResponse().getStatus();
        assertEquals(200, statusCode);
        String content = mvcResult.getResponse().getContentAsString();

        assertTrue(content.matches("\\{\"transactionId\":\\d+,\"fundId\":5,\"name\":\"tien nha\",\"type\":\"reduce\",\"actor\":\"Trinh Trang\",\"amountOfMoney\":1000000.0,\"note\":\"chat vcl\",\"dateOfCreation\":\"2020-05-29\"}"));
    }

    @Test
    void getAnObjNotExist() throws Exception {
        mock = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        MvcResult mvcResult = mock.perform(MockMvcRequestBuilders.get(uri, "-1"))
                .andReturn();
        
        int statusCode = mvcResult.getResponse().getStatus();
        assertEquals(200, statusCode);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "null");
    }
}