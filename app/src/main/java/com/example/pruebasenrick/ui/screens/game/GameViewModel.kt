package com.example.pruebasenrick.ui.screens.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pruebasenrick.data.Character
import com.example.pruebasenrick.data.repository.CharacterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class GameViewModel(
    private val repository: CharacterRepository = CharacterRepository()
) : ViewModel() {

    // Estado con el personaje actual
    private val _character = MutableStateFlow<Character?>(null)
    val character: StateFlow<Character?> = _character

    // Estado con el resultado actual ("¡Correcto!" o "Ups...")
    private val _result = MutableStateFlow<String?>(null)
    val result: StateFlow<String?> = _result

    init {
        getRandomCharacter()
    }

    // Pide un personaje aleatorio (usamos un id random)
    fun getRandomCharacter() {
        viewModelScope.launch {
            val randomId = Random.nextInt(1, 826) // La API tiene 825 personajes
            val fetchedCharacter = repository.getCharacterById(randomId)
            _character.value = fetchedCharacter
            _result.value = null // Reinicia el resultado
        }
    }

    // Evalúa la respuesta del usuario
    fun checkAnswer(isAlive: Boolean) {
        val actualStatus = _character.value?.status ?: return

        val isCorrect = when (actualStatus.lowercase()) {
            "alive" -> isAlive
            "dead" -> !isAlive
            else -> false
        }

        _result.value = if (isCorrect) {
            "¡Correcto!"
        } else {
            "Ups... incorrecto"
        }
    }
}