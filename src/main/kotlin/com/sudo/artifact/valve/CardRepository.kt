package com.sudo.artifact.valve

import com.sudo.artifact.model.card.Card

interface CardRepository {
    fun isAValidCardName(name: String): Boolean
    fun getCard(name: String): Card?
}
