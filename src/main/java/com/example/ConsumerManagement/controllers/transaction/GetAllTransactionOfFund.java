package com.example.ConsumerManagement.controllers.transaction;

import com.example.ConsumerManagement.checker.fundAuthenticityChecker.FundMemberAuthenticityChecker;
import com.example.ConsumerManagement.checker.fundChecker.FundExistChecker;
import com.example.ConsumerManagement.controllers.AbstractAuthenticationRequiredController;
import com.example.ConsumerManagement.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GetAllTransactionOfFund extends AbstractAuthenticationRequiredController {

    @Autowired
    TransactionService transactionService;

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

    @GetMapping("/{fundId}/transaction/getAll")
    public @ResponseBody Object getAllTransaction(@PathVariable("fundId")Integer fundId,
                             @CookieValue("username")String actor){
        if (checker == null) initChecker();

        initCheckerParams(fundId, actor);

        if (!checker.satisfy()) return "Fail to get all transaction";

        return transactionService.findAllByFundId(fundId);
    }

    private void initCheckerParams(Integer fundId, String actor) {
        setActorExistParams(actor);
        setFundExistParams(fundId);
        setActorIsMemberParams(actor, fundId);
    }
}
