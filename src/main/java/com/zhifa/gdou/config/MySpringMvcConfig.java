package com.zhifa.gdou.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootConfiguration
public class MySpringMvcConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private LoginConfig loginConfig;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginConfig).excludePathPatterns("/manager/login")
                .excludePathPatterns("/manager_login")
                .addPathPatterns("/**");
    }
}
