package com.example.ConsumerManagement.controllers.fund;

import com.example.ConsumerManagement.checker.fundFormChecker.FundFormPossibilityChecker;
import com.example.ConsumerManagement.checker.fundFormChecker.FundFormValidityChecker;
import com.example.ConsumerManagement.controllers.AbstractAuthenticationRequiredController;
import com.example.ConsumerManagement.models.persistence.entities.Fund;
import com.example.ConsumerManagement.services.FundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

@Controller
public class CreateFundController extends AbstractAuthenticationRequiredController {
    @Autowired
    private FundService fundService;

    @Autowired
    FundFormValidityChecker validityChecker;
    void setValidityCheckerParams(Fund fund){
        validityChecker.setFund(fund);
    }

    @Autowired
    FundFormPossibilityChecker possibilityChecker;
    void setPossibilityCheckerParams(Fund fund){
        possibilityChecker.setFund(fund);
    }

    @Override
    public void initChecker() {
        super.initChecker();
        this.addChecker(validityChecker);
        this.addChecker(possibilityChecker);
    }

    @PostMapping("/fund")
    public @ResponseBody String create(@ModelAttribute("Fund") Fund fund,
                                       @CookieValue("owner") String owner) {

        if (checker == null) initChecker();

        LocalDate dateNow = LocalDate.now();
        String dateOfCreation = dateNow.toString();
        fund.setDateOfCreation(dateOfCreation);

        fund.setOwner(owner);

        initCheckerParams(fund, owner);

        if (!checker.satisfy()) return "fail";
        fundService.save(fund);
        return "success";
    }

    private void initCheckerParams(Fund fund, String owner) {
        setActorExistParams(owner);
        setPossibilityCheckerParams(fund);
        setValidityCheckerParams(fund);
    }
}
