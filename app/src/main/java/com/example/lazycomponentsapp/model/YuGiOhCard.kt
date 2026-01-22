package com.example.lazycomponentsapp.model

import androidx.annotation.DrawableRes

data class YuGiOhCard(
    val index: String,
    val name: String,
    val type: String,
    val attack: Int?,
    val defense: Int?,
    val description: String,
    @DrawableRes val image: Int
)