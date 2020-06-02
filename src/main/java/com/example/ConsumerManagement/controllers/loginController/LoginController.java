package com.example.ConsumerManagement.controllers.loginController;


import com.example.ConsumerManagement.models.persistence.entities.Account;
import com.example.ConsumerManagement.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    AccountService accountService;

    @GetMapping(value = "/login")
    public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = null;
        if (request.getCookies() != null)
            for (Cookie cookie: request.getCookies()){
                if (cookie.getName().equals("username")) {
                    username = cookie.getValue();
                    break;
                }
            }

        if (username != null && accountService.findById(username).isPresent()) redirect("/home", response);
        return "login";
    }

    @PostMapping(value = "/login")
    public @ResponseBody String processLogin (@ModelAttribute("account") Account account,
                                              HttpServletResponse response) throws IOException {
        String username = account.getUsername();
        String password = account.getPassword();

        Optional<Account>  optional = accountService.findById(username);
        String msg = null;

        if(optional.isEmpty()){
            msg = "Account is not exist";
        } else{
            Account auth = optional.get();
            if(auth.getUsername().equals(username) && auth.getPassword().equals(password)){
                Cookie cookie = new Cookie("username", username);
                response.addCookie(cookie);
                redirect("/home", response);
            }
            else {
                msg = "Password does not match";
            }
        }

        return msg;
    }

    private void redirect(String s, HttpServletResponse response) throws IOException {
        response.sendRedirect(s);
    }
}
