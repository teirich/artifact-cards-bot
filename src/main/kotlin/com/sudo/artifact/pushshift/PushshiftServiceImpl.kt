package com.sudo.artifact.pushshift

import com.google.api.client.http.GenericUrl
import com.google.api.client.http.HttpRequest
import com.google.api.client.http.HttpRequestFactory
import com.google.api.client.http.HttpResponse
import com.google.gson.Gson
import com.google.inject.Inject
import com.sudo.artifact.model.pushshift.Comment
import com.sudo.artifact.model.pushshift.PushshiftResp

/**
 * https://api.pushshift.io/reddit/search/comment/?subreddit=Artifact&after=10m&fields=id,author,authorfull_name,body,created_utc,retrieved_on,permalink,score,subreddit
 */
class PushshiftServiceImpl @Inject constructor(private val gson: Gson,
                                               private val httpRequestFactory: HttpRequestFactory): PushshiftService {

    override fun findCommentsSince(lastFetch: Long): List<Comment> {
        val url = "https://api.pushshift.io/reddit/search/comment/?subreddit=TestArtifactCardBot&after=$lastFetch" +
                "&fields=id,author,authorfull_name,body,created_utc,retrieved_on,permalink,score,subreddit"
        println(url)
        val req : HttpRequest = httpRequestFactory.buildGetRequest(GenericUrl(url))
        val resp : HttpResponse = req.execute()
        val json : String = resp.parseAsString()
        val pushshiftResp: PushshiftResp = gson.fromJson<PushshiftResp>(json, PushshiftResp::class.java)
        return listOf(*pushshiftResp.data)
    }
}
