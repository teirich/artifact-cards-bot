package com.sudo.artifact.pushshift

import com.sudo.artifact.model.pushshift.Comment
import io.reactivex.Observable

interface CommentListener {
    fun startListening(): Observable<Collection<Comment>>
}
