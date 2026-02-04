package com.aidaml.cc.demo.configuration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aidaml.cc.demo.security.jwt.JwtTokenFilter;

@Configuration
public class FilterConfiguration {
    
    @Bean
    public FilterRegistrationBean<JwtTokenFilter> jwtFilter() {

        FilterRegistrationBean<JwtTokenFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new JwtTokenFilter());
        registrationBean.addUrlPatterns("/users/*"); // users is the only password protected route.

        return registrationBean;
    }

}
