package com.sudo.artifact.pushshift

import io.reactivex.Observable

interface CommentListener {
    fun startListening(): Observable<CommentMatch>
}
