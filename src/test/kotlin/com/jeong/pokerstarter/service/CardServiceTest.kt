package com.jeong.pokerstarter.service

import com.jeong.pokerstarter.entity.Card
import com.jeong.pokerstarter.repository.CardRepository
import com.jeong.pokerstarter.service.CardService.Companion.CARD_NUMBER
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

internal class CardServiceTest(
) : BehaviorSpec({
    val repository = mockk<CardRepository>()
    val service = CardService(repository)

    val cards = listOf(
        "AD", "2D", "3D", "4D", "5D", "6D", "7D", "8D", "9D", "10D", "JD", "QD", "KD",
        "AH", "2H", "3H", "4H", "5H", "6H", "7H", "8H", "9H", "10H", "JH", "QH", "KH",
        "AS", "2S", "3S", "4S", "5S", "6S", "7S", "8S", "9S", "10S", "JS", "QS", "KS",
        "AC", "2C", "3C", "4C", "5C", "6C", "7C", "8C", "9C", "10C", "JC", "QC", "KC"
    )
    every { repository.findAll() } returns cards.map { Card(type = it) }

    given("invoke findCardsByRandom") {
        `when`("list 비어있을 때") {
            val actual = service.findCardsByRandom(except = listOf())
            then("return 임의의 카드 5장") {
                actual.distinct().size shouldBe CARD_NUMBER
                cards.containsAll(actual).shouldBeTrue()
            }
        }
        `when`("3개원소 list 를 주었을 때") {
            val holdCount = 3
            val holdCards = cards.shuffled().take(holdCount)
            val actual = service.findCardsByRandom(except = holdCards)
            then("return 임의의 카드 2장 && 겹치면 안된다") {
                actual.distinct().size shouldBe CARD_NUMBER - holdCount
                holdCards.forEach { actual.contains(it).shouldBeFalse() }
            }
        }
        `when`("5개 원소 list 를 주었을 때") {
            val holdCount = 5
            val holdCards = cards.shuffled().take(holdCount)
            val actual = service.findCardsByRandom(except = holdCards)
            then("return 카드를 아무것도 주지 않는다.") {
                actual.size shouldBe 0
            }
        }
    }
})