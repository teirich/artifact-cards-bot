package com.sudo.artifact.pushshift

import com.sudo.artifact.model.pushshift.Comment

/**
 * https://github.com/pushshift/api
 *
 * https://api.pushshift.io/reddit/search/comment/?subreddit=Artifact&after=10m&fields=id,author,authorfull_name,body,created_utc,retrieved_on,permalink,score,subreddit
 *
 */
interface PushshiftService {
    fun findCommentsSince(lastFetch: Long) : List<Comment>
}
