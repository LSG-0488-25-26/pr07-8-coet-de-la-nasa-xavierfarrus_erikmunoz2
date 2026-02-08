package com.example.yugioh.room

import com.example.yugioh.model.YugiohCard
import com.example.yugioh.room.dao.FavoriteCardDao
import com.example.yugioh.room.dao.InventoryCardDao
import com.example.yugioh.room.entity.toFavoriteCard
import com.example.yugioh.room.entity.toInventoryCard
import com.example.yugioh.room.entity.toYugiohCard

class RoomRepository {
    
    private val favoriteDao: FavoriteCardDao = YugiohApplication.database.favoriteCardDao()
    private val inventoryDao: InventoryCardDao = YugiohApplication.database.inventoryCardDao()
    
    // ========== FAVORITOS ==========
    
    suspend fun getAllFavorites(): List<YugiohCard> {
        return favoriteDao.getAllFavorites().map { it.toYugiohCard() }
    }
    
    suspend fun isFavorite(cardId: Int): Boolean {
        return favoriteDao.isFavorite(cardId)
    }
    
    suspend fun addFavorite(card: YugiohCard) {
        favoriteDao.addFavorite(card.toFavoriteCard())
    }
    
    suspend fun removeFavorite(cardId: Int) {
        favoriteDao.removeFavoriteById(cardId)
    }
    
    suspend fun getFavoritesCount(): Int {
        return favoriteDao.getFavoritesCount()
    }
    
    suspend fun deleteAllFavorites() {
        favoriteDao.deleteAllFavorites()
    }
    
    // ========== INVENTARIO ==========
    
    suspend fun getAllInventoryCards(): List<YugiohCard> {
        return inventoryDao.getAllInventoryCards().map { it.toYugiohCard() }
    }
    
    suspend fun isInInventory(cardId: Int): Boolean {
        return inventoryDao.isInInventory(cardId)
    }
    
    suspend fun addToInventory(card: YugiohCard): Boolean {
        val result = inventoryDao.addToInventory(card.toInventoryCard())
        return result != -1L // -1 significa que ya existía (IGNORE)
    }
    
    suspend fun addMultipleToInventory(cards: List<YugiohCard>): Pair<Int, Int> {
        val inventoryCards = cards.map { it.toInventoryCard() }
        val results = inventoryDao.addMultipleToInventory(inventoryCards)
        val added = results.count { it != -1L }
        val repeated = results.size - added
        return Pair(added, repeated)
    }
    
    suspend fun removeFromInventory(cardId: Int) {
        inventoryDao.removeFromInventoryById(cardId)
    }
    
    suspend fun getInventoryCount(): Int {
        return inventoryDao.getInventoryCount()
    }
    
    suspend fun deleteAllInventory() {
        inventoryDao.deleteAllInventory()
    }
}
