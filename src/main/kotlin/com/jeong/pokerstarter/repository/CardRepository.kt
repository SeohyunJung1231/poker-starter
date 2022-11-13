package com.jeong.pokerstarter.repository

import com.jeong.pokerstarter.entity.Card
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CardRepository: JpaRepository<Card, Long> {

}