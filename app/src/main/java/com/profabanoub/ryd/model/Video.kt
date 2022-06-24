package com.profabanoub.ryd.model

import com.google.gson.annotations.SerializedName

data class Video(
    @SerializedName("id") var id: String? = null,
    @SerializedName("dateCreated") var dateCreated: String? = null,
    @SerializedName("likes") var likes: Int? = null,
    @SerializedName("dislikes") var dislikes: Int? = null,
    @SerializedName("rating") var rating: Double? = null,
    @SerializedName("viewCount") var viewCount: Int? = null,
    @SerializedName("deleted") var deleted: Boolean? = null
)
