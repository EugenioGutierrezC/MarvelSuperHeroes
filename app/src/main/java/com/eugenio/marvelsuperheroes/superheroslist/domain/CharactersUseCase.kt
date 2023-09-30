package com.eugenio.marvelsuperheroes.superheroslist.domain

import com.eugenio.marvelsuperheroes.superheroslist.data.repository.CharactersRepository
import com.eugenio.marvelsuperheroes.superheroslist.domain.mapper.CharactersMapper
import com.eugenio.marvelsuperheroes.superheroslist.ui.model.CharacterViewItem
import javax.inject.Inject

class CharactersUseCase @Inject constructor(
    private val repository: CharactersRepository,
    private val mapper: CharactersMapper
) {
    suspend operator fun invoke(): List<CharacterViewItem>? {
        val response = repository.getAllCharactersEveryTwenty()
        repository.incrementOffsetBy()
        return response?.data?.results?.let { mapper.mapToViewItems(it) }
    }
}