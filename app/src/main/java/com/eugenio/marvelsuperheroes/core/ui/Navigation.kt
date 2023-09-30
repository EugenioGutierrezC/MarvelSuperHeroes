package com.eugenio.marvelsuperheroes.core.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.eugenio.marvelsuperheroes.core.data.Routes
import com.eugenio.marvelsuperheroes.superheroslist.ui.CharactersListScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.CharactersList.route) {
        composable(route = Routes.CharactersList.route) { CharactersListScreen() }
    }
}