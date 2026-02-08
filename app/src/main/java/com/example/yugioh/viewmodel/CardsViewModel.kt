package com.example.yugioh.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yugioh.api.Repository
import com.example.yugioh.model.YugiohCard
import kotlinx.coroutines.launch
import android.util.Log

class CardsViewModel : ViewModel() {

    private val repository = Repository()

    private val _cards = MutableLiveData<List<YugiohCard>>(emptyList())
    val cards: LiveData<List<YugiohCard>> = _cards

    private val _query = MutableLiveData("")
    val query: LiveData<String> = _query

    private val _filtered = MediatorLiveData<List<YugiohCard>>().apply {
        addSource(_cards) { updateFiltered() }
        addSource(_query) { updateFiltered() }
    }
    val filtered: LiveData<List<YugiohCard>> = _filtered

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String?>(null)
    val error: LiveData<String?> = _error

    fun loadCards() {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null

            try {
                val response = repository.getCardsPage(num = 400, offset = 0)

                if (response.isSuccessful) {
                    _cards.value = response.body()?.data.orEmpty()
                    Log.d("CardsViewModel", "Loaded ${_cards.value?.size ?: 0} cards")
                } else {
                    _error.value = "HTTP ${response.code()}"
                    Log.e("CardsViewModel", "HTTP error ${response.code()}")
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "Error desconocido"
                Log.e("CardsViewModel", "Exception loading cards", e)
            } finally {
                _loading.value = false
            }
        }
    }

    fun getCardFromLoadedList(cardId: Int): YugiohCard? {
        return _cards.value?.firstOrNull { it.id == cardId }
    }

    fun setQuery(q: String) {
        _query.value = q
    }

    private fun updateFiltered() {
        val q = _query.value.orEmpty()
        val list = _cards.value.orEmpty()
        _filtered.value = if (q.isBlank()) list else list.filter { card ->
            val name = card.name ?: ""
            val desc = card.desc ?: ""
            name.contains(q, ignoreCase = true) || desc.contains(q, ignoreCase = true)
        }
    }
}
