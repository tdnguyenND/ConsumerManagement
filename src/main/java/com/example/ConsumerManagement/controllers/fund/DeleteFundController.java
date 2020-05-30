package com.example.ConsumerManagement.controllers.fund;

import com.example.ConsumerManagement.checker.fundAuthenticityChecker.FundTreasurerAuthenticityChecker;
import com.example.ConsumerManagement.checker.fundChecker.FundExistChecker;
import com.example.ConsumerManagement.controllers.AbstractAuthenticationRequiredController;
import com.example.ConsumerManagement.services.FundService;
import com.example.ConsumerManagement.services.UserFundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class DeleteFundController extends AbstractAuthenticationRequiredController {
    @Autowired
    FundService fundService;

    @Autowired
    UserFundService userFundService;

    @Autowired
    FundTreasurerAuthenticityChecker actorIsTreasurer;
    void setActorIsTreasurerParams(String actor, Integer fundId){
        actorIsTreasurer.setFundId(fundId);
        actorIsTreasurer.setUsername(actor);
    }

    @Autowired
    FundExistChecker fundExistChecker;
    void setFundExistChecker(Integer fundId){
        fundExistChecker.setFundId(fundId);
    }

    @Override
    public void initChecker() {
        super.initChecker();
        this.addChecker(fundExistChecker);
        this.addChecker(actorIsTreasurer);
    }

    @DeleteMapping(value = "/fund/{fundId}")
    public @ResponseBody String delete(@PathVariable int fundId,
                       @CookieValue("username")String actor) {
        if (checker == null) initChecker();
        initParams(actor, fundId);
        if (!checker.satisfy()) return "fail";

        fundService.deleteById(fundId);
        userFundService.deleteAllByFundId(fundId);

        return "success";
    }

    private void initParams(String actor, int fundId) {
        setActorExistParams(actor);
        setActorIsTreasurerParams(actor, fundId);
        setFundExistChecker(fundId);
    }
}
