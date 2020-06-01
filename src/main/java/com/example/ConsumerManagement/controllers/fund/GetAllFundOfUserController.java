package com.example.ConsumerManagement.controllers.fund;

import com.example.ConsumerManagement.controllers.AbstractAuthenticationRequiredController;
import com.example.ConsumerManagement.services.UserFundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GetAllFundOfUserController extends AbstractAuthenticationRequiredController {
    @Autowired
    UserFundService userFundService;

    @Override
    public void initChecker() {
        super.initChecker();
    }

    @GetMapping("/fund/getAll")
    public @ResponseBody
    Iterable<Integer> getAll(@CookieValue("username")String actor){
        if (checker == null) initChecker();

        initCheckerParams(actor);

        if (!checker.satisfy()) return null;

        else return userFundService.findAllFundIdByUsername(actor);
    }

    private void initCheckerParams(String actor) {
        setActorExistParams(actor);
    }
}
