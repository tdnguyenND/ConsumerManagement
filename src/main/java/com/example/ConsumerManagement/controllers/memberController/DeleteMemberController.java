package com.example.ConsumerManagement.controllers.memberController;

import com.example.ConsumerManagement.checker.fundAuthenticityChecker.FundMemberAuthenticityChecker;
import com.example.ConsumerManagement.checker.fundAuthenticityChecker.FundTreasurerAuthenticityChecker;
import com.example.ConsumerManagement.models.persistence.relationships.UserFundKey;
import com.example.ConsumerManagement.services.UserFundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Scope("prototype")
public class DeleteMemberController extends InteractiveController {
    @Autowired
    UserFundService userFundService;

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

    @Override
    public void initChecker() {
        super.initChecker();
        this.addChecker(actorIsTreasurer);
        this.addChecker(targetIsMember);
    }

    @RequestMapping("/{fundId}/member/delete")
    public @ResponseBody String deleteMember(@CookieValue("username")String actor,
                                             @RequestParam("target")String target,
                                             @PathVariable("fundId")Integer fundId){
        if (checker == null) initChecker();

        initCheckerParams(actor, target, fundId);

        if (!checker.satisfy()) {
            return "fail";
        }
        UserFundKey userFundKey = new UserFundKey();
        userFundKey.setUsername(target);
        userFundKey.setFundId(fundId);
        userFundService.deleteById(userFundKey);
        return "success";
    }

    private void initCheckerParams(String actor, String target, Integer fundId) {
        setActorExistParams(actor);
        setActorIsTreasurerParams(actor, fundId);

        setTargetExistParams(target);
        setTargetIsMemberParams(target, fundId);
    }
}
