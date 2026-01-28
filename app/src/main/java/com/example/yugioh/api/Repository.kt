package com.example.yugioh.api

import com.example.yugioh.model.CardsResponse
import retrofit2.Response

class Repository {

    private val api: APIInterface = APIInterface.create()

    suspend fun getAllCards() = api.getCards()

}
