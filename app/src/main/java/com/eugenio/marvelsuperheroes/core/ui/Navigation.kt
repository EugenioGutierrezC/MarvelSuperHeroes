package com.eugenio.marvelsuperheroes.core.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.eugenio.marvelsuperheroes.core.data.Routes
import com.eugenio.marvelsuperheroes.superherodetail.ui.CharacterDetailScreen
import com.eugenio.marvelsuperheroes.superheroslist.ui.CharactersListScreen
import com.eugenio.marvelsuperheroes.superheroslist.ui.CharactersListViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.CharactersList.route) {
        composable(route = Routes.CharactersList.route) {
            val viewModel: CharactersListViewModel = hiltViewModel()
            CharactersListScreen(navController = navController, viewModel = viewModel)
        }
        composable(
            route = Routes.CharacterDetail.route,
            arguments = listOf(navArgument("characterId") { type = NavType.IntType })
        ) {
            CharacterDetailScreen()
        }
    }
}