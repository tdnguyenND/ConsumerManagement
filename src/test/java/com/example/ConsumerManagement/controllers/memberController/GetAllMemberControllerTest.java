package com.example.ConsumerManagement.controllers.memberController;

import com.example.ConsumerManagement.controllers.ControllerAbstractTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.servlet.http.Cookie;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql("/data.sql")
class GetAllMemberControllerTest extends ControllerAbstractTest {

    String uriCtx;
    Integer fundId;
    String actor;

    @BeforeEach
    void init(){
        setUp();
        uriCtx = "/{fundId}/member/getAll";
        fundId = 1;
        actor = "tdnguyen.uet";
    }

    @Test
    void getAllMember() throws Exception {
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.get(uriCtx, fundId)
                .cookie(new Cookie("username", actor))).andReturn().getResponse();

        assertEquals(200, response.getStatus());
        assertEquals("[\"tdnguyen.uet\",\"user1\",\"user3\"]", response.getContentAsString());
    }

    @Test
    void getAllMember1() throws Exception{
        actor = "user2";
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.get(uriCtx, fundId)
                .cookie(new Cookie("username", actor))).andReturn().getResponse();
        assertEquals(200, response.getStatus());
        assertEquals("", response.getContentAsString());
    }

    @Test
    void getAllMember2() throws Exception{
        actor = "x";
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.get(uriCtx, fundId)
                .cookie(new Cookie("username", actor))).andReturn().getResponse();
        assertEquals(200, response.getStatus());
        assertEquals("", response.getContentAsString());
    }

    @Test
    void getAllMember3() throws Exception{
        fundId = null;
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.get(uriCtx, fundId)
                .cookie(new Cookie("username", actor))).andReturn().getResponse();
        assertEquals(404, response.getStatus());
    }

    @Test
    void getAllMember4() throws Exception{
        fundId = -1;
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.get(uriCtx, fundId)
                .cookie(new Cookie("username", actor))).andReturn().getResponse();
        assertEquals(200, response.getStatus());
        assertEquals("", response.getContentAsString());
    }
}