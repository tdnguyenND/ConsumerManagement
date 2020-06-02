package com.example.ConsumerManagement.controllers.fund;

import com.example.ConsumerManagement.controllers.AbstractAuthenticationRequiredController;
import com.example.ConsumerManagement.controllers.ControllerAbstractTest;
import com.example.ConsumerManagement.services.FundService;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql("/data.sql")
class GetFundInformationControllerTest extends ControllerAbstractTest {

    String uri;
    String actor;
    Integer fundId;

    @BeforeEach
    void init(){
        setUp();
        uri = "/fund/";
        fundId = 1;
        actor = "tdnguyen.uet";
    }


    @Test
    void getFundInfo() throws Exception {
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.get(uri + fundId)
                .cookie(new Cookie("username", actor))
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());
        assertEquals("{\"fundId\":1,\"name\":\"p501\",\"owner\":\"tdnguyen.uet\",\"dateOfCreation\":\"2005-02-28\",\"balance\":1000000.0}",
                response.getContentAsString());
    }

    @Test
    void getAnObjNotExist() throws Exception {
        fundId = -1;
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.get(uri + fundId)
                .cookie(new Cookie("username", actor))
        ).andReturn().getResponse();

        int statusCode = response.getStatus();
        assertEquals(200, statusCode);
        String content = response.getContentAsString();
        assertEquals(content, "null");
    }

    @Test
    void actorIsNotAMemberOfTargetFund() throws Exception {
        actor = "user2";
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.get(uri + fundId)
                .cookie(new Cookie("username", actor))
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());
        assertEquals("null", response.getContentAsString());

    }
}