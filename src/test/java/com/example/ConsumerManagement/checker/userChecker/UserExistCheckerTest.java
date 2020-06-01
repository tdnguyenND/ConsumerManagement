package com.example.ConsumerManagement.checker.userChecker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql("/data.sql")
class UserExistCheckerTest {

    @Autowired
    UserExistChecker userExistChecker;

    @Test
    void satisfy() {
        userExistChecker.setUsername("tdnguyen.uet");
        assertTrue(userExistChecker.satisfy());

        userExistChecker.setUsername("x");
        assertFalse(userExistChecker.satisfy());
    }
}