package com.sudo.artifact

import com.google.api.client.http.HttpRequestFactory
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.inject.*
import com.google.inject.name.Names
import com.sudo.artifact.config.HttpRequestFactoryProvider
import com.sudo.artifact.config.OauthHttpRequestFactoryProvider
import com.sudo.artifact.model.card.Card
import com.sudo.artifact.pushshift.CommentListener
import com.sudo.artifact.pushshift.PushshiftCommentListener
import com.sudo.artifact.pushshift.PushshiftService
import com.sudo.artifact.pushshift.PushshiftServiceImpl
import com.sudo.artifact.reddit.RedditClientProvider
import com.sudo.artifact.reddit.RedditService
import com.sudo.artifact.reddit.RedditServiceImpl
import com.sudo.artifact.valve.CachedHttpCardRepository
import com.sudo.artifact.valve.CardRepository
import com.sudo.artifact.valve.HttpCardLoader
import net.dean.jraw.RedditClient


fun main(args: Array<String>) {
    //[x]start background process to fetch valve's json & build cache
    //record time epoch
    //fetch reddit comments from pushshift
    //https://pushshift.io/api-parameters/ https://api.pushshift.io/reddit/search/comment/?subreddit=Artifact&after=10m
    //record time epoch
    //parse comments for [[Card Name]]
    //Oauth & reply to any comment which matches
    //timeout 1 min
    val injector: Injector = Guice.createInjector(object : AbstractModule() {
        override fun configure() {
            bind(RedditService::class.java).to(RedditServiceImpl::class.java).asEagerSingleton()
            bind(RedditClient::class.java).toProvider(RedditClientProvider::class.java).asEagerSingleton()
            bind(CommentListener::class.java).to(PushshiftCommentListener::class.java).asEagerSingleton()
            bind(PushshiftService::class.java).to(PushshiftServiceImpl::class.java).asEagerSingleton()
            bind(CardRepository::class.java).to(CachedHttpCardRepository::class.java).asEagerSingleton()
            bind(HttpRequestFactory::class.java).toProvider(HttpRequestFactoryProvider::class.java).asEagerSingleton()
            bind(HttpRequestFactory::class.java).annotatedWith(Names.named("oauth"))
                    .toProvider(OauthHttpRequestFactoryProvider::class.java).asEagerSingleton()
            bind(HttpCardLoader::class.java).asEagerSingleton()
        }

        @Provides
        fun gsonBuilder(): Gson {
            return GsonBuilder().create()
        }
    })

    val cardRepository: CardRepository = injector.getInstance(CardRepository::class.java)
    val commentListener: CommentListener = injector.getInstance(CommentListener::class.java)
    val redditService: RedditService = injector.getInstance(RedditService::class.java)

    val found: Card? = cardRepository.getCard("Meepo")
    println("Found? (${found})")
    val found2: Card? = cardRepository.getCard("Divided We Stand")
    println("Found2? (${found2})")
    val spell: Card? = cardRepository.getCard("Coup de Grace")
    println("Spell ($spell)")
    val item: Card? = cardRepository.getCard("Book of the Dead")
    println("Item ($item)")

    commentListener.startListening().subscribe { match -> redditService.replyToComment(match) }
}
