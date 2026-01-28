package com.example.yugioh.model

import com.google.gson.annotations.SerializedName

data class CardsResponse(
    @SerializedName("data")
    val data: List<YugiohCard>
)
