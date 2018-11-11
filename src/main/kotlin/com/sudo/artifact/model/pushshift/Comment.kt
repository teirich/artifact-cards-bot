package com.sudo.artifact.model.pushshift

import com.google.gson.annotations.SerializedName

/**
 * {
 * author: "poptard278837219",
 * author_flair_background_color: "",
 * author_flair_css_class: "custom greenrocks",
 * author_flair_richtext: [ ],
 * author_flair_template_id: "81a15a70-9430-11e7-8f68-0e9270fa6f48",
 * author_flair_text: "MONO GREEN OMEGALUL",
 * author_flair_text_color: "dark",
 * author_flair_type: "text",
 * author_fullname: "t2_24tkp7ou",
 * author_patreon_flair: false,
 * body: "Hope if there is any reward who isnt pre order the reward stick for a week so we can see properly if worth buy it or not",
 * created_utc: 1541812074,
 * gildings: {
 * gid_1: 0,
 * gid_2: 0,
 * gid_3: 0
 * },
 * id: "e9e88jn",
 * link_id: "t3_9voyq2",
 * no_follow: true,
 * parent_id: "t1_e9dzmhw",
 * permalink: "/r/Artifact/comments/9voyq2/artifact_preorders_go_up_tomorrow_at_9_am_pst11/e9e88jn/",
 * retrieved_on: 1541812075,
 * score: 1,
 * send_replies: true,
 * stickied: false,
 * subreddit: "Artifact",
 * subreddit_id: "t5_31338"
 * }
 */
data class Comment(@SerializedName("id") val id: String,
                   @SerializedName("author") val author: String,
                   @SerializedName("author_full_name") val authorFullname: String,
                   @SerializedName("body") val body: String,
                   @SerializedName("created_utc") val createdUtc: Long,
                   @SerializedName("retrieved_on") val retrievedOn: Long,
                   @SerializedName("permalink") val permalink: String,
                   @SerializedName("score") val score: Int,
                   @SerializedName("subreddit") val subreddit: String)
