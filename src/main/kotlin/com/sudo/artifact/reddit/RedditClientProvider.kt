package com.sudo.artifact.reddit

import com.google.inject.Provider
import net.dean.jraw.RedditClient
import net.dean.jraw.oauth.Credentials
import net.dean.jraw.http.OkHttpNetworkAdapter
import net.dean.jraw.http.UserAgent
import net.dean.jraw.oauth.OAuthHelper


var name : String = "artifact-cards-bot"
var clientId : String = "vqGkZJ6rFoH7JQ"

class RedditClientProvider : Provider<RedditClient> {
    override fun get(): RedditClient {
        val userAgent = UserAgent("bot", "com.sudo.artifact", "v1.0", "artifact-card-bot")
        val credentials = Credentials.script(name, System.getProperty("reddit.password"),
                clientId, System.getProperty("reddit.secret"))
        val adapter = OkHttpNetworkAdapter(userAgent)
        return OAuthHelper.automatic(adapter, credentials)
    }
}
