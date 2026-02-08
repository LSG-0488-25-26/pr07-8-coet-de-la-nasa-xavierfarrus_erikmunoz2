package com.example.yugioh.room.dao

import androidx.room.*
import com.example.yugioh.room.entity.FavoriteCard

@Dao
interface FavoriteCardDao {
    
    @Query("SELECT * FROM favorite_cards ORDER BY added_timestamp DESC")
    suspend fun getAllFavorites(): List<FavoriteCard>
    
    @Query("SELECT * FROM favorite_cards WHERE id = :cardId")
    suspend fun getFavoriteById(cardId: Int): FavoriteCard?
    
    @Query("SELECT EXISTS(SELECT 1 FROM favorite_cards WHERE id = :cardId)")
    suspend fun isFavorite(cardId: Int): Boolean
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(card: FavoriteCard)
    
    @Delete
    suspend fun removeFavorite(card: FavoriteCard)
    
    @Query("DELETE FROM favorite_cards WHERE id = :cardId")
    suspend fun removeFavoriteById(cardId: Int)
    
    @Query("DELETE FROM favorite_cards")
    suspend fun deleteAllFavorites()
    
    @Query("SELECT COUNT(*) FROM favorite_cards")
    suspend fun getFavoritesCount(): Int
}
