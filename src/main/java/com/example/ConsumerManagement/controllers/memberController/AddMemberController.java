package com.example.ConsumerManagement.controllers.memberController;

import com.example.ConsumerManagement.checker.fundAuthenticityChecker.FundTreasurerAuthenticityChecker;
import com.example.ConsumerManagement.checker.fundAuthenticityChecker.NotAMemberOfFundChecker;
import com.example.ConsumerManagement.models.persistence.relationships.UserFund;
import com.example.ConsumerManagement.services.UserFundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
public class AddMemberController extends InteractiveController {
    @Autowired UserFundService userFundService;

    @Autowired FundTreasurerAuthenticityChecker actorIsTreasurer;
    protected void setActorIsTreasurerParams(String actor, Integer fundId){
        this.actorIsTreasurer.setUsername(actor);
        this.actorIsTreasurer.setFundId(fundId);
    }

    @Autowired NotAMemberOfFundChecker targetIsNotMember;
    protected void setTargetIsNotMemberParams(String target, Integer fundId){
        this.targetIsNotMember.setUsername(target);
        this.targetIsNotMember.setFundId(fundId);
    }

    @Override
    public void initChecker() {
        super.initChecker();
        this.addChecker(actorIsTreasurer);
        this.addChecker(targetIsNotMember);
    }

    @RequestMapping("/{fundId}/member/add")
    public @ResponseBody String addMember(@CookieValue("username")String actor,
                                          @RequestParam("target")String target,
                                          @PathVariable("fundId")Integer fundId){
        if (checker == null) initChecker();
        initCheckerParameters(actor, target, fundId);

        if (!checker.satisfy()) {
            return "fail";
        }
        UserFund userFund = new UserFund();
        userFund.setUsername(target);
        userFund.setFundId(fundId);
        userFund.setDateOfParticipant(LocalDate.now().toString());

        userFundService.save(userFund);
        return "success";
    }

    private void initCheckerParameters(String actor, String target, Integer fundId) {
        setActorExistParams(actor);
        setActorIsTreasurerParams(actor, fundId);

        setTargetExistParams(target);
        setTargetIsNotMemberParams(target, fundId);
    }
}
