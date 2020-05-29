package com.example.ConsumerManagement.controllers.fund;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DeleteFundControllerTest {
    @Autowired
    WebApplicationContext webApplicationContext;

    protected MockMvc mockMvc;

    @Test
    void delete() throws Exception{
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        String uri = "/{fundId}";
        MvcResult mvcResultDelete = mockMvc.perform(MockMvcRequestBuilders.delete(uri, "10"))
                .andReturn();

        int statusCode = mvcResultDelete.getResponse().getStatus();
        assertEquals(200, statusCode);

        MvcResult mvcResultFind = mockMvc.perform(MockMvcRequestBuilders.get(uri, "10"))
                .andReturn();
        String content = mvcResultFind.getResponse().getContentAsString();
        assertEquals(content, "null");
    }
}