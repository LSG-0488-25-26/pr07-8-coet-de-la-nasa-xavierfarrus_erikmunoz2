package com.example.yugioh.api

import com.example.yugioh.model.CardsResponse
import retrofit2.Response

class Repository {

    private val api: APIInterface = APIInterface.create()

    suspend fun getCardsPage(num: Int = 400, offset: Int = 0) =
        api.getCards(num = num, offset = offset)

}
