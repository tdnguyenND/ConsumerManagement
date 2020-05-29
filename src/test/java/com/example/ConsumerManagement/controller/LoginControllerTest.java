package com.example.ConsumerManagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class LoginControllerTest  {
    MockMvc mockMvc;

    ObjectMapper objectMapper;

    @Autowired
    WebApplicationContext webApplicationContext;

    @BeforeEach
    void init(){
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new Jdk8Module());
    }

    @Test
    void loginTest() throws Exception {
        String uri = "/login";
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200,status);

    }

    @Test
    public void processloginTest1() throws Exception{
        String uri = "/login";
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .param("username", "user1")
                .param("password", "password1")).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200,status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content,"success");
    }

    @Test
    public void processloginTest() throws Exception{
        String uri = "/login";
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .param("username", "user1")
                .param("password", "password")).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200,status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content,"fail");
    }



}
