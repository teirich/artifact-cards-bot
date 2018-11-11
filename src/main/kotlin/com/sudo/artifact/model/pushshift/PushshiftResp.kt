package com.sudo.artifact.model.pushshift

import com.google.gson.annotations.SerializedName

data class PushshiftResp(@SerializedName("data") val data: Array<Comment>)
