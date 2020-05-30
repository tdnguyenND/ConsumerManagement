package com.example.ConsumerManagement.controllers.memberController;

import com.example.ConsumerManagement.controllers.ControllerAbstractTest;
import com.example.ConsumerManagement.models.persistence.entities.Fund;
import com.example.ConsumerManagement.services.FundService;
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
class TransferTreasurerControllerTest extends ControllerAbstractTest {

    @Autowired
    FundService fundService;

    int defaultFundId;
    String uri;

    @BeforeEach
    void init(){
        setUp();
        defaultFundId = 1;
        uri = "/" + defaultFundId + "/member/transfer-treasurer";
    }

    @Test
    @Sql("/data.sql")
    @DisplayName("should be success if everything are fine")
    void transferTreasurer() throws Exception {
        String username = "tdnguyen.uet";
        String target = "user1";
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.get(uri)
                .param("target", target)
                .cookie(new Cookie("username", username))
        ).andReturn().getResponse();

        assertEquals(response.getStatus(), 200);
        assertEquals(response.getContentAsString(), "success");

        assertEquals("user1",
                fundService.findById(defaultFundId).get().getOwner());
    }

    @Test
    @Sql("/data.sql")
    @DisplayName("should be fail if user is not treasurer of fund")
    void transferTreasurer1() throws Exception {
        String username = "user3";
        String target = "user1";
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.get(uri)
                .param("target", target)
                .cookie(new Cookie("username", username))
        ).andReturn().getResponse();

        assertEquals(response.getStatus(), 200);
        assertEquals(response.getContentAsString(), "fail");

        assertEquals("tdnguyen.uet",
                fundService.findById(defaultFundId).get().getOwner());
    }

    @Test
    @Sql("/data.sql")
    @DisplayName("should be denied if target user is not in fund")
    void transferTreasurer2() throws Exception {
        String username = "tdnguyen.uet";
        String target = "user2";
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.get(uri)
                .param("target", target)
                .cookie(new Cookie("username", username))
        ).andReturn().getResponse();

        assertEquals(response.getStatus(), 200);
        assertEquals(response.getContentAsString(), "fail");

        assertEquals("tdnguyen.uet",
                fundService.findById(defaultFundId).get().getOwner());
    }

    @Test
    @Sql("/data.sql")
    @DisplayName("should be fail if target user is not exist")
    void transferTreasurer3() throws Exception {
        String username = "tdnguyen.uet";
        String target = "x";
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.get(uri)
                .param("target", target)
                .cookie(new Cookie("username", username))
        ).andReturn().getResponse();

        assertEquals(response.getStatus(), 200);
        assertEquals(response.getContentAsString(), "fail");

        assertEquals("tdnguyen.uet",
                fundService.findById(defaultFundId).get().getOwner());
    }
}