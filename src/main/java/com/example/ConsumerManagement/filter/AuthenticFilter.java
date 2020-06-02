package com.example.ConsumerManagement.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            response.sendRedirect("/login");
        }
        else {
            boolean login =false;
            for (Cookie cookie : ((HttpServletRequest) servletRequest).getCookies()) {
                if (cookie.getName().equals("username")) {
                    filterChain.doFilter(servletRequest, servletResponse);
                    login = true;
                    break;
                }
            }
            if (!login) response.sendRedirect("/login");
        }
    }
}
