package com.sudo.artifact.reddit

import com.google.common.base.CharMatcher
import com.sudo.artifact.model.card.Card
import org.jsoup.Jsoup

data class ShortRef (val card: Card,
                     val type: String)

data class CardTree(val parent: Card,
                    val references: List<ShortRef>) {
    fun hasReferences():Boolean = references.isNotEmpty()
}

class ReplyBuilder(private val cards: List<CardTree>) {
    private val sp: String = "    \n"

    private fun escHtml(str: String): String = Jsoup.parse(str).text()

    fun buildCard(card: Card): String {
        val imgLink = "[${card.cardName.english}](${card.largeImage.default})    "

        fun about() : String {
            val parts = arrayListOf<String>()
            val color = card.getColor()
            if(color != null) parts.add(color)
            parts.add(card.cardType)
            if(card.rarity != null) parts.add(card.rarity)
            return parts.joinToString(" ")
        }

        fun stats(): String =  when(card.cardType) {
            "Hero", "Creep" -> "HP: *${card.hitPoints}* Attack: *${card.attack}*    "
            "Spell" -> "Mana Cost: *${card.manaCost}*    "
            "Item" -> "Type: *${card.subType}* Item Cost: *${card.goldCost}*    "
            else -> ""
        }

        var base = "$imgLink    \n${about()}    \n${stats()}"
        if(card.cardText.english != null) {
            base = "$base    \n${escHtml(card.cardText.english)}"
        }
        return base
    }

    fun buildRef(ref: ShortRef): String {
        val card = buildCard(ref.card)
        return "[${resolveRefType(ref.type)}] $card"
    }

    fun resolveRefType(refType: String): String {
        return CharMatcher.`is`('_').replaceFrom(refType, ' ')
    }

    fun get(): String {
        val sb: StringBuilder = java.lang.StringBuilder()
        for(tree in cards) {
            val parentStr = "## " + buildCard(tree.parent)
            val references = tree.references.map { "### " + buildRef(it) } .joinToString("    \n")
            sb.append("$parentStr    \n$references")
            sb.append("    \n")
        }
        return sb.toString()
    }
}
