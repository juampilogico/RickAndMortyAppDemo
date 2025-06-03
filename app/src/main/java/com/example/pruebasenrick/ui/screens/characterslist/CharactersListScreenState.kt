package com.example.pruebasenrick.ui.screens.characterslist

import com.example.pruebasenrick.data.Character

data class CharactersListScreenState(
    val characterList: List<Character> = emptyList(),
    val filteredList: List<Character> = emptyList(),
    val searchQuery: String = "",
    val username: String = "usuario1"

)
