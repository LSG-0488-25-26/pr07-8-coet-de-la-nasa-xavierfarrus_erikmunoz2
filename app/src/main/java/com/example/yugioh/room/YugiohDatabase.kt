package com.example.yugioh.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.yugioh.room.dao.FavoriteCardDao
import com.example.yugioh.room.dao.InventoryCardDao
import com.example.yugioh.room.entity.FavoriteCard
import com.example.yugioh.room.entity.InventoryCard

@Database(
    entities = [FavoriteCard::class, InventoryCard::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class YugiohDatabase : RoomDatabase() {
    abstract fun favoriteCardDao(): FavoriteCardDao
    abstract fun inventoryCardDao(): InventoryCardDao
}
