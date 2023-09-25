package com.exam.examserver.configuration;

import com.exam.examserver.filter.RequestResponseLogger;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    FilterRegistrationBean<RequestResponseLogger> createLoggers(RequestResponseLogger requestResponseLogger) {
        FilterRegistrationBean<RequestResponseLogger> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(requestResponseLogger);
        registrationBean.addUrlPatterns("/user/create", "/user/getUsername/*");
        return registrationBean;
    }
}

