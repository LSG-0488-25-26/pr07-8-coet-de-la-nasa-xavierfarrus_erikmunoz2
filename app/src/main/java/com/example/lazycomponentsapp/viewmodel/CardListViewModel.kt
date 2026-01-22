package com.example.lazycomponentsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lazycomponentsapp.model.YuGiOhCard

class CardListViewModel : ViewModel() {

    private val _cards = MutableLiveData<List<YuGiOhCard>>(CardRepository.getCardList())
    val cards: LiveData<List<YuGiOhCard>> = _cards

    //Busca dentro de la lista de cartas la carta que coincida con el index proporcionado
    fun getCardByIndex(index: String): YuGiOhCard? {
        return _cards.value?.firstOrNull { it.index == index }
    }
}