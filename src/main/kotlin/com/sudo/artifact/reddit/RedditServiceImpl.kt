package com.sudo.artifact.reddit

import com.google.inject.Inject
import com.sudo.artifact.model.card.Card
import com.sudo.artifact.pushshift.CommentMatch
import com.sudo.artifact.valve.CardRepository
import net.dean.jraw.ApiException
import net.dean.jraw.RedditClient

class RedditServiceImpl @Inject constructor(private val redditClient: RedditClient,
                                            private val cardRepository: CardRepository) : RedditService {

    private fun resolveReferences(card: Card): CardTree {
        val refs  = arrayListOf<ShortRef>()
        for(ref in card.references) {
            val found = cardRepository.getCard(ref.cardId)
            if(found != null) {
                refs.add(ShortRef(found, ref.refType))
            }
        }
        return CardTree(card, refs)
    }

    override fun replyToComment(commentMatch: CommentMatch) {
        //write reply
        val reply = ReplyBuilder(commentMatch.matches.map { resolveReferences(it) }.toList()).get()
        println("replying to comment $commentMatch.comment.id")
        println(reply)
        try {
            redditClient.comment(commentMatch.comment.id).reply(reply)
        }
        catch (e: ApiException) {
            println(e)
        }
    }
}
