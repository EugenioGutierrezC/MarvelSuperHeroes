package com.eugenio.marvelsuperheroes.superheroslist.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.eugenio.marvelsuperheroes.core.ui.multipreview.DarkAndLightPreview
import com.eugenio.marvelsuperheroes.core.ui.theme.MarvelSuperHeroesTheme
import com.eugenio.marvelsuperheroes.superheroslist.ui.components.CharacterRow
import com.eugenio.marvelsuperheroes.superheroslist.ui.components.CharacterRowShimmer
import com.eugenio.marvelsuperheroes.superheroslist.ui.model.CharacterViewItem

@Composable
fun CharactersListScreen(viewModel: CharactersListViewModel = hiltViewModel()) {
    val characters = viewModel.charactersFlow.collectAsLazyPagingItems()

    if (characters.loadState.refresh is LoadState.Loading) {
        CharactersLoading()
    } else {
        CharactersList(characters)
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
fun CharactersList(charactersList: LazyPagingItems<CharacterViewItem>) {
    LazyColumn {
        items(charactersList.itemCount) { index ->
            val character = charactersList[index]
            if (character != null) {
                CharacterRow(character.name, character.comics, character.thumbnail)
            }
        }
        item {
            if(charactersList.loadState.append is LoadState.Loading) {
                CharacterRowShimmer()
            }
        }
    }
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