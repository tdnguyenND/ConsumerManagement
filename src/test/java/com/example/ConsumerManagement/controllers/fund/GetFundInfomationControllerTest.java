package com.example.ConsumerManagement.controllers.fund;

import com.example.ConsumerManagement.services.FundService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GetFundInfomationControllerTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    protected MockMvc mock;
    private String uri = "/fund/{fundId}";
    @Test
    void getFundInfo() throws Exception {
        mock = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        MvcResult mvcResult = mock.perform(MockMvcRequestBuilders.get(uri, "10"))
                .andReturn();

        int statusCode = mvcResult.getResponse().getStatus();
        assertEquals(200, statusCode);
        String content = mvcResult.getResponse().getContentAsString();
        assertTrue(content.matches("\\{\"fundId\":\\d+,\"name\":\"Sinh hoat hang thang\",\"owner\":\"Trinh Thi Thu Trang\",\"dateOfCreation\":\"2020-05-28\",\"balance\":2000000.0}"));
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