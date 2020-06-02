package com.example.ConsumerManagement.controllers.transaction;

import com.example.ConsumerManagement.checker.fundAuthenticityChecker.FundMemberAuthenticityChecker;
import com.example.ConsumerManagement.checker.fundChecker.FundExistChecker;
import com.example.ConsumerManagement.checker.fundFormChecker.FundFormPossibilityChecker;
import com.example.ConsumerManagement.checker.fundFormChecker.FundFormValidityChecker;
import com.example.ConsumerManagement.checker.transactionFormChecker.TransactionFormPossibilityChecker;
import com.example.ConsumerManagement.checker.transactionFormChecker.TransactionFormValidityChecker;
import com.example.ConsumerManagement.controllers.AbstractAuthenticationRequiredController;
import com.example.ConsumerManagement.models.persistence.entities.Fund;
import com.example.ConsumerManagement.models.persistence.entities.Transaction;
import com.example.ConsumerManagement.services.FundService;
import com.example.ConsumerManagement.services.TransactionService;
import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class CreateTransactionController extends AbstractAuthenticationRequiredController {
    @Autowired
    TransactionService transactionService;

    @Autowired
    FundService fundService;

    @Autowired
    FundExistChecker fundExist;
    void setFundExistParams(Integer fundId){
        fundExist.setFundId(fundId);
    }

    @Autowired
    TransactionFormValidityChecker validity;
    void setValidityParams(Transaction transaction){
        validity.setTransaction(transaction);
    }

    @Autowired
    TransactionFormPossibilityChecker possibility;
    void setPossibilityParams(Transaction transaction){
        possibility.setTransaction(transaction);
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
        this.addChecker(validity);
        this.addChecker(possibility);
    }

    @PostMapping("/{fundId}/transaction/create")
    public @ResponseBody String create(@ModelAttribute("transaction")Transaction transaction,
                                    @PathVariable("fundId")Integer fundId,
                                    @CookieValue("username")String actor){
        if (checker == null) initChecker();

        LocalDate dateNow = LocalDate.now();
        String dateOfCreation = dateNow.toString();
        transaction.setDateOfCreation(dateOfCreation);
        transaction.setActor(actor);

        transaction.setActor(actor);
        transaction.setFundId(fundId);

        initCheckerParams(transaction, actor, fundId);

        if (!checker.satisfy()) return "fail";

        transactionService.save(transaction);
        Fund fund = fundService.findById(fundId).get();
        double balance = fund.getBalance();
        switch (transaction.getType()){
            case "Reduce":
            case "Giảm":{
                balance -= transaction.getAmountOfMoney();
                break;
            }
            case "Increase":
            case "Tăng":{
                balance += transaction.getAmountOfMoney();
                break;
            }
        }
        fundService.updateBalance(fundId, balance);

        return "success";
    }

    private void initCheckerParams(Transaction transaction, String actor, Integer fundId) {
        setActorExistParams(actor);
        setActorIsMemberParams(actor, fundId);
        setFundExistParams(fundId);
        setPossibilityParams(transaction);
        setValidityParams(transaction);
    }
}
