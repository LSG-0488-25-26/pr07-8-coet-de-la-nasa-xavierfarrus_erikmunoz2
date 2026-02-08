package com.example.yugioh.room

import androidx.room.TypeConverter
import com.example.yugioh.model.BanlistInfo
import com.example.yugioh.model.CardImage
import com.example.yugioh.model.CardPrice
import com.example.yugioh.model.CardSet
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    // Converter para List<CardImage>
    @TypeConverter
    fun fromCardImageList(value: List<CardImage>?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toCardImageList(value: String): List<CardImage> {
        val listType = object : TypeToken<List<CardImage>>() {}.type
        return gson.fromJson(value, listType)
    }

    // Converter para List<CardPrice>
    @TypeConverter
    fun fromCardPriceList(value: List<CardPrice>?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toCardPriceList(value: String): List<CardPrice> {
        val listType = object : TypeToken<List<CardPrice>>() {}.type
        return gson.fromJson(value, listType)
    }

    // Converter para List<CardSet>
    @TypeConverter
    fun fromCardSetList(value: List<CardSet>?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toCardSetList(value: String?): List<CardSet>? {
        if (value == null) return null
        val listType = object : TypeToken<List<CardSet>>() {}.type
        return gson.fromJson(value, listType)
    }

    // Converter para BanlistInfo
    @TypeConverter
    fun fromBanlistInfo(value: BanlistInfo?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toBanlistInfo(value: String?): BanlistInfo? {
        if (value == null) return null
        return gson.fromJson(value, BanlistInfo::class.java)
    }
}
