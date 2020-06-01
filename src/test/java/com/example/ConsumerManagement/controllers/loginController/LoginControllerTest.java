package com.example.ConsumerManagement.controllers.loginController;


import com.example.ConsumerManagement.controllers.ControllerAbstractTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Objects;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class LoginControllerTest extends ControllerAbstractTest {

    String uri;
    String username;
    String password;

    @BeforeEach
    void init(){
        setUp();
        uri = "/login";
        username = "tdnguyen.uet";
        password = "12345678";
    }

    @Test
    public void processLoginTest() throws Exception{
        MockHttpServletResponse response = this.mvc.perform(MockMvcRequestBuilders.post(uri)
                .param("username",username)
                .param("password",password))
                .andReturn().getResponse();
        assertEquals(username, Objects.requireNonNull(response.getCookie("username")).getValue());

    }

    @Test
    public void usernameNotExist() throws Exception{
        username = "user";
        MockHttpServletResponse response = this.mvc.perform(MockMvcRequestBuilders.post(uri)
                .param("username",username)
                .param("password",password))
                .andReturn().getResponse();

        assertEquals("Account is not exist",
                response.getContentAsString());

    }

    @Test
    public void processLoginTest3() throws Exception{

        MockHttpServletResponse response = this.mvc.perform(MockMvcRequestBuilders.post("/login")
                .param("username","user1")
                .param("password","password"))
                .andReturn().getResponse();
        assertEquals("Password does not match", response.getContentAsString());
    }

}
