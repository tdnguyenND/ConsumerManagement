package com.example.ConsumerManagement.controllers.memberController;

import com.example.ConsumerManagement.controllers.ControllerAbstractTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.servlet.http.Cookie;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql("/data.sql")
class GetMemberDetailsControllerTest extends ControllerAbstractTest {
    int defaultFundId;
    String uri;

    @BeforeEach
    void init(){
        setUp();
        defaultFundId = 1;
        uri = "/" + defaultFundId + "/member/details";
    }

    @Test
    @DisplayName("should be success if everything are fine")
    void getMemberDetails() throws Exception {
        String username = "tdnguyen.uet";
        String target = "user3";
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.get(uri)
                .param("target", target)
                .cookie(new Cookie("username", username))
        ).andReturn().getResponse();
        assertEquals(response.getStatus(), 200);
        assertEquals(response.getContentAsString(),
                "{\"username\":\"user3\",\"firstName\":\"firstName3\",\"lastName\":\"lastName3\",\"password\":\"password3\",\"phoneNumber\":\"913873123\",\"gender\":\"male\",\"email\":\"user3@email.com\"}");
    }

    @Test
    @DisplayName("should be denied if username is not in fund")
    void getMemberDetails1() throws Exception {
        String username = "user2";
        String target = "user3";
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.get(uri)
                .param("target", target)
                .cookie(new Cookie("username", username))
        ).andReturn().getResponse();
        assertEquals(response.getStatus(), 200);
        assertEquals(response.getContentAsString(),
                "null");
    }

    @Test
    @DisplayName("should be denied if username is not exist")
    void getMemberDetails2() throws Exception {
        String username = "x";
        String target = "user3";
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.get(uri)
                .param("target", target)
                .cookie(new Cookie("username", username))
        ).andReturn().getResponse();
        assertEquals(response.getStatus(), 200);
        assertEquals(response.getContentAsString(),
                "null");
    }

    @Test
    @DisplayName("should return null if target user is not exist")
    void getMemberDetails3() throws Exception {
        String username = "tdnguyen.uet";
        String target = "x";
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.get(uri)
                .param("target", target)
                .cookie(new Cookie("username", username))
        ).andReturn().getResponse();
        assertEquals(response.getStatus(), 200);
        assertEquals(response.getContentAsString(),
                "null");
    }

    @Test
    @DisplayName("should return null if target user is not in fund")
    void getMemberDetails4() throws Exception {
        String username = "tdnguyen.uet";
        String target = "user2";
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.get(uri)
                .param("target", target)
                .cookie(new Cookie("username", username))
        ).andReturn().getResponse();
        assertEquals(response.getStatus(), 200);
        assertEquals(response.getContentAsString(),
                "null");
    }
}