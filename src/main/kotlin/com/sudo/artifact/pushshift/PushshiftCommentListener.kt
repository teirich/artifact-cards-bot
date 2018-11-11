package com.sudo.artifact.pushshift

import com.google.inject.Inject
import com.sudo.artifact.getAllOperatorMatches
import com.sudo.artifact.model.card.Card
import com.sudo.artifact.model.pushshift.Comment
import com.sudo.artifact.valve.CardRepository
import io.reactivex.Observable
import java.io.IOException
import java.util.*

fun getCurrentTimeInSec(): Long {
    return Date().time / 1000
}

fun getLastRetrieved(comments: List<Comment>): Long? {
    return comments.map { comment -> comment.retrievedOn }
            .max()
}

data class CommentMatch(val comment: Comment, val matches: List<Card>) {
    fun hasAMatch(): Boolean {
        return matches.isNotEmpty()
    }
}

class PushshiftCommentListener @Inject constructor(private val pushshiftService: PushshiftService,
                                                   private val cardService: CardRepository): CommentListener {
    var _lastFetchSec = getCurrentTimeInSec()

    fun getCardsMentionedInComment(comment: Comment) : List<Card>{
        return getAllOperatorMatches(comment.body).mapNotNull { match -> cardService.getCard(match) }.toList()
    }

    override fun startListening(): Observable<CommentMatch> {

        return Observable.create { emitter -> run {
            while(!emitter.isDisposed) {
                try {
                    println("lastFetch=$_lastFetchSec")
                    val comments: List<Comment> = pushshiftService.findCommentsSince(_lastFetchSec)

                    val lastRetrieved = getLastRetrieved(comments)
                    if (lastRetrieved != null) {
                        _lastFetchSec = lastRetrieved
                    }

                    comments.forEach {  println("debug:$it") }

                    comments
                        .map { comment -> CommentMatch(comment, getCardsMentionedInComment(comment)) }
                        .filter { match -> match.hasAMatch() }
                        .forEach(emitter::onNext)

                    Thread.sleep(10000L)
                }
                catch(e: IOException) {
                    println(e)
                }
            }
        }}
    }
}

