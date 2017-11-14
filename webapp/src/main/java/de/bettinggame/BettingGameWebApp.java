/*
 * Created on 29.03.2017
 * 
 * Copyright(c) 1995 - 2017 T-Systems Multimedia Solutions GmbH
 * Riesaer Str. 5, 01129 Dresden
 * All rights reserved.
 */

package de.bettinggame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Application starter and configuration.
 */
@SpringBootApplication
// @EnableWebMvc
public class BettingGameWebApp extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(BettingGameWebApp.class, args);
    }
}
