package com.sudo.artifact.config

import com.google.api.client.http.HttpRequestFactory
import com.google.inject.Provider
import com.google.api.client.auth.oauth2.BearerToken
import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.json.JsonObjectParser

val ACCESS_TOKEN = "" //TODO:

class OauthHttpRequestFactoryProvider : Provider<HttpRequestFactory> {
    override fun get(): HttpRequestFactory {
        return HTTP_TRANSPORT.createRequestFactory { request ->
            val credential = Credential(BearerToken.authorizationHeaderAccessMethod()).setAccessToken(ACCESS_TOKEN)
            credential.initialize(request)
            request.parser = JsonObjectParser(JSON_FACTORY)
        }
    }
}
