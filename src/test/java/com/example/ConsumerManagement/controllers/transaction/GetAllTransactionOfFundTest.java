package com.example.ConsumerManagement.controllers.transaction;

import com.example.ConsumerManagement.controllers.AbstractController;
import com.example.ConsumerManagement.controllers.ControllerAbstractTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Scope;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.servlet.http.Cookie;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql("/data.sql")
class GetAllTransactionOfFundTest extends ControllerAbstractTest {

    String uri;
    String actor;
    Integer fundId;

    @BeforeEach
    void init(){
        setUp();
        uri = "/{fundId}/transaction/getAll";
        actor = "tdnguyen.uet";
        fundId = 1;
    }

    @Test
    void getAllTransaction() throws Exception {
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.get(uri, fundId)
                .cookie(new Cookie("username", actor))
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());
        assertEquals("[{\"transactionId\":1,\"fundId\":1,\"name\":\"buy something\",\"type\":\"reduce\",\"actor\":\"tdnguyen.uet\",\"amountOfMoney\":10.0,\"note\":\"buy y * x\",\"dateOfCreation\":\"2005-03-03\"}" + ',' +
                        "{\"transactionId\":2,\"fundId\":1,\"name\":\"buy x\",\"type\":\"reduce\",\"actor\":\"user3\",\"amountOfMoney\":10.0,\"note\":\"buy x * y\",\"dateOfCreation\":\"2005-04-01\"}" + ',' +
                        "{\"transactionId\":3,\"fundId\":1,\"name\":\"nộp tiền\",\"type\":\"increase\",\"actor\":\"user3\",\"amountOfMoney\":1000.0,\"note\":\"nộp tiền hàng tháng\",\"dateOfCreation\":\"2005-04-01\"}]"
                , response.getContentAsString());
    }

    @Test
    void fundNotExist() throws Exception {
        fundId = -1;
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.get(uri, fundId)
                .cookie(new Cookie("username", actor))
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());
        assertEquals("Fail to get all transaction"
                , response.getContentAsString());
    }

    @Test
    void fundIdIsNull() throws Exception {
        fundId = null;
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.get(uri, fundId)
                .cookie(new Cookie("username", actor))
        ).andReturn().getResponse();

        assertEquals(404, response.getStatus());
    }

    @Test
    void actorIsNull() throws Exception {
        actor = null;
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.get(uri, fundId)
        ).andReturn().getResponse();

        assertEquals(400, response.getStatus());
    }

    @Test
    void actorIsNotAMember() throws Exception {
        actor = "user2";
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.get(uri, fundId)
                .cookie(new Cookie("username", actor))
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());
        assertEquals("Fail to get all transaction"
                , response.getContentAsString());
    }
}