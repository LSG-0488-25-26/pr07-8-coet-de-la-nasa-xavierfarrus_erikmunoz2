package com.example.yugioh.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yugioh.model.YugiohCard
import com.example.yugioh.room.RoomRepository
import kotlinx.coroutines.launch

class FavoritesViewModel : ViewModel() {

    private val roomRepository = RoomRepository()

    private val _favorites = MutableLiveData<List<YugiohCard>>(emptyList())
    val favorites: LiveData<List<YugiohCard>> = _favorites

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val _snackbarMessage = MutableLiveData<String?>(null)
    val snackbarMessage: LiveData<String?> = _snackbarMessage

    // Cache para verificar rápidamente si una carta es favorita
    private val _favoriteIds = MutableLiveData<Set<Int>>(emptySet())
    val favoriteIds: LiveData<Set<Int>> = _favoriteIds

    init {
        loadFavorites()
    }

    fun loadFavorites() {
        viewModelScope.launch {
            _loading.value = true
            try {
                val cards = roomRepository.getAllFavorites()
                _favorites.value = cards
                _favoriteIds.value = cards.map { it.id }.toSet()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _loading.value = false
            }
        }
    }

    fun isFavorite(cardId: Int): Boolean {
        return _favoriteIds.value?.contains(cardId) ?: false
    }

    fun toggleFavorite(card: YugiohCard, onComplete: ((Boolean) -> Unit)? = null) {
        viewModelScope.launch {
            try {
                val isFav = roomRepository.isFavorite(card.id)
                if (isFav) {
                    roomRepository.removeFavorite(card.id)
                    _snackbarMessage.value = "Eliminado de favoritos"
                    onComplete?.invoke(false)
                } else {
                    roomRepository.addFavorite(card)
                    _snackbarMessage.value = "Añadido a favoritos"
                    onComplete?.invoke(true)
                }
                loadFavorites()
            } catch (e: Exception) {
                e.printStackTrace()
                _snackbarMessage.value = "Error al actualizar favoritos"
            }
        }
    }

    fun addFavorite(card: YugiohCard) {
        viewModelScope.launch {
            try {
                roomRepository.addFavorite(card)
                loadFavorites()
                _snackbarMessage.value = "Añadido a favoritos"
            } catch (e: Exception) {
                e.printStackTrace()
                _snackbarMessage.value = "Error al añadir favorito"
            }
        }
    }

    fun removeFavorite(cardId: Int) {
        viewModelScope.launch {
            try {
                roomRepository.removeFavorite(cardId)
                loadFavorites()
                _snackbarMessage.value = "Eliminado de favoritos"
            } catch (e: Exception) {
                e.printStackTrace()
                _snackbarMessage.value = "Error al eliminar favorito"
            }
        }
    }

    fun clearSnackbarMessage() {
        _snackbarMessage.value = null
    }
}
