package com.jeong.pokerstarter.controller

import com.jeong.pokerstarter.CardService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/cards")
class CardController(
    private val cardService: CardService
) {

    @GetMapping
    fun getRandomCards(
        @RequestParam(required = false) hold: List<String>?
    ): List<String> {
        check(hold.isNullOrEmpty() || hold.size <= 5)

        return cardService.findCardsByRandom(hold ?: listOf())
    }
}