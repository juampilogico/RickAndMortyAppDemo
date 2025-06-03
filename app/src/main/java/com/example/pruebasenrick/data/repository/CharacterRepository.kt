package com.example.pruebasenrick.data.repository

import com.example.pruebasenrick.data.Character
import com.example.pruebasenrick.data.CharacterApiService
import com.example.pruebasenrick.data.CharacterResponse

class CharacterRepository(
    private val apiService: CharacterApiService = ApiClient.retrofitService
) : ICharacterRepository {

    override suspend fun fetchCharacters(): List<Character> {
        val response = apiService.fetchCharacters()
        return response.results
    }

    override suspend fun fetchCharacter(characterId: Int): Character {
        val response = apiService.fetchCharacters()
        return response.results.first { it.id == characterId }
    }
}

