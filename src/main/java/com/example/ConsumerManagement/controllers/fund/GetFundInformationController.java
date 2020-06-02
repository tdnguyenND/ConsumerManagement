package com.example.ConsumerManagement.controllers.fund;

import com.example.ConsumerManagement.checker.fundAuthenticityChecker.FundMemberAuthenticityChecker;
import com.example.ConsumerManagement.controllers.AbstractAuthenticationRequiredController;
import com.example.ConsumerManagement.models.persistence.entities.Fund;
import com.example.ConsumerManagement.models.persistence.entities.Transaction;
import com.example.ConsumerManagement.models.repositories.TransactionRepository;
import com.example.ConsumerManagement.services.FundService;
import com.example.ConsumerManagement.services.TransactionService;
import com.example.ConsumerManagement.services.UserFundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class GetFundInformationController extends AbstractAuthenticationRequiredController {
    @Autowired
    private FundService fundService;

    @Autowired
    UserFundService userFundService;

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

    @GetMapping(value = "/{fundId}")
    public String getInfo(@PathVariable int fundId,
                          @CookieValue("username")String actor,
                          Model model){
        if (checker == null) initChecker();

        initCheckerParams(actor, fundId);

        if (!checker.satisfy()) return null;

        model.addAttribute("fund", fundService.findById(fundId).get());
        model.addAttribute("members", userFundService.findAllMembersByFundId(fundId));

        return "fundInfo";
    }

    private void initCheckerParams(String actor, int fundId) {
        setActorIsMemberParams(actor, fundId);
        setActorExistParams(actor);
    }
}
