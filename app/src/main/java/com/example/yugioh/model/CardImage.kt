package com.example.yugioh.model

import com.google.gson.annotations.SerializedName

data class CardImage(
    val id: Int,

    @SerializedName("image_url")
    val imageUrl: String,

    @SerializedName("image_url_small")
    val imageUrlSmall: String,

    @SerializedName("image_url_cropped")
    val imageUrlCropped: String
)
