package com.eugenio.marvelsuperheroes.superherodetail.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.eugenio.marvelsuperheroes.superherodetail.data.repository.CharacterDetailRepository
import com.eugenio.marvelsuperheroes.superherodetail.data.repository.ComicPagingSourceFactory
import com.eugenio.marvelsuperheroes.superherodetail.domain.mapper.CharacterDetailMapper
import com.eugenio.marvelsuperheroes.superherodetail.domain.mapper.ComicMapper
import com.eugenio.marvelsuperheroes.superherodetail.ui.model.CharacterDetailItem
import com.eugenio.marvelsuperheroes.superherodetail.ui.model.ComicsItem
import com.eugenio.marvelsuperheroes.superheroslist.data.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CharacterDetailUseCase @Inject constructor(
    private val characterMapper: CharacterDetailMapper,
    private val comicMapper: ComicMapper,
    private val repository: CharacterDetailRepository,
    private var comicPagingSourceFactory: ComicPagingSourceFactory
) {
    suspend operator fun invoke(characterId: Int): CharacterDetailItem? {
        val response = repository.getCharacter(characterId)

        return response?.data?.results?.let { characterMapper.mapToDetailItem(it[FIRST_RESULT]) }
    }

    fun getComics(characterId: Int): Flow<PagingData<ComicsItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = CharactersRepository.limit20,
                prefetchDistance = 1
            ),
            pagingSourceFactory = { comicPagingSourceFactory.create(characterId) }
        ).flow.map { pagingData ->
            pagingData.map { comicMapper.mapToComicsItem(it) }
        }
    }

    companion object {
        private const val FIRST_RESULT = 0
    }
}