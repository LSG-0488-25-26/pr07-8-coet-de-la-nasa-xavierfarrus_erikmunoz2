package com.example.yugioh.api

import com.example.yugioh.model.CardsResponse
import retrofit2.Response

class Repository {

    private val api: APIInterface = APIInterface.create()

    suspend fun getAllCards(): Response<CardsResponse> =
        api.getCards()

    suspend fun getCardById(id: Int): Response<CardsResponse> =
        api.getCardById(id)
}
