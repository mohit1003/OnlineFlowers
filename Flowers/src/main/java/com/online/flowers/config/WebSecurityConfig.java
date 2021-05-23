package com.online.flowers.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http
        .csrf().disable().authorizeRequests()
         .antMatchers("/").permitAll()
         .antMatchers("/h2-console/**").permitAll();

    	http.headers().frameOptions().sameOrigin();
        // all requests are authenticated
        
        http.cors();
    }
}