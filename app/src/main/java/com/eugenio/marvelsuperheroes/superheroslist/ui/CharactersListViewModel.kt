package com.eugenio.marvelsuperheroes.superheroslist.ui


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.eugenio.marvelsuperheroes.core.utils.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharactersListViewModel @Inject constructor(): ViewModel()  {
    val myDataState = mutableStateOf<ViewState<Unit>>(ViewState.Loading)
}