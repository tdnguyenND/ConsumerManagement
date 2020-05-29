package com.example.ConsumerManagement.controllers.memberController;

import com.example.ConsumerManagement.controllers.ControllerAbstractTest;
import com.example.ConsumerManagement.services.UserFundService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.servlet.http.Cookie;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LeaveFundControllerTest extends ControllerAbstractTest {
    @Autowired
    UserFundService userFundService;

    int defaultFundId;
    String uri;

    @BeforeEach
    void init(){
        setUp();
        defaultFundId = 1;
        uri = "/" + defaultFundId + "/member/leave";
    }

    @Test
    @Sql("/data.sql")
    @DisplayName("should be success if everything are fine")
    void leaveFund() throws Exception {
        String username = "user1";
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.get(uri)
                .cookie(new Cookie("username", username))
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());
        assertEquals("success", response.getContentAsString());
        assertEquals("[\"tdnguyen.uet\",\"user3\"]",
                mapToJson(userFundService.findAllMembersByFundId(defaultFundId)));
    }

    @Test
    @Sql("/data.sql")
    @DisplayName("should be denied if username is treasurer of target fund")
    void leaveFund1() throws Exception {
        String username = "tdnguyen.uet";
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.get(uri)
                .cookie(new Cookie("username", username))
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());
        assertEquals("fail", response.getContentAsString());
        assertEquals("[\"tdnguyen.uet\",\"user1\",\"user3\"]",
                mapToJson(userFundService.findAllMembersByFundId(defaultFundId)));
    }

    @Test
    @Sql("/data.sql")
    @DisplayName("should be fail if user is not in fund")
    void leaveFund2() throws Exception {
        String username = "user2";
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.get(uri)
                .cookie(new Cookie("username", username))
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());
        assertEquals("fail", response.getContentAsString());
        assertEquals("[\"tdnguyen.uet\",\"user1\",\"user3\"]",
                mapToJson(userFundService.findAllMembersByFundId(defaultFundId)));
    }

    @Test
    @Sql("/data.sql")
    @DisplayName("should be fail if user is not exist")
    void leaveFund3() throws Exception {
        String username = "x";
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.get(uri)
                .cookie(new Cookie("username", username))
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());
        assertEquals("fail", response.getContentAsString());
        assertEquals("[\"tdnguyen.uet\",\"user1\",\"user3\"]",
                mapToJson(userFundService.findAllMembersByFundId(defaultFundId)));
    }
}