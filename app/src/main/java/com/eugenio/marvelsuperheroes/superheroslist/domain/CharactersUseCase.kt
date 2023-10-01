package com.eugenio.marvelsuperheroes.superheroslist.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.eugenio.marvelsuperheroes.superheroslist.data.repository.CharacterPagingSource
import com.eugenio.marvelsuperheroes.superheroslist.data.repository.CharactersRepository
import com.eugenio.marvelsuperheroes.superheroslist.domain.mapper.CharactersMapper
import com.eugenio.marvelsuperheroes.superheroslist.ui.model.CharacterViewItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CharactersUseCase @Inject constructor(
    private val mapper: CharactersMapper,
    private val characterPagingSource: CharacterPagingSource
) {
    fun getCharactersPaged(): Flow<PagingData<CharacterViewItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = CharactersRepository.limit20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { characterPagingSource }
        ).flow.map { pagingData ->
            pagingData.map { mapper.mapToViewItem(it) }
        }
    }
}