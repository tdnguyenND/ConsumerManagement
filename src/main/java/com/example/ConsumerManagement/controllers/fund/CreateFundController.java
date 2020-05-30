package com.example.ConsumerManagement.controllers.fund;

import com.example.ConsumerManagement.models.persistence.entities.Fund;
import com.example.ConsumerManagement.services.FundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

@RestController
public class CreateFundController {
    @Autowired
    private FundService fundService;

    @PostMapping("/fund")
    public Fund create(@ModelAttribute("Fund") Fund fund, @CookieValue String owner) {
        LocalDate dateNow = LocalDate.now();
        String dateOfCreation = dateNow.toString();
        fund.setDateOfCreation(dateOfCreation);

        fund.setOwner(owner);

        return fundService.save(fund);
    }
}
