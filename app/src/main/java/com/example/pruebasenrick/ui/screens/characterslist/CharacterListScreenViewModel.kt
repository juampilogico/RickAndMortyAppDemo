package com.example.pruebasenrick.ui.screens.characterslist

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pruebasenrick.data.Character
import com.example.pruebasenrick.data.repository.CharacterRepository
import com.example.pruebasenrick.data.repository.ICharacterRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.IOException

class CharacterListScreenViewModel(
    private val characterRepository: ICharacterRepository = CharacterRepository()
) : ViewModel() {

    var uiState by mutableStateOf(CharactersListScreenState())
        private set

    private var fetchJob: Job? = null

    init {
        fetchCharacters()
    }

    fun fetchCharacters() {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            try {
                val characters = characterRepository.fetchCharacters()
                uiState = uiState.copy(
                    characterList = characters,
                    filteredList = characters // inicial: todos
                )
            } catch (e: IOException) {
                Log.e("CharacterApp", "Error recuperando la lista de personajes", e)
            }
        }
    }

    fun onSearchChange(query: String) {
        val filtered = uiState.characterList.filter {
            it.name.contains(query, ignoreCase = true)
        }
        uiState = uiState.copy(searchQuery = query, filteredList = filtered)
    }
}
