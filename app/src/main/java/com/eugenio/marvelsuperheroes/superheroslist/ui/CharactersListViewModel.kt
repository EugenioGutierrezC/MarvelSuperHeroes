package com.eugenio.marvelsuperheroes.superheroslist.ui


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eugenio.marvelsuperheroes.core.utils.ViewState
import com.eugenio.marvelsuperheroes.superheroslist.domain.CharactersUseCase
import com.eugenio.marvelsuperheroes.superheroslist.ui.model.CharacterViewItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersListViewModel @Inject constructor(
    private val charactersUseCase: CharactersUseCase
): ViewModel()  {
    val myDataState = mutableStateOf<ViewState<List<CharacterViewItem>>>(ViewState.Loading)

    fun getCharacters() {
        viewModelScope.launch {
            try {
                val result = charactersUseCase()
                myDataState.value = ViewState.Success(result ?: emptyList())
            } catch (e: Exception) {
                myDataState.value = ViewState.Error(e)
            }
        }
    }
}