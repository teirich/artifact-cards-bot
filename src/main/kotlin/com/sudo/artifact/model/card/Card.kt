package com.sudo.artifact.model.card

import com.google.gson.annotations.SerializedName

/*
{
card_id: 10004,
base_card_id: 10004,
card_type: "Hero",
card_name: {
english: "Meepo"
},
card_text: {
english: "&lt;span style='font-weight:bold;color:#ffffff;'&gt;Active 2:&lt;/span&gt; Move Meepo to an allied &lt;span style='font-weight:bold;color:#ffffff;'&gt;Meepo&lt;/span&gt;'s lane.  Deal 2 damage to the new enemy neighbors."
},
mini_image: {
default: "https://steamcdn-a.akamaihd.net/apps/583950/icons/set01/10004.8e77e6463c971dab1fa01b3761808eb2cf5163b0.png"
},
large_image: {
default: "https://steamcdn-a.akamaihd.net/apps/583950/icons/set01/10004_large_english.3fe0105079199e7b70dd200e8ce59dcc6202becd.png"
},
ingame_image: {
default: "https://steamcdn-a.akamaihd.net/apps/583950/icons/set01/10004_ingame.abad49f30c45897120d06d3bef0db20798800c49.png"
},
illustrator: "Wisnu Tan",
rarity: "Rare",
is_blue: true,
item_def: 110004,
attack: 4,
hit_points: 5,
references: [
{
card_id: 10005,
ref_type: "includes",
count: 3
},
{
card_id: 10429,
ref_type: "active_ability"
},
{
card_id: 10490,
ref_type: "passive_ability"
}
]
}
 */
data class Card(@SerializedName("card_id") val cardId: Long,
                @SerializedName("base_card_id") val baseCardId: Long,
                @SerializedName("card_type") val cardType: String,
                @SerializedName("card_name") val cardName: LocalizedText,
                @SerializedName("card_text") val cardText: LocalizedText,
                @SerializedName("mini_image") val miniImage: Image,
                @SerializedName("large_image") val largeImage: Image,
                @SerializedName("ingame_image") val ingameImage: Image,
                @SerializedName("illustrator") val illustrator: String,
                @SerializedName("rarity") val rarity: String,
                @SerializedName("is_blue") val isBlue: Boolean,
                @SerializedName("is_black") val isBlack: Boolean,
                @SerializedName("is_red") val isRed: Boolean,
                @SerializedName("is_green") val isGreen: Boolean,
                @SerializedName("item_def") val itemDef: Long,
                @SerializedName("attack") val attack: Int,
                @SerializedName("hit_points") val hitPoints: Int,
                @SerializedName("mana_cost") val manaCost: Int,
                @SerializedName("sub_type") val subType: String,
                @SerializedName("gold_cost") val goldCost: Int,
                @SerializedName("references") val references: Array<Reference>) {
    fun getColor(): String? {
        if(isBlue) return "Blue"
        if(isBlack) return "Black"
        if(isRed) return "Red"
        if(isGreen) return "Green"
        return null
    }
}
