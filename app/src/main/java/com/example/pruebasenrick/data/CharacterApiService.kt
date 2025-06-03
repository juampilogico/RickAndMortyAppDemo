package com.example.pruebasenrick.data

import retrofit2.http.GET
import com.example.pruebasenrick.data.CharacterResponse


interface CharacterApiService {
    @GET("character")
    suspend fun fetchCharacters(): CharacterResponse
}