package com.sudo.artifact.reddit

import com.google.inject.Inject
import com.sudo.artifact.model.card.Card
import com.sudo.artifact.pushshift.CommentMatch
import net.dean.jraw.RedditClient

class RedditServiceImpl @Inject constructor(private val redditClient: RedditClient) : RedditService {
    fun cardImgLink(card: Card): String {
        return "[${card.cardName.english}](${card.largeImage.default})"
    }

    override fun replyToComment(commentMatch: CommentMatch) {
        //write reply
        val prefix = "Artifact Card Bot"
        val postfix = "Thank You"
        val replyText = commentMatch.matches.map {cardImgLink(it)}.joinToString("\n")
        println("replying to comment $commentMatch.comment.id")
        redditClient.comment(commentMatch.comment.id).reply("$prefix\n$replyText\n$postfix")
    }
}
