package com.example.ConsumerManagement.controllers.fund;

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
public class CreateFundControllerTest extends CreateFundController {
    @Autowired
    WebApplicationContext webApplicationContext;

    protected MockMvc mockMvc;

    @Test
    public void creatFundTest() throws Exception{
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/fund")
                .param("name", "Sinh hoat hang thang")
                .param("owner", "Trinh Thi Thu Trang")
                .param("balance", "2000000")).andReturn();

        int statusCode = mvcResult.getResponse().getStatus();
        assertEquals(200, statusCode);
        String content = mvcResult.getResponse().getContentAsString();
        assertTrue(content.matches("\\{\"fundId\":\\d+,\"name\":\"Sinh hoat hang thang\",\"owner\":\"Trinh Thi Thu Trang\",\"dateOfCreation\":\"2020-05-28\",\"balance\":2000000.0}"));
    }
}
