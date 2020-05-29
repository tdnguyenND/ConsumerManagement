package com.example.ConsumerManagement.controller;


import com.example.ConsumerManagement.models.persistence.entities.Account;
import com.example.ConsumerManagement.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    AccountService accountService;

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login (Model model){
        model.addAttribute("error",new String());
        model.addAttribute("account",new Account());
        return "loginPage";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ModelAndView processLogin (@ModelAttribute("account") Account account, HttpServletResponse response){

        ModelAndView model = new ModelAndView();

        String username = account.getUsername();
        String password = account.getPassword();
        Optional<Account>  optional = accountService.findById(username);
        if(optional.isEmpty()){
            model.addObject("error","username not exists");
            model.setViewName("loginPage");
        } else{
            Account auth = optional.get();
            if(auth.getUsername().equals(username) && auth.getPassword().equals(password)){
                Cookie cookie = new Cookie("username", username);
                response.addCookie(cookie);
                model.setViewName("homePage");
            }
            else {
                model.addObject("error","password not match with username");
                model.setViewName("loginPage");
            }
        }
        return model;
    }
}
