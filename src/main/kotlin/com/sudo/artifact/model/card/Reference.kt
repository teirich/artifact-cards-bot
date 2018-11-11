package com.sudo.artifact.model.card

import com.google.gson.annotations.SerializedName

/*
{
card_id: 10005,
ref_type: "includes",
count: 3
}
 */
data class Reference(@SerializedName("card_id") val cardId: Long,
                     @SerializedName("ref_type") val refType: String,
                     @SerializedName("count") val count: Int)
