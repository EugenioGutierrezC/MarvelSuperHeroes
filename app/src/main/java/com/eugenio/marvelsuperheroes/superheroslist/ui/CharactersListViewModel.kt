package com.eugenio.marvelsuperheroes.superheroslist.ui


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.eugenio.marvelsuperheroes.superheroslist.domain.CharactersUseCase
import com.eugenio.marvelsuperheroes.superheroslist.ui.model.CharacterViewItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class CharactersListViewModel @Inject constructor(
    private val charactersUseCase: CharactersUseCase
): ViewModel()  {
    private val _reloadTrigger = MutableStateFlow(Unit)

    @OptIn(ExperimentalCoroutinesApi::class)
    val charactersFlow: Flow<PagingData<CharacterViewItem>> = _reloadTrigger.flatMapLatest {
        Log.d("CharactersListViewModel", "Fetching new data after trigger")
        charactersUseCase.getCharactersPaged()
    }.cachedIn(viewModelScope)

    fun reloadData() {
        Log.d("CharactersListViewModel", "reloadData called")
        _reloadTrigger.value = Unit
    }
}