package com.example.yugioh.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yugioh.model.YugiohCard

class InventoryViewModel : ViewModel() {

    private val _openedPack = MutableLiveData<List<YugiohCard>>(emptyList())
    val openedPack: LiveData<List<YugiohCard>> = _openedPack

    private val _inventory = MutableLiveData<List<YugiohCard>>(emptyList())
    val inventory: LiveData<List<YugiohCard>> = _inventory
    private val _snackbarMessage = MutableLiveData<String?>(null)
    val snackbarMessage: LiveData<String?> = _snackbarMessage

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

        val current = _inventory.value.orEmpty()
        val existingIds = current.asSequence().map { it.id }.toHashSet()
        val inventory: LiveData<List<YugiohCard>> = _inventory


        val packUnique = pack.distinctBy { it.id }
        val duplicatesInsidePack = pack.size - packUnique.size

        val newOnes = packUnique.filter { it.id !in existingIds }
        val duplicatesAgainstInventory = packUnique.size - newOnes.size

        if (newOnes.isNotEmpty()) {
            _inventory.value = current + newOnes
        }

        val added = newOnes.size
        val repeated = duplicatesInsidePack + duplicatesAgainstInventory

        _snackbarMessage.value = if (added > 0) {
            if (repeated > 0) "Añadidas $added cartas nuevas al inventario. Ignoradas $repeated repetidas."
            else "Añadidas $added cartas nuevas al inventario."
        } else {
            "Todas eran repetidas. No se añadió ninguna."
        }
    }



}
