package com.example.ConsumerManagement.controllers.memberController;

import com.example.ConsumerManagement.checker.Checker;
import com.example.ConsumerManagement.checker.fundAuthenticityChecker.FundMemberAuthenticityChecker;
import com.example.ConsumerManagement.checker.fundAuthenticityChecker.FundTreasurerAuthenticityChecker;
import com.example.ConsumerManagement.checker.fundAuthenticityChecker.NotATreasurerOfFundChecker;
import com.example.ConsumerManagement.services.FundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class TransferTreasurerController extends InteractiveController {
    @Autowired
    FundTreasurerAuthenticityChecker actorIsTreasurer;
    protected void setActorIsTreasurerParams(String actor, Integer fundId){
        this.actorIsTreasurer.setUsername(actor);
        this.actorIsTreasurer.setFundId(fundId);
    }

    @Autowired
    FundMemberAuthenticityChecker targetIsMember;
    protected void setTargetIsMemberParams(String target, Integer fundId){
        targetIsMember.setFundId(fundId);
        targetIsMember.setUsername(target);
    }

    @Autowired
    NotATreasurerOfFundChecker targetIsNotTreasurer;
    protected void setTargetIsNotTreasurerParams(String actor, Integer fundId){
        this.targetIsNotTreasurer.setUsername(actor);
        this.targetIsNotTreasurer.setFundId(fundId);
    }

    @Autowired
    FundService fundService;

    @Override
    public void initChecker() {
        super.initChecker();
        addChecker(actorIsTreasurer);
        addChecker(targetIsMember);
        addChecker(targetIsNotTreasurer);
    }

    @RequestMapping("/{fundId}/member/transfer-treasurer")
    public @ResponseBody String transferTreasurer(@CookieValue("username")String actor,
                                                  @RequestParam("target")String target,
                                                  @PathVariable("fundId")Integer fundId){

        if (checker == null) initChecker();

        initCheckerParams(actor, target, fundId);

        if (!checker.satisfy()) {
            return "fail";
        }

        fundService.changeOwner(fundId, target);
        return "success";
    }

    private void initCheckerParams(String actor, String target, Integer fundId) {
        setActorExistParams(actor);
        setActorIsTreasurerParams(actor, fundId);

        setTargetExistParams(target);
        setTargetIsNotTreasurerParams(target, fundId);
        setTargetIsMemberParams(target, fundId);
    }
}
