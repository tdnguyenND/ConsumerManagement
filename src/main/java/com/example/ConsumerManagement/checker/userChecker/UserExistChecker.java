package com.example.ConsumerManagement.checker.userChecker;

import com.example.ConsumerManagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class UserExistChecker extends UserChecker {

    @Autowired
    UserService userService;

    @Override
    public boolean satisfy() {
        return userService.findById(username).isPresent() && super.satisfy();
    }
}
