package com.example.pruebasenrick.data.repository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.pruebasenrick.data.CharacterApiService

object ApiClient {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val retrofitService: CharacterApiService by lazy {
        retrofit.create(CharacterApiService::class.java)
    }
}
