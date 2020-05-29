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
class AddMemberControllerTest extends ControllerAbstractTest {

    @Autowired
    UserFundService userFundService;

    int defaultFundId;
    String uri;

    @BeforeEach
    void init(){
        setUp();
        defaultFundId = 1;
        uri = "/" + defaultFundId + "/member/add";
    }

    @Test
    @Sql("/data.sql")
    @DisplayName("should be success if everything are fine")
    void addMember() throws Exception {
        String username = "tdnguyen.uet";
        String target = "user2";
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.get(uri)
                .param("target", target)
                .cookie(new Cookie("username", username))
        ).andReturn().getResponse();

        assertEquals(response.getStatus(), 200);
        assertEquals(response.getContentAsString(), "success");

        assertEquals(mapToJson(userFundService.findAllMembersByFundId(defaultFundId)),
                "[\"tdnguyen.uet\",\"user1\",\"user2\",\"user3\"]");
    }

    @Test
    @Sql("/data.sql")
    @DisplayName("should reject if username is not treasurer of target fundId")
    void addMember1() throws Exception {
        String username = "user1";
        String target = "user2";
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.get(uri)
                .param("target", target)
                .cookie(new Cookie("username", username))
        ).andReturn().getResponse();

        assertEquals(response.getStatus(), 200);
        assertEquals(response.getContentAsString(), "fail");

        assertEquals(mapToJson(userFundService.findAllMembersByFundId(defaultFundId)),
                "[\"tdnguyen.uet\",\"user1\",\"user3\"]");
    }

    @Test
    @Sql("/data.sql")
    @DisplayName("should reject if target user is in fund already")
    void addMember2() throws Exception {
        String username = "tdnguyen.uet";
        String target = "user1";
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.get(uri)
                .param("target", target)
                .cookie(new Cookie("username", username))
        ).andReturn().getResponse();

        assertEquals(response.getStatus(), 200);
        assertEquals(response.getContentAsString(), "fail");

        assertEquals(mapToJson(userFundService.findAllMembersByFundId(defaultFundId)),
                "[\"tdnguyen.uet\",\"user1\",\"user3\"]");
    }

    @Test
    @Sql("/data.sql")
    @DisplayName("should reject if target user is not exist")
    void addMember3() throws Exception {
        String username = "tdnguyen.uet";
        String target = "x";
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.get(uri)
                .param("target", target)
                .cookie(new Cookie("username", username))
        ).andReturn().getResponse();

        assertEquals(response.getStatus(), 200);
        assertEquals(response.getContentAsString(), "fail");

        assertEquals(mapToJson(userFundService.findAllMembersByFundId(defaultFundId)),
                "[\"tdnguyen.uet\",\"user1\",\"user3\"]");
    }

    @Test
    @Sql("/data.sql")
    @DisplayName("should reject if username is not exist")
    void addMember4() throws Exception {
        String username = "x";
        String target = "user1";
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.get(uri)
                .param("target", target)
                .cookie(new Cookie("username", username))
        ).andReturn().getResponse();

        assertEquals(response.getStatus(), 200);
        assertEquals(response.getContentAsString(), "fail");

        assertEquals(mapToJson(userFundService.findAllMembersByFundId(defaultFundId)),
                "[\"tdnguyen.uet\",\"user1\",\"user3\"]");
    }
}