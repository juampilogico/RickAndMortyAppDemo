package com.example.pruebasenrick.data

import retrofit2.http.GET
import com.example.pruebasenrick.data.CharacterResponse
import retrofit2.http.Path

interface CharacterApiService {

    // Trae una lista de personajes (ya lo tenías)
    @GET("character")
    suspend fun fetchCharacters(): CharacterResponse

    // 🚨 Nuevo método: Trae un personaje por ID (lo necesitamos para el minijuego)
    @GET("character/{id}")
    suspend fun fetchCharacterById(@Path("id") id: Int): Character
}
