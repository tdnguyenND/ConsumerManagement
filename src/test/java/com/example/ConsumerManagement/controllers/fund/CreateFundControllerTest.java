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
public class CreateFundControllerTest extends CreateFundController {
    @Autowired
    WebApplicationContext webApplicationContext;

    protected MockMvc mockMvc;

    @Test
    public void creatFundTest() throws Exception{
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/fund")
                .param("name", "Sinh hoat hang thang")
                .param("balance", "2000000")
                .cookie(new Cookie("owner", "Trinh Trang"))).andReturn();

        int statusCode = mvcResult.getResponse().getStatus();
        assertEquals(200, statusCode);
        String content = mvcResult.getResponse().getContentAsString();
        assertTrue(content.matches("\\{\"fundId\":\\d+,\"name\":\"Sinh hoat hang thang\",\"owner\":\"Trinh Thi Thu Trang\",\"dateOfCreation\":\"\\d{4}-\\d{2}-\\d{2}\",\"balance\":2000000.0}"));
    }

    @Test
    public void createFundWithoutName() throws Exception{
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/fund")
                .param("name", "")
                .cookie(new Cookie("owner", "Pham Thi Hoa"))
                .param("balance", "8000000")).andReturn();

        int statusCode = mvcResult.getResponse().getStatus();
        assertEquals(200, statusCode);
//        String content = mvcResult.getResponse().getContentAsString();
//        assertEquals(content, "Validate is failed");
    }

    @Test
    public void createFundWithoutBalance() throws Exception{
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/fund")
                .param("name", "Mua xe")
                .param("balance", "")
                .cookie(new Cookie("owner", "Nguyen Thi Thao"))).andReturn();

        int statusCode = mvcResult.getResponse().getStatus();
        assertEquals(400, statusCode);
//        String content = mvcResult.getResponse().getContentAsString();
//        assertEquals(content, "Validate is failed");
    }

    @Test
    public void createFundWithBalanceIsNegative() throws Exception{
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/fund")
                .param("name", "Mua xe")
                .cookie(new Cookie("owner", "Nguyen Thi Thao"))
                .param("balance", "-300000")).andReturn();

        int statusCode = mvcResult.getResponse().getStatus();
        assertEquals(200, statusCode);
//        String content = mvcResult.getResponse().getContentAsString();
//        assertEquals(content, "invalid value");
    }


    @Test
    public void createFundWithoutOwner() throws Exception{
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/fund")
                .param("name", "Mua xe")
                .param("balance", "")).andReturn();

        int statusCode = mvcResult.getResponse().getStatus();
        assertEquals(400, statusCode);
//        String content = mvcResult.getResponse().getContentAsString();
//        assertEquals(content, "invalid value");
    }

}
