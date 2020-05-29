package com.example.ConsumerManagement.controllers.memberController;

import com.example.ConsumerManagement.checker.fundAuthenticityChecker.FundMemberAuthenticityChecker;
import com.example.ConsumerManagement.checker.fundAuthenticityChecker.NotATreasurerOfFundChecker;
import com.example.ConsumerManagement.checker.userChecker.UserExistChecker;
import com.example.ConsumerManagement.controllers.AbstractAuthenticationRequiredController;
import com.example.ConsumerManagement.controllers.AbstractController;
import com.example.ConsumerManagement.models.persistence.relationships.UserFundKey;
import com.example.ConsumerManagement.services.UserFundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LeaveFundController extends AbstractAuthenticationRequiredController {
    @Autowired
    UserFundService userFundService;

    @Autowired
    FundMemberAuthenticityChecker actorIsMember;
    protected void setActorIsMemberParams(String actor, Integer fundId){
        this.actorIsMember.setUsername(actor);
        this.actorIsMember.setFundId(fundId);
    }

    @Autowired
    NotATreasurerOfFundChecker actorIsNotTreasurer;
    protected void setActorIsNotTreasurerParams(String actor, Integer fundId){
        this.actorIsNotTreasurer.setUsername(actor);
        this.actorIsNotTreasurer.setFundId(fundId);
    }

    @Override
    public void initChecker() {
        super.initChecker();
        addChecker(actorIsMember);
        addChecker(actorIsNotTreasurer);
    }

    @RequestMapping("/{fundId}/member/leave")
    public @ResponseBody String leaveFund(@CookieValue("username")String actor,
                                          @PathVariable("fundId")Integer fundId){

        if (this.checker == null) initChecker();

        initCheckerParams(actor, fundId);

        if (!checker.satisfy()){
            return "fail";
        }

        UserFundKey userFundKey = new UserFundKey();
        userFundKey.setUsername(actor);
        userFundKey.setFundId(fundId);
        userFundService.deleteById(userFundKey);

        return "success";
    }

    void initCheckerParams(String actor, Integer fundId){
        setActorExistParams(actor);
        setActorIsMemberParams(actor, fundId);

        setActorIsNotTreasurerParams(actor, fundId);
    }
}
