/*
 * Created on 29.03.2017
 * 
 * Copyright(c) 1995 - 2017 T-Systems Multimedia Solutions GmbH
 * Riesaer Str. 5, 01129 Dresden
 * All rights reserved.
 */

package de.bettinggame;

import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Application starter and configuration.
 */
@SpringBootApplication
@EnableWebMvc
public class BettingGameWebApp extends WebMvcConfigurerAdapter {
    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(BettingGameWebApp.class, args);
    }
}
