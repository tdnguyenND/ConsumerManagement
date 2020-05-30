package com.example.ConsumerManagement.controllers.memberController;

import com.example.ConsumerManagement.checker.fundAuthenticityChecker.FundMemberAuthenticityChecker;
import com.example.ConsumerManagement.checker.fundChecker.FundExistChecker;
import com.example.ConsumerManagement.controllers.AbstractAuthenticationRequiredController;
import com.example.ConsumerManagement.services.UserFundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GetAllMemberController extends AbstractAuthenticationRequiredController {

    @Autowired
    UserFundService userFundService;

    @Autowired
    FundExistChecker fundExist;
    void setFundExistParams(Integer fundId){
        fundExist.setFundId(fundId);
    }

    @Autowired
    FundMemberAuthenticityChecker actorIsMember;
    void setActorIsMemberParams(String actor, Integer fundId){
        actorIsMember.setUsername(actor);
        actorIsMember.setFundId(fundId);
    }

    @Override
    public void initChecker() {
        super.initChecker();
        this.addChecker(fundExist);
        this.addChecker(actorIsMember);
    }

    @GetMapping("/{fundId}/member/getAll")
    public @ResponseBody Iterable<String> getAllMember(@PathVariable("fundId") Integer fundId,
                                                       @CookieValue("username")String actor){
        if (checker == null) initChecker();
        initCheckerParams(actor, fundId);

        if (!checker.satisfy()) return null;
        else return userFundService.findAllMembersByFundId(fundId);
    }

    private void initCheckerParams(String actor, Integer fundId) {
        setActorExistParams(actor);
        setFundExistParams(fundId);
        setActorIsMemberParams(actor, fundId);
    }
}
