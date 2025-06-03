package com.example.pruebasenrick.data.repository

import com.example.pruebasenrick.data.Character

interface ICharacterRepository {
    suspend fun fetchCharacter(characterId: Int): Character
    suspend fun fetchCharacters(): List<Character>
}
