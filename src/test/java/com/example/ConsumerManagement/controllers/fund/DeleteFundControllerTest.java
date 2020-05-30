package com.example.ConsumerManagement.controllers.fund;

import com.example.ConsumerManagement.controllers.ControllerAbstractTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.Cookie;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql("/data.sql")
class DeleteFundControllerTest extends ControllerAbstractTest {

    String uri;
    String actor;
    Integer fundId;

    @BeforeEach
    void init(){
        setUp();
        uri = "/fund/";
        actor = "tdnguyen.uet";
        fundId = 1;
    }

    @Test
    void deleteFund() throws Exception{
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.delete(uri + fundId)
                .cookie(new Cookie("username", actor))
        ).andReturn().getResponse();

        int statusCode = response.getStatus();
        assertEquals(200, statusCode);
        assertEquals("success", response.getContentAsString());
    }

    @Test
    void deleteFund2() throws Exception {
        actor = "user1";
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.delete(uri + fundId)
                .cookie(new Cookie("username", actor))
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());
        assertEquals("fail", response.getContentAsString());
    }

    @Test
    void deleteAFundNotExist() throws Exception{
        fundId = -1;
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.delete(uri + fundId)
                .cookie(new Cookie("username", actor))
        ).andReturn().getResponse();

        int statusCode = response.getStatus();
        assertEquals(200, statusCode);
        assertEquals("fail", response.getContentAsString());
    }
}