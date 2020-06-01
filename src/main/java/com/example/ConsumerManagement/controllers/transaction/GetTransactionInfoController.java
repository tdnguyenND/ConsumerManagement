package com.example.ConsumerManagement.controllers.transaction;

import com.example.ConsumerManagement.checker.fundAuthenticityChecker.FundMemberAuthenticityChecker;
import com.example.ConsumerManagement.checker.fundChecker.FundExistChecker;
import com.example.ConsumerManagement.checker.transactionChecker.TransactionExistChecker;
import com.example.ConsumerManagement.controllers.AbstractAuthenticationRequiredController;
import com.example.ConsumerManagement.models.persistence.entities.Transaction;
import com.example.ConsumerManagement.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Optional;

@RestController
public class GetTransactionInfoController extends AbstractAuthenticationRequiredController {

    @Autowired
    private TransactionService transactionService;

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

    @Autowired
    TransactionExistChecker transactionExist;
    void setTransactionExistParams(Integer transactionId){
        transactionExist.setTransactionId(transactionId);
    }

    @Override
    public void initChecker() {
        super.initChecker();
        this.addChecker(fundExist);
        this.addChecker(actorIsMember);
        this.addChecker(transactionExist);
    }

    @GetMapping("/{fundId}/transaction/{transactionId}")
    public @ResponseBody Object getInfo(@PathVariable("fundId") Integer fundId,
                                        @PathVariable("transactionId")Integer transactionId,
                                        @CookieValue("username")String actor){
        if (checker == null) initChecker();

        initCheckerParams(fundId, transactionId, actor);

        if (!checker.satisfy()) return "Fail to get transaction";

        return transactionService.findById(transactionId);
    }

    private void initCheckerParams(Integer fundId, Integer transactionId, String actor) {
        setActorExistParams(actor);
        setActorIsMemberParams(actor, fundId);
        setFundExistParams(fundId);
        setTransactionExistParams(transactionId);
    }
}
