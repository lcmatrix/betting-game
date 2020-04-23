package de.bettinggame

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class BettingGameWebApp

fun main(args: Array<String>) {
    SpringApplication.run(BettingGameWebApp::class.java, *args)
}
