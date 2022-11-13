package com.jeong.pokerstarter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PokerStarterApplication

fun main(args: Array<String>) {
    runApplication<PokerStarterApplication>(*args)
}
