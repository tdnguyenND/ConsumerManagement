package com.example.ConsumerManagement.controllers;

import com.example.ConsumerManagement.checker.userChecker.UserExistChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractAuthenticationRequiredController extends AbstractController {
    protected String actor;

    @Autowired
    protected UserExistChecker actorExist;

    protected void setActorExistParams(String actor){
        this.actorExist.setUsername(actor);
    }

    public void initChecker(){
        super.initChecker();
        addChecker(actorExist);
    }
}
