package de.bettinggame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Application starter and configuration.
 */
@SpringBootApplication
public class BettingGameWebApp extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(BettingGameWebApp.class, args);
    }
}
