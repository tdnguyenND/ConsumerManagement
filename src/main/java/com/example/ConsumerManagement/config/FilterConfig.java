package com.example.ConsumerManagement.config;

import com.example.ConsumerManagement.filter.AuthenticFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<AuthenticFilter> filter(){
        FilterRegistrationBean<AuthenticFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthenticFilter());
        registrationBean.addUrlPatterns("/home", "/", "/fund/*", "/{fundId}/*");

        return registrationBean;
    }
}
