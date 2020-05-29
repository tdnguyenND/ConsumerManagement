package com.example.ConsumerManagement.controllers.memberController;

import com.example.ConsumerManagement.checker.fundAuthenticityChecker.FundMemberAuthenticityChecker;
import com.example.ConsumerManagement.models.persistence.entities.User;
import com.example.ConsumerManagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@Scope("prototype")
public class GetMemberDetailsController extends InteractiveController {
    @Autowired
    FundMemberAuthenticityChecker actorIsMember;
    protected void setActorIsMemberParams(String target, Integer fundId){
        actorIsMember.setFundId(fundId);
        actorIsMember.setUsername(target);
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
            this.addChecker(actorIsMember);
            this.addChecker(targetIsMember);
    }

    @Autowired
    UserService userService;

    @RequestMapping("/{fundId}/member/details")
    public @ResponseBody Optional<User> getMemberDetails(@CookieValue("username")String actor,
                                                         @RequestParam("target")String target,
                                                         @PathVariable("fundId")Integer fundId){
        if (checker == null) initChecker();

        initCheckerParams(actor, target, fundId);

        if (!checker.satisfy()) return Optional.empty();

        return userService.findById(target);
    }

    private void initCheckerParams(String actor, String target, Integer fundId) {
        setActorExistParams(actor);
        setActorIsMemberParams(actor, fundId);

        setTargetExistParams(target);
        setTargetIsMemberParams(target, fundId);
    }
}
