package com.example.yugioh.room.dao

import androidx.room.*
import com.example.yugioh.room.entity.InventoryCard

@Dao
interface InventoryCardDao {
    
    @Query("SELECT * FROM inventory_cards ORDER BY added_timestamp DESC")
    suspend fun getAllInventoryCards(): List<InventoryCard>
    
    @Query("SELECT * FROM inventory_cards WHERE id = :cardId")
    suspend fun getInventoryCardById(cardId: Int): InventoryCard?
    
    @Query("SELECT EXISTS(SELECT 1 FROM inventory_cards WHERE id = :cardId)")
    suspend fun isInInventory(cardId: Int): Boolean
    
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addToInventory(card: InventoryCard): Long
    
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMultipleToInventory(cards: List<InventoryCard>): List<Long>
    
    @Delete
    suspend fun removeFromInventory(card: InventoryCard)
    
    @Query("DELETE FROM inventory_cards WHERE id = :cardId")
    suspend fun removeFromInventoryById(cardId: Int)
    
    @Query("DELETE FROM inventory_cards")
    suspend fun deleteAllInventory()
    
    @Query("SELECT COUNT(*) FROM inventory_cards")
    suspend fun getInventoryCount(): Int
}
