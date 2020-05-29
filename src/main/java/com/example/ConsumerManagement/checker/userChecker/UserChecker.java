package com.example.ConsumerManagement.checker.userChecker;

import com.example.ConsumerManagement.checker.CheckerDecorator;

public abstract class UserChecker extends CheckerDecorator {
    protected String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
