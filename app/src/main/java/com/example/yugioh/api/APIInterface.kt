package com.example.yugioh.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface APIInterface {

    // Lista general (puedes filtrar por nombre, type, etc. con params)
    @GET("cardinfo.php")
    suspend fun getCards(
        @Query("fname") nameContains: String? = null,
        @Query("num") num: Int? = null,
        @Query("offset") offset: Int? = null
    ): Response<CardsResponse>

    // Detalle por id (opcional, si prefieres pedirlo al servidor)
    @GET("cardinfo.php")
    suspend fun getCardById(
        @Query("id") id: Int
    ): Response<CardsResponse>

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
