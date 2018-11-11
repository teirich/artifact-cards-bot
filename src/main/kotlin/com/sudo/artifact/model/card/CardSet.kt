package com.sudo.artifact.model.card

import com.google.gson.annotations.SerializedName

data class CardSetResponse(@SerializedName("card_set") val cardSet: CardSet)

data class CardSetInfo(@SerializedName("set_id") val setId: String,
                       @SerializedName("pack_item_def") val packItemDef: Int,
                       @SerializedName("name") val name: LocalizedText)

data class CardSet(@SerializedName("version") val version: Int,
                   @SerializedName("set_info") val setInfo: CardSetInfo,
                   @SerializedName("card_list") val cardList: Array<Card>)
