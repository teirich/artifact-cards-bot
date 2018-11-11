package com.sudo.artifact.model.card

import com.google.gson.annotations.SerializedName


data class CardSetMetadata(@SerializedName("cdn_root") val cdnRoot: String,
                           @SerializedName("url") val url: String,
                           @SerializedName("expire_time") val expireTime: Long)
