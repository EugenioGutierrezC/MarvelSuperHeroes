package com.eugenio.marvelsuperheroes.core.data

sealed class Routes(val route: String) {
    object CharactersList: Routes("charactersList")
}