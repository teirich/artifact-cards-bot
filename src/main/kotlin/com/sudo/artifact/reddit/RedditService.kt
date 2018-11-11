package com.sudo.artifact.reddit

import com.sudo.artifact.pushshift.CommentMatch

interface RedditService {
    fun replyToComment(commentMatch: CommentMatch)
}
