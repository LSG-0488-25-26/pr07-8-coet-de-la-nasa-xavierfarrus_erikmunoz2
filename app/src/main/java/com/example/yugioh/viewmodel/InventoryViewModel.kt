package com.example.yugioh.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yugioh.model.YugiohCard
import com.example.yugioh.room.RoomRepository
import kotlinx.coroutines.launch

class InventoryViewModel : ViewModel() {

    private val roomRepository = RoomRepository()

    private val _openedPack = MutableLiveData<List<YugiohCard>>(emptyList())
    val openedPack: LiveData<List<YugiohCard>> = _openedPack

    private val _inventory = MutableLiveData<List<YugiohCard>>(emptyList())
    val inventory: LiveData<List<YugiohCard>> = _inventory
    
    private val _snackbarMessage = MutableLiveData<String?>(null)
    val snackbarMessage: LiveData<String?> = _snackbarMessage

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    init {
        // Cargar inventario desde la BD al iniciar
        loadInventory()
    }

    fun loadInventory() {
        viewModelScope.launch {
            _loading.value = true
            try {
                val cards = roomRepository.getAllInventoryCards()
                _inventory.value = cards
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _loading.value = false
            }
        }
    }

    fun clearSnackbarMessage() {
        _snackbarMessage.value = null
    }

    fun openPack5(fromAllCards: List<YugiohCard>) {
        if (fromAllCards.isEmpty()) {
            _openedPack.value = emptyList()
            return
        }

        val pack = if (fromAllCards.size >= 5) {
            fromAllCards.shuffled().take(5)
        } else {
            List(5) { fromAllCards.random() }
        }

        _openedPack.value = pack
    }

    fun addPackToInventory() {
        val pack = _openedPack.value.orEmpty()
        if (pack.isEmpty()) return

        viewModelScope.launch {
            try {
                // Guardar en la base de datos
                val result = roomRepository.addMultipleToInventory(pack)
                val added = result.first
                val repeated = result.second

                // Recargar inventario desde la BD
                loadInventory()

                _snackbarMessage.value = if (added > 0) {
                    if (repeated > 0) "Añadidas $added cartas nuevas al inventario. Ignoradas $repeated repetidas."
                    else "Añadidas $added cartas nuevas al inventario."
                } else {
                    "Todas eran repetidas. No se añadió ninguna."
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _snackbarMessage.value = "Error al añadir al inventario"
            }
        }
    }

    fun removeFromInventory(cardId: Int) {
        viewModelScope.launch {
            try {
                roomRepository.removeFromInventory(cardId)
                loadInventory()
                _snackbarMessage.value = "Carta eliminada del inventario"
            } catch (e: Exception) {
                e.printStackTrace()
                _snackbarMessage.value = "Error al eliminar carta"
            }
        }
    }
}

