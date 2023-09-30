package com.eugenio.marvelsuperheroes.superheroslist.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.eugenio.marvelsuperheroes.core.ui.multipreview.DarkAndLightPreview
import com.eugenio.marvelsuperheroes.core.ui.theme.MarvelSuperHeroesTheme
import com.eugenio.marvelsuperheroes.core.utils.ViewState
import com.eugenio.marvelsuperheroes.superheroslist.ui.components.CharacterRowShimmer

@Composable
fun CharactersListScreen(viewModel: CharactersListViewModel = hiltViewModel()) {
    when(val state = viewModel.myDataState.value) {
        ViewState.Loading -> CharactersLoading()
        is ViewState.Success -> {
            CharactersSuccess()
        }
        is ViewState.Error -> {
            state.exception
        }
    }
}

@Composable
fun CharactersLoading() {
    LazyColumn {
        items(10) {
            CharacterRowShimmer()
            Divider(Modifier.fillMaxSize())
        }
    }
}

@Composable
fun CharactersSuccess() {
    //Set up when retrivedata
}

@Composable
@DarkAndLightPreview
fun CharactersListLoadingScreenPreview() {
    MarvelSuperHeroesTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            CharactersLoading()
        }
    }
}