package com.example.ConsumerManagement.controllers.fund;

import com.example.ConsumerManagement.checker.fundAuthenticityChecker.FundMemberAuthenticityChecker;
import com.example.ConsumerManagement.controllers.AbstractAuthenticationRequiredController;
import com.example.ConsumerManagement.models.persistence.entities.Fund;
import com.example.ConsumerManagement.services.FundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class GetFundInfomationController extends AbstractAuthenticationRequiredController {
    @Autowired
    private FundService fundService;

    @Autowired
    FundMemberAuthenticityChecker actorIsMember;
    void setActorIsMemberParams(String actor, Integer fundId){
        actorIsMember.setFundId(fundId);
        actorIsMember.setUsername(actor);
    }

    @Override
    public void initChecker() {
        super.initChecker();
        this.addChecker(actorIsMember);
    }

    @GetMapping(value = "/fund/{fundId}")
    public Optional<Fund> getInfo(@PathVariable int fundId,
                                  @CookieValue("username")String actor){
        if (checker == null) initChecker();

        initCheckerParams(actor, fundId);

        if (!checker.satisfy()) return Optional.empty();

        return fundService.findById(fundId);
    }

    private void initCheckerParams(String actor, int fundId) {
        setActorIsMemberParams(actor, fundId);
        setActorExistParams(actor);
    }
}
