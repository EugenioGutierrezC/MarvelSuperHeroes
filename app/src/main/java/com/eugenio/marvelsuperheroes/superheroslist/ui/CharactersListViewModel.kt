package com.eugenio.marvelsuperheroes.superheroslist.ui


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.eugenio.marvelsuperheroes.superheroslist.domain.CharactersUseCase
import com.eugenio.marvelsuperheroes.superheroslist.ui.model.CharacterViewItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CharactersListViewModel @Inject constructor(
    private val charactersUseCase: CharactersUseCase
): ViewModel()  {
    val charactersFlow: Flow<PagingData<CharacterViewItem>> = charactersUseCase.getCharactersPaged().cachedIn(viewModelScope)
}