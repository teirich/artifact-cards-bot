package com.sudo.artifact.pushshift

import com.google.inject.Inject
import com.sudo.artifact.model.pushshift.Comment
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

class PushshiftCommentListener @Inject constructor(private val pushshiftService: PushshiftService): CommentListener {
    var _lastFetchSec = getCurrentTimeInSec()

    override fun startListening(): Observable<Collection<Comment>> {

        return Observable.create { emitter -> run {
            while(!emitter.isDisposed) {
                try {
                    println("lastFetch=$_lastFetchSec")
                    val comments: List<Comment> = pushshiftService.findCommentsSince(_lastFetchSec)

                    val lastRetrieved = getLastRetrieved(comments)
                    if (lastRetrieved != null) {
                        _lastFetchSec = lastRetrieved
                    }

                    val matchedComments = comments.filter { comment -> true } //TODO: detectOperator(comment.body) }
                    if (matchedComments.isNotEmpty())
                        emitter.onNext(matchedComments)
                    Thread.sleep(10000L)
                }
                catch(e: IOException) {
                    println(e)
                }
            }
        }}
    }
}

