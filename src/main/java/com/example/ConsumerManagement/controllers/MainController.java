package com.example.ConsumerManagement.controllers;


import com.example.ConsumerManagement.models.persistence.entities.Fund;
import com.example.ConsumerManagement.services.FundService;
import com.example.ConsumerManagement.services.TransactionService;
import com.example.ConsumerManagement.services.UserFundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    UserFundService userFundService;

    @Autowired
    FundService fundService;

    @Autowired
    TransactionService transactionService;

    @RequestMapping(value = "/home")
    public String home(HttpServletRequest request, HttpServletResponse response, Model model) throws ServletException, IOException {
        String username = null;
        for (Cookie cookie: request.getCookies()){
            if (cookie.getName().equals("username")) {
                username = cookie.getValue();
                break;
            }
        }
        if (username == null) response.sendRedirect("/login");
        List<Fund> fundList = new ArrayList<Fund>();
        for(Integer fundId: userFundService.findAllFundIdByUsername(username)){
            fundList.add(fundService.findById(fundId).get());
        }
        model.addAttribute("fundList", fundList);
        return "main";
    }

}
