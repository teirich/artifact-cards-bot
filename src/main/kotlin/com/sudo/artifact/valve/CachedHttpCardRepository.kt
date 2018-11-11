package com.sudo.artifact.valve

import com.google.common.cache.CacheBuilder
import com.google.common.cache.LoadingCache
import com.google.inject.Inject
import com.sudo.artifact.model.card.Card
import java.util.concurrent.TimeUnit

class CachedHttpCardRepository @Inject constructor(cacheLoader: HttpCardLoader) : CardRepository {
    private val seriesToCardsCache: LoadingCache<String, List<Card>> = CacheBuilder.newBuilder()
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build(cacheLoader)

    private val allSeries = listOf("00", "01")

    override fun isAValidCardName(name: String): Boolean = getCard(name) != null

    override fun getCard(name: String): Card? = getAllSeries().find { card -> name == card.cardName.english }

    private fun getAllSeries(): List<Card> = allSeries.flatMap {s -> seriesToCardsCache[s]}
}
