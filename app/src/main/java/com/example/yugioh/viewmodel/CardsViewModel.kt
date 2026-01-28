package com.example.yugioh.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yugioh.api.Repository
import com.example.yugioh.model.YugiohCard
import kotlinx.coroutines.launch

class CardsViewModel : ViewModel() {

    private val repository = Repository()

    private val _cards = MutableLiveData<List<YugiohCard>>(emptyList())
    val cards: LiveData<List<YugiohCard>> = _cards

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String?>(null)
    val error: LiveData<String?> = _error

    fun loadCards() {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null

            try {
                val response = repository.getAllCards()
                if (response.isSuccessful) {
                    _cards.value = response.body()?.data.orEmpty()
                } else {
                    _error.value = "HTTP ${response.code()}"
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "Error desconocido"
            } finally {
                _loading.value = false
            }
        }
    }

    fun getCardFromLoadedList(cardId: Int): YugiohCard? {
        return _cards.value?.firstOrNull { it.id == cardId }
    }
}
