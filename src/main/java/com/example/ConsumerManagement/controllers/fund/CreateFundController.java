package com.example.ConsumerManagement.controllers.fund;

import com.example.ConsumerManagement.models.persistence.entities.Fund;
import com.example.ConsumerManagement.services.FundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

@Controller
public class CreateFundController {
    @Autowired
    private FundService fundService;

    @PostMapping("/fund")
    public @ResponseBody Fund create(HttpServletRequest seq, HttpServletResponse res) {
        LocalDate dateNow = LocalDate.now();
        String dateOfCreation = dateNow.toString();

        Fund fund = new Fund();
        fund.setName(seq.getParameter("name"));
        fund.setOwner(seq.getParameter("owner"));
        fund.setDateOfCreation(dateOfCreation);
        if (seq.getParameter("balance") != null)
            fund.setBalance(Double.parseDouble(seq.getParameter("balance")));

        return fundService.save(fund);
    }
}
