package com.example.ConsumerManagement.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class MainController {
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
        model.addAttribute("name", "world");
        return "homePage";
    }
}
