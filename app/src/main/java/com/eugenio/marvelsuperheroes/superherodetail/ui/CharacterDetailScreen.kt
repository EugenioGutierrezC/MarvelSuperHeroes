package com.eugenio.marvelsuperheroes.superherodetail.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.eugenio.marvelsuperheroes.R
import com.eugenio.marvelsuperheroes.core.utils.ViewState
import com.eugenio.marvelsuperheroes.superherodetail.ui.components.ComicRow

@Composable
fun CharacterDetailScreen(viewModel: CharacterDetailViewModel = hiltViewModel(), characterId: Int) {
    LaunchedEffect(characterId) {
        viewModel.initialize(characterId)
        viewModel.getCharacters(characterId)
    }
    when(val state = viewModel.myDataState.value) {
        ViewState.Loading -> {}
        is ViewState.Success -> {
            CharacterDetail(viewModel, state.data.thumbnail.toString(), state.data.name, state.data.description)
        }
        is ViewState.Error -> {
            state.exception
        }
    }
}

@Composable
fun CharacterDetail(viewModel: CharacterDetailViewModel = hiltViewModel(), imagePath: String, title: String, description: String) {

    val comics = viewModel.charactersFlow.collectAsLazyPagingItems()
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            AsyncImage(
                model = imagePath,
                contentDescription = null,
                modifier = Modifier
                    .height(LocalConfiguration.current.screenHeightDp.dp * 0.25f)
                    .fillMaxWidth(),
                placeholder = painterResource(id = R.drawable.marvel_logo_square),
                error = painterResource(id = R.drawable.marvel_logo_square)
            )

            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
            )

            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        items(comics.itemCount) { index ->
            val comic = comics[index]
            if (comic != null) {
                ComicRow(
                    title = comic.name,
                    date = comic.date,
                    imageUrl = comic.thumbnail
                )
            }
        }
    }
}

/*@Composable
@DarkAndLightPreview
fun CharacterDetailScreenPreview() {
    CharacterDetail("", "Spiderman", "Vecino y amigo")
}*/