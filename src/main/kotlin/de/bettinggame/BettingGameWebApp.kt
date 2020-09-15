package de.bettinggame

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BettingGameWebApp

fun main(args: Array<String>) {
    runApplication<BettingGameWebApp>(*args)
}
