package com.eugenio.marvelsuperheroes.superherodetail.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.eugenio.marvelsuperheroes.core.utils.ViewState
import com.eugenio.marvelsuperheroes.superherodetail.domain.CharacterDetailUseCase
import com.eugenio.marvelsuperheroes.superherodetail.ui.model.CharacterDetailItem
import com.eugenio.marvelsuperheroes.superherodetail.ui.model.ComicsItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.net.URL
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val characterDetailUseCase: CharacterDetailUseCase
): ViewModel() {
    val myDataState = mutableStateOf<ViewState<CharacterDetailItem>>(ViewState.Loading)
    var charactersFlow: Flow<PagingData<ComicsItem>>? = null

    fun initialize(characterId: Int) {
        charactersFlow = characterDetailUseCase.getComics(characterId).cachedIn(viewModelScope)
    }

    fun getCharacters(characterId: Int) {
        viewModelScope.launch {
            try {
                val result = characterDetailUseCase(characterId)
                myDataState.value = ViewState.Success(result ?: CharacterDetailItem("","", URL("")))
            } catch (e: Exception) {
                myDataState.value = ViewState.Error(e)
            }
        }
    }
}