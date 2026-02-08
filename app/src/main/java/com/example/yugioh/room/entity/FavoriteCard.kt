package com.example.yugioh.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.yugioh.model.BanlistInfo
import com.example.yugioh.model.CardImage
import com.example.yugioh.model.CardPrice
import com.example.yugioh.model.CardSet
import com.example.yugioh.model.YugiohCard

@Entity(tableName = "favorite_cards")
data class FavoriteCard(
    @PrimaryKey val id: Int,
    val name: String,
    val type: String?,
    val desc: String?,
    val race: String?,
    val archetype: String?,
    val frameType: String?,
    val humanReadableCardType: String?,
    val ygoprodeckUrl: String?,
    val banlistInfo: BanlistInfo?,
    val cardImages: List<CardImage>,
    val cardSets: List<CardSet>?,
    val cardPrices: List<CardPrice>,
    @ColumnInfo(name = "added_timestamp")
    val addedTimestamp: Long = System.currentTimeMillis()
)

// Función de extensión para convertir YugiohCard a FavoriteCard
fun YugiohCard.toFavoriteCard(): FavoriteCard {
    return FavoriteCard(
        id = this.id,
        name = this.name,
        type = this.type,
        desc = this.desc,
        race = this.race,
        archetype = this.archetype,
        frameType = this.frameType,
        humanReadableCardType = this.humanReadableCardType,
        ygoprodeckUrl = this.ygoprodeckUrl,
        banlistInfo = this.banlistInfo,
        cardImages = this.cardImages,
        cardSets = this.cardSets,
        cardPrices = this.cardPrices
    )
}

// Función de extensión para convertir FavoriteCard a YugiohCard
fun FavoriteCard.toYugiohCard(): YugiohCard {
    return YugiohCard(
        id = this.id,
        name = this.name,
        type = this.type,
        desc = this.desc,
        race = this.race,
        archetype = this.archetype,
        frameType = this.frameType,
        humanReadableCardType = this.humanReadableCardType,
        ygoprodeckUrl = this.ygoprodeckUrl,
        banlistInfo = this.banlistInfo,
        cardImages = this.cardImages,
        cardSets = this.cardSets,
        cardPrices = this.cardPrices
    )
}
