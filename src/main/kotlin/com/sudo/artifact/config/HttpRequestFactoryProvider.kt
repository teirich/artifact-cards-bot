package com.sudo.artifact.config

import com.google.api.client.auth.oauth2.BearerToken
import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.http.HttpRequestFactory
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.JsonObjectParser
import com.google.api.client.json.gson.GsonFactory
import com.google.inject.Provider

val HTTP_TRANSPORT = NetHttpTransport()
val JSON_FACTORY = GsonFactory()

class HttpRequestFactoryProvider : Provider<HttpRequestFactory> {
    override fun get(): HttpRequestFactory {
        return HTTP_TRANSPORT.createRequestFactory { request ->
            val credential = Credential(BearerToken.authorizationHeaderAccessMethod()).setAccessToken(ACCESS_TOKEN)
            credential.initialize(request)
            request.parser = JsonObjectParser(JSON_FACTORY)
        }
    }
}
