package com.sudo.artifact.valve

import com.google.api.client.http.GenericUrl
import com.google.api.client.http.HttpRequest
import com.google.api.client.http.HttpRequestFactory
import com.google.api.client.http.HttpResponse
import com.google.common.cache.CacheLoader
import com.google.gson.Gson
import com.google.inject.Inject
import com.sudo.artifact.model.card.Card
import com.sudo.artifact.model.card.CardSetMetadata
import com.sudo.artifact.model.card.CardSetResponse

/**
 * https://github.com/ValveSoftware/ArtifactDeckCode
 */
class HttpCardLoader @Inject constructor(private val gson: Gson, private val httpRequestFactory: HttpRequestFactory)
    : CacheLoader<String, List<Card>>() {

    fun requestCardSetMetadata(setId: String): CardSetMetadata {
        val req : HttpRequest = httpRequestFactory.buildGetRequest(GenericUrl("https://playartifact.com/cardset/${setId}"))
        val resp : HttpResponse = req.execute()
        val json: String = resp.parseAsString()
        return gson.fromJson(json, CardSetMetadata::class.java)
    }

    fun requestCardSet(metadata: CardSetMetadata) : CardSetResponse {
        val cardUrl = GenericUrl(metadata.cdnRoot + metadata.url)
        val req : HttpRequest = httpRequestFactory.buildGetRequest(cardUrl)
        val resp : HttpResponse = req.execute()
        val json: String = resp.parseAsString()
        return gson.fromJson(json, CardSetResponse::class.java)
    }

    override fun load(key: String): List<Card> {
        val cardSetMetadataResp : CardSetMetadata = requestCardSetMetadata(key)
        val cardSetResp : CardSetResponse = requestCardSet(cardSetMetadataResp)
        return listOf(*cardSetResp.cardSet.cardList)
    }
}
