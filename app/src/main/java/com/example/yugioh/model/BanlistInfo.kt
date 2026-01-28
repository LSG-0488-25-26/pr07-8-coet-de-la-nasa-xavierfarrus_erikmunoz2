package com.example.yugioh.model

import com.google.gson.annotations.SerializedName

data class BanlistInfo(
    @SerializedName("ban_tcg")
    val banTcg: String?
)
