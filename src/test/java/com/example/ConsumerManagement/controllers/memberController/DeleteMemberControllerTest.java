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
class DeleteMemberControllerTest extends ControllerAbstractTest {

    @Autowired
    UserFundService userFundService;

    int defaultFundId;
    String uri;

    @BeforeEach
    void init(){
        setUp();
        defaultFundId = 1;
        uri = "/" + defaultFundId + "/member/delete";
    }

    @Test
    @Sql("/data.sql")
    @DisplayName("should be success if everything are fine")
    void deleteMember() throws Exception {
        String username = "tdnguyen.uet";
        String target = "user1";
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.get(uri)
                .param("target", target)
                .cookie(new Cookie("username", username))
        ).andReturn().getResponse();

        assertEquals(response.getStatus(), 200);
        assertEquals(response.getContentAsString(), "success");

        assertEquals(mapToJson(userFundService.findAllMembersByFundId(defaultFundId)),
                "[\"tdnguyen.uet\",\"user3\"]");
    }

    @Test
    @Sql("/data.sql")
    @DisplayName("should reject if username is not treasurer of target fundId")
    void deleteMember1() throws Exception {
        String username = "user1";
        String target = "user3";
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
    @DisplayName("should reject if target user is not in fund")
    void deleteMember2() throws Exception {
        String username = "tdnguyen.uet";
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
    @DisplayName("should reject if target user is not exist")
    void deleteMember3() throws Exception {
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
    void deleteMember4() throws Exception {
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