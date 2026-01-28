package com.example.yugioh.api

import com.example.yugioh.model.CardsResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface APIInterface {

    // Endpoint principal: devuelve todas las cartas.

    @GET("cardinfo.php")
    suspend fun getCards(): Response<CardsResponse>

    companion object {

        private const val BASE_URL = "https://db.ygoprodeck.com/api/v7/"

        fun create(): APIInterface {
            val logging = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofit.create(APIInterface::class.java)
        }
    }
}
