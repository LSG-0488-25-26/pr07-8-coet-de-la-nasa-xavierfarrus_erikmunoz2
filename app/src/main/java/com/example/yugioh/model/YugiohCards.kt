package com.example.yugioh.model

import com.google.gson.annotations.SerializedName


data class YugiohCard(
    val id: Int,
    val name: String,
    val type: String?,
    val desc: String?,
    val race: String?,
    val archetype: String?,

    @SerializedName("frameType") val frameType: String?,
    @SerializedName("humanReadableCardType") val humanReadableCardType: String?,
    @SerializedName("ygoprodeck_url") val ygoprodeckUrl: String?,

    @SerializedName("banlist_info") val banlistInfo: BanlistInfo?,
    @SerializedName("card_images") val cardImages: List<CardImage> = emptyList(),
    @SerializedName("card_sets") val cardSets: List<CardSet>? = null,
    @SerializedName("card_prices") val cardPrices: List<CardPrice> = emptyList()
)
