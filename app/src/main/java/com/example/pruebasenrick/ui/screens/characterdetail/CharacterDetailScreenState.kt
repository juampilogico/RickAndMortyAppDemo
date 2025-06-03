package com.example.pruebasenrick.ui.screens.characterdetail

import com.example.pruebasenrick.data.Character
import com.example.pruebasenrick.data.Location
import com.example.pruebasenrick.data.Origin

data class CharacterDetailScreenState(
    val characterId: Int = 0,
    val characterDetail: Character = Character(
        id = 0,
        name = "",
        status = "",
        species = "",
        type = "",
        gender = "",
        origin = Origin(name = "", url = ""),
        location = Location(name = "", url = ""),
        image = "",
        episode = emptyList(),
        created = ""
    )
)
