package com.example.ConsumerManagement.controllers.transaction;

import com.example.ConsumerManagement.controllers.ControllerAbstractTest;
import com.example.ConsumerManagement.models.persistence.entities.Transaction;
import com.example.ConsumerManagement.services.TransactionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.Cookie;
import java.awt.*;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql("/data.sql")
class CreateTransactionControllerTest extends ControllerAbstractTest {
    String uri;
    Integer fundId;
    String actor;
    String name;
    String type;
    Double amountOfMoney;
    String note;

    @Autowired
    TransactionService transactionService;

    @BeforeEach
    void init(){
        setUp();
        uri = "/{fundId}/transaction/create";
        fundId = 1;
        type = "reduce";
        actor = "tdnguyen.uet";
        name = "tien nha";
        amountOfMoney = 1000000.0;
        note = "some note";
    }

    @Test
    void create() throws Exception {
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.post(uri, fundId)
                .param("name", name)
                .param("type", type)
                .param("actor", actor)
                .param("amountOfMoney", String.valueOf(amountOfMoney))
                .param("note", note)
                .cookie(new Cookie("username", actor))
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());
        assertEquals("success", response.getContentAsString());
        assertEquals("[{\"transactionId\":1,\"fundId\":1,\"name\":\"buy something\",\"type\":\"reduce\",\"actor\":\"tdnguyen.uet\",\"amountOfMoney\":10.0,\"note\":\"buy y * x\",\"dateOfCreation\":\"2005-03-03\"}" + ',' +
                        "{\"transactionId\":2,\"fundId\":1,\"name\":\"buy x\",\"type\":\"reduce\",\"actor\":\"user3\",\"amountOfMoney\":10.0,\"note\":\"buy x * y\",\"dateOfCreation\":\"2005-04-01\"}" + ',' +
                        "{\"transactionId\":3,\"fundId\":1,\"name\":\"nộp tiền\",\"type\":\"increase\",\"actor\":\"user3\",\"amountOfMoney\":1000.0,\"note\":\"nộp tiền hàng tháng\",\"dateOfCreation\":\"2005-04-01\"}" + ',' +
                        "{\"transactionId\":6,\"fundId\":" + fundId + ",\"name\":\"" + name + "\",\"type\":\"" + type + "\",\"actor\":\"" + actor + "\",\"amountOfMoney\":" + amountOfMoney + ",\"note\":\"" + note + "\",\"dateOfCreation\":\"" + LocalDate.now().toString() + "\"}]"
                , mapToJson(transactionService.findAllByFundId(fundId)));
    }

    @Test
    void createWithoutName() throws Exception {
        name = null;
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.post(uri, fundId)
                .param("name", name)
                .param("type", type)
                .param("amountOfMoney", String.valueOf(amountOfMoney))
                .param("note", note)
                .cookie(new Cookie("username", actor))
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());
        assertEquals("fail", response.getContentAsString());
    }

    @Test
    void createWithoutType() throws Exception {
        type = null;
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.post(uri, fundId)
                .param("name", name)
                .param("type", type)
                .param("amountOfMoney", String.valueOf(amountOfMoney))
                .param("note", note)
                .cookie(new Cookie("username", actor))
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());
        assertEquals("fail", response.getContentAsString());
    }

    @Test
    void createWithoutAmountOfMoney() throws Exception {
        amountOfMoney = null;
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.post(uri, fundId)
                .param("name", name)
                .param("type", type)
                .param("actor", actor)
                .param("amountOfMoney", String.valueOf(amountOfMoney))
                .param("note", note)
                .cookie(new Cookie("username", actor))
        ).andReturn().getResponse();

        assertEquals(400, response.getStatus());
    }

    @Test
    @Sql("/data.sql")
    void createWithoutNote() throws Exception {
        note = null;
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.post(uri, fundId)
                .param("name", name)
                .param("type", type)
                .param("actor", actor)
                .param("amountOfMoney", String.valueOf(amountOfMoney))
                .param("note", note)
                .cookie(new Cookie("username", actor))
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());
        assertEquals("success", response.getContentAsString());
        assertEquals("[{\"transactionId\":1,\"fundId\":1,\"name\":\"buy something\",\"type\":\"reduce\",\"actor\":\"tdnguyen.uet\",\"amountOfMoney\":10.0,\"note\":\"buy y * x\",\"dateOfCreation\":\"2005-03-03\"}" + ',' +
                        "{\"transactionId\":2,\"fundId\":1,\"name\":\"buy x\",\"type\":\"reduce\",\"actor\":\"user3\",\"amountOfMoney\":10.0,\"note\":\"buy x * y\",\"dateOfCreation\":\"2005-04-01\"}" + ',' +
                        "{\"transactionId\":3,\"fundId\":1,\"name\":\"nộp tiền\",\"type\":\"increase\",\"actor\":\"user3\",\"amountOfMoney\":1000.0,\"note\":\"nộp tiền hàng tháng\",\"dateOfCreation\":\"2005-04-01\"}" + ',' +
                        "{\"transactionId\":6,\"fundId\":" + fundId + ",\"name\":\"" + name + "\",\"type\":\"" + type + "\",\"actor\":\"" + actor + "\",\"amountOfMoney\":" + amountOfMoney + ",\"note\":" + note + ",\"dateOfCreation\":\"" + LocalDate.now().toString() + "\"}]"
                , mapToJson(transactionService.findAllByFundId(fundId)));
    }

    @Test
    void actorIsNotAMember() throws Exception {
        actor = "user2";
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.post(uri, fundId)
                .param("name", name)
                .param("type", type)
                .param("actor", actor)
                .param("amountOfMoney", String.valueOf(amountOfMoney))
                .param("note", note)
                .cookie(new Cookie("username", actor))
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());
        assertEquals("fail", response.getContentAsString());
    }

    @Test
    void actorIsNotExist() throws Exception {
        actor = "x";
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.post(uri, fundId)
                .param("name", name)
                .param("type", type)
                .param("actor", actor)
                .param("amountOfMoney", String.valueOf(amountOfMoney))
                .param("note", note)
                .cookie(new Cookie("username", actor))
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());
        assertEquals("fail", response.getContentAsString());
    }

    @Test
    void fundIsNotExist() throws Exception {
        fundId = -1;
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.post(uri, fundId)
                .param("name", name)
                .param("type", type)
                .param("actor", actor)
                .param("amountOfMoney", String.valueOf(amountOfMoney))
                .param("note", note)
                .cookie(new Cookie("username", actor))
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());
        assertEquals("fail", response.getContentAsString());
    }
}
