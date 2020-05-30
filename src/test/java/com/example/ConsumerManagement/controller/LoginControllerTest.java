package com.example.ConsumerManagement.controller;


import com.example.ConsumerManagement.config.WebConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfig.class)
@WebAppConfiguration
@SpringBootTest
public class LoginControllerTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void loginTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/login"))
                            .andExpect(model().attributeExists("account"));
    }

    @Test
    public  void processLoginTest() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.post("/login")
                             .param("username","user1")
                             .param("password","password1"))
                             .andExpect(status().isOk())
                             .andExpect(view().name("homePage"));

    }

    @Test
    public  void processLoginTest1() throws Exception{

        this.mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .param("username","user")
                .param("password","password")).andExpect(status().isOk())
                .andExpect(model().attributeExists("error"))
                .andExpect(view().name("loginPage"));

    }

    @Test
    public  void processLoginTest2() throws Exception{

        this.mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .param("username","user10")
                .param("password","password")).andExpect(status().isOk())
                .andExpect(model().attributeExists("error"))
                .andExpect(model().attribute("error","username not exists"))
                .andExpect(view().name("loginPage"));

    }

    @Test
    public  void processLoginTest3() throws Exception{

        this.mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .param("username","user1")
                .param("password","password")).andExpect(status().isOk())
                .andExpect(model().attributeExists("error"))
                .andExpect(model().attribute("error","password not match with username"))
                .andExpect(view().name("loginPage"));

    }

}
