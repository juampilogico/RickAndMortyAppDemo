package com.example.pruebasenrick.ui.screens

sealed class Screens(val route: String) {
    object Splash : Screens("splash")
    object Login : Screens("login")
    object CharacterList : Screens("character_list")
    object Favorites : Screens("favorites")
    object Game : Screens("game") // 🔥 NUEVA RUTA
    sealed class Screens(val route: String) { object Game : Screens("game")
    }

    object CharacterDetail : Screens("character_detail/{characterId}") {
        fun createRoute(characterId: Int) = "character_detail/$characterId"
    }
}

