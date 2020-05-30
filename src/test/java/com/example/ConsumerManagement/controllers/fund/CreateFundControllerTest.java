package com.example.ConsumerManagement.controllers.fund;

import com.example.ConsumerManagement.controllers.ControllerAbstractTest;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql("/data.sql")
public class CreateFundControllerTest extends ControllerAbstractTest {
    String uri;
    String name;
    Double balance;
    String owner;

    @BeforeEach
    void init(){
        setUp();
        uri = "/fund";
        name = "Sinh hoat hang thang";
        balance = 2000000.0;
        owner = "user1";
    }

    @Test
    public void creatFundTest() throws Exception{

        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.post(uri)
                .param("name", name)
                .param("balance", String.valueOf(balance))
                .cookie(new Cookie("owner", owner))
        ).andReturn().getResponse();

        int statusCode = response.getStatus();
        assertEquals(200, statusCode);
        String content = response.getContentAsString();
        assertEquals("success", content);
    }

    @Test
    public void createFundWithoutName() throws Exception{
        name = null;
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.post(uri)
                .param("name", name)
                .cookie(new Cookie("owner", owner))
                .param("balance", String.valueOf(balance))
        ).andReturn().getResponse();

        int statusCode = response.getStatus();
        assertEquals(200, statusCode);
    }

    @Test
    public void createFundWithoutBalance() throws Exception{
        balance = null;
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.post(uri)
                .param("name", name)
                .param("balance", String.valueOf(balance))
                .cookie(new Cookie("owner", owner))
        ).andReturn().getResponse();

        assertEquals(400, response.getStatus());
    }

    @Test
    public void createFundWithBalanceIsNegative() throws Exception{
        balance = -1.0;
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.post(uri)
                .param("name", name)
                .cookie(new Cookie("owner", owner))
                .param("balance", String.valueOf(balance))
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());
        assertEquals("fail", response.getContentAsString());
    }


    @Test
    public void createFundWithoutOwner() throws Exception{
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.post(uri)
                .param("name", name)
                .param("balance", String.valueOf(balance))
        ).andReturn().getResponse();

        assertEquals(400, response.getStatus());
    }

}
