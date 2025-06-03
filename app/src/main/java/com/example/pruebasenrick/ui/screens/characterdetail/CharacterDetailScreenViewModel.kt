package com.example.pruebasenrick.ui.screens.characterdetail


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pruebasenrick.data.repository.CharacterRepository
import com.example.pruebasenrick.data.repository.ICharacterRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import androidx.compose.runtime.*

class CharacterDetailScreenViewModel(
    private val characterRepository: ICharacterRepository = CharacterRepository()
) : ViewModel() {

    var uiState by mutableStateOf(CharacterDetailScreenState())
        private set

    private var fetchJob: Job? = null

    fun fetchCharacter() {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            val character = characterRepository.fetchCharacter(uiState.characterId)
            uiState = uiState.copy(characterDetail = character)
        }
    }

    fun setCharacterId(id: Int) {
        uiState = uiState.copy(characterId = id)
        fetchCharacter()
    }
}
