package com.example.ConsumerManagement.controllers.memberController;

import com.example.ConsumerManagement.checker.userChecker.UserExistChecker;
import com.example.ConsumerManagement.controllers.AbstractAuthenticationRequiredController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class InteractiveController extends AbstractAuthenticationRequiredController {
    @Autowired
    protected UserExistChecker targetExist;

    protected void setTargetExistParams(String target){
        targetExist.setUsername(target);
    }

    @Override
    public void initChecker(){
        super.initChecker();
        this.addChecker(targetExist);
    }
}
