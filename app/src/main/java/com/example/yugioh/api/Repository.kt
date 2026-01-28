package com.example.yugioh.api

class Repository {
    private val api = APIInterface.create()

    suspend fun getAllCards() = api.getCards()
    suspend fun getCardById(id: Int) = api.getCardById(id)
}
