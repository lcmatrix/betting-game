/*
 * Created on 04.07.2017
 * 
 * Copyright(c) 1995 - 2017 T-Systems Multimedia Solutions GmbH
 * Riesaer Str. 5, 01129 Dresden
 * All rights reserved.
 */

package de.bettinggame;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Web security configuration.
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/", "/static/**", "/index", "/favicon.ico", "/register").permitAll()
                    .antMatchers("/secure/**").hasAnyRole("ROLE_USER", "ROLE_ADMIN")
                .and()
                .formLogin()
                    .loginPage("/login")
                    .failureForwardUrl("/error")
                    .successForwardUrl("/index")
                    .permitAll()
                .and()
                .logout()
                    .logoutSuccessUrl("/index")
                    .invalidateHttpSession(true)
                    .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("USER", "ADMIN")
        .and().withUser("user").password("user").roles("USER");
    }
}
