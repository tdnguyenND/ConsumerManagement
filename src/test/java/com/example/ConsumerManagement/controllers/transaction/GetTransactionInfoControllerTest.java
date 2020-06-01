package com.example.ConsumerManagement.controllers.transaction;

import com.example.ConsumerManagement.controllers.ControllerAbstractTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.Cookie;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GetTransactionInfoControllerTest extends ControllerAbstractTest {
    private String uri = "/{fundId}/transaction/{transactionId}";
    Integer fundId;
    Integer transactionId;
    String actor;

    @BeforeEach
    void init(){
        setUp();
        fundId = 1;
        transactionId = 1;
        actor = "tdnguyen.uet";
    }

    @Test
    void getInfo() throws Exception {
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.get(uri, fundId, transactionId)
                .cookie(new Cookie("username", actor))
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());

        assertEquals("{\"transactionId\":1,\"fundId\":1,\"name\":\"buy something\",\"type\":\"reduce\",\"actor\":\"tdnguyen.uet\",\"amountOfMoney\":10.0,\"note\":\"buy y * x\",\"dateOfCreation\":\"2005-03-03\"}"
                , response.getContentAsString());
    }

    @Test
    void getAnObjNotExist() throws Exception {
        transactionId = -1;
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.get(uri, fundId, transactionId)
                .cookie(new Cookie("username", actor))
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());
        assertEquals("Fail to get transaction", response.getContentAsString());
    }
}