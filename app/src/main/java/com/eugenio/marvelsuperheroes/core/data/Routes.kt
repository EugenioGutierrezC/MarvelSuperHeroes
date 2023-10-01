package com.eugenio.marvelsuperheroes.core.data

sealed class Routes(val route: String) {
    object CharactersList: Routes("charactersList")
    object CharacterDetail: Routes("characterDetail/{characterId}") {
        fun createRoute(characterId: Int) = "characterDetail/$characterId"
    }
}