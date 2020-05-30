package com.example.ConsumerManagement.controllers.fund;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.Cookie;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DeleteFundControllerTest {
    @Autowired
    WebApplicationContext webApplicationContext;

    protected MockMvc mockMvc;
    private String uri = "/fund/{fundId}";

    @Test
    void deleteFund() throws Exception{
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        String uri = "/fund/{fundId}";
        String fundId = "19";
        MvcResult mvcResultDelete = mockMvc.perform(MockMvcRequestBuilders.delete(uri, fundId))
                .andReturn();

        int statusCode = mvcResultDelete.getResponse().getStatus();
        assertEquals(200, statusCode);

        MvcResult mvcResultFind = mockMvc.perform(MockMvcRequestBuilders.get(uri, fundId))
                .andReturn();
        String content = mvcResultFind.getResponse().getContentAsString();
        assertEquals(content, "null");
    }

    @Test
    void deleteAFundNotExist() throws Exception{
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        String fundId = "-1";
        MvcResult mvcResultDelete = mockMvc.perform(MockMvcRequestBuilders.delete(uri, fundId))
                .andReturn();

        int statusCode = mvcResultDelete.getResponse().getStatus();
        assertEquals(200, statusCode);

        MvcResult mvcResultFind = mockMvc.perform(MockMvcRequestBuilders.get(uri, fundId))
                .andReturn();
        String content = mvcResultFind.getResponse().getContentAsString();
        assertEquals(content, "Obj not exist");
    }
}