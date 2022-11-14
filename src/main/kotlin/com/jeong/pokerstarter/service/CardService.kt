package com.jeong.pokerstarter.service

import com.jeong.pokerstarter.repository.CardRepository
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

@Service
class CardService(
    private val cardRepository: CardRepository
) {

    //TODO enum 으로 만들것인지 로직 보고 결정하기
    lateinit var cards: List<String>

    @PostConstruct
    fun init() {
        cards = cardRepository.findAll().map {
            it.type
        }
    }

    fun findCardsByRandom(except: List<String>): List<String> {
        val newCards = cards.toMutableList()
        newCards.removeAll(except)

        return newCards.asSequence().shuffled().take(CARD_NUMBER - except.size).toList()
    }

    companion object {
        const val CARD_NUMBER = 5
    }
}