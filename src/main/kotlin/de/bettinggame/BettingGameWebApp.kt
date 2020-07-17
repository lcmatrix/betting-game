package de.bettinggame

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer

@EnableAuthorizationServer
@SpringBootApplication
class BettingGameWebApp

fun main(args: Array<String>) {
    SpringApplication.run(BettingGameWebApp::class.java, *args)
}
