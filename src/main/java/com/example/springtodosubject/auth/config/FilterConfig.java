package com.example.springtodosubject.auth.config;

import com.example.springtodosubject.auth.filter.AuthFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<AuthFilter> authFilter() {
        FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthFilter());
        registrationBean.addUrlPatterns("/*"); // 모든 요청에 적용(AuthFilter가 로그인/로그아웃 요청은 제외시킴)
        registrationBean.setOrder(1); // 필터 실행 순서 : 첫번째
        return registrationBean;
    }
}
