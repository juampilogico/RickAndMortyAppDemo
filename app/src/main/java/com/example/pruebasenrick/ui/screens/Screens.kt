package com.example.pruebasenrick.ui.screens

sealed class Screens(val route: String) {
    object Splash : Screens("splash")
    object Login : Screens("login")
    object CharacterList : Screens("character_list")
    object CharacterDetail : Screens("character_detail/{characterId}") {
        fun createRoute(characterId: Int) = "character_detail/$characterId"
    }
}
