package com.eugenio.marvelsuperheroes.superherodetail.ui

import android.app.Activity
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.eugenio.marvelsuperheroes.core.ui.components.ErrorDialog
import com.eugenio.marvelsuperheroes.core.ui.multipreview.DarkAndLightPreview
import com.eugenio.marvelsuperheroes.core.utils.ViewState
import com.eugenio.marvelsuperheroes.superherodetail.ui.components.CharacterInformation
import com.eugenio.marvelsuperheroes.superherodetail.ui.components.CharacterInformationSkeleton
import com.eugenio.marvelsuperheroes.superherodetail.ui.components.ComicRow
import com.eugenio.marvelsuperheroes.superherodetail.ui.components.ComicRowSkeleton
import com.eugenio.marvelsuperheroes.superherodetail.ui.model.ComicsItem
import java.net.URL

@Composable
fun CharacterDetailScreen(viewModel: CharacterDetailViewModel = hiltViewModel(), characterId: Int) {
    LaunchedEffect(characterId) {
        viewModel.initialize(characterId)
        viewModel.getCharacters(characterId)
    }
    val comics = viewModel.charactersFlow?.collectAsLazyPagingItems()
    val activity = (LocalContext.current as? Activity)

    when (val state = viewModel.myDataState.value) {
        ViewState.Loading -> {
            CharacterDetailLoading()
        }
        is ViewState.Success -> {
            val list = if(comics != null) {
                List(comics.itemCount) { index ->
                    comics[index]
                }
            } else {
                emptyList()
            }
            CharacterDetail(
                list,
                state.data.thumbnail.toString(),
                state.data.name,
                state.data.description
            )
        }

        is ViewState.Error -> {
            ErrorDialog(
                onButtonClick = {
                    viewModel.getCharacters(characterId)
                    viewModel.initialize(characterId)
                    comics?.retry()
                },
                onDissmissClick = {
                    activity?.finish()
                }
            )
            state.exception
        }
    }
}

@Composable
fun CharacterDetail(
    comics: List<ComicsItem?>,
    imagePath: String,
    title: String,
    description: String
) {

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            CharacterInformation(imagePath = imagePath, title = title, description = description)
        }

        items(comics.size) { index ->
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

@Composable
fun CharacterDetailLoading() {
    LazyColumn {
        item {
            CharacterInformationSkeleton()
        }
        items(10) {
            ComicRowSkeleton()
        }
    }
}

@Composable
@DarkAndLightPreview
fun CharacterDetailScreenPreview() {
    val fakeComicsList = listOf(
        ComicsItem("Comic 1", "18/10/2023", URL("https://fake.url/image1")),
        ComicsItem("Comic 2", "18/10/2023", URL("https://fake.url/image2"))
    )
    CharacterDetail(fakeComicsList, "https://fake.url/image2", "Spiderman", "Vecino y amigo")
}

@Composable
@DarkAndLightPreview
fun CharacterDetailLoadingScreenPreview() {
    CharacterDetailLoading()
}