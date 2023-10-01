package com.eugenio.marvelsuperheroes.superherodetail.domain.mapper

import com.eugenio.marvelsuperheroes.core.data.model.CharactersResponse
import com.eugenio.marvelsuperheroes.core.utils.ensureHttps
import com.eugenio.marvelsuperheroes.superherodetail.ui.model.CharacterDetailItem
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.net.URL
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CharacterDetailMapper @Inject constructor()  {

    @Singleton
    fun mapToDetailItem(character: CharactersResponse.Data.Result): CharacterDetailItem {
        val httpsUrl = ensureHttps("${character.thumbnail.path}.${character.thumbnail.extension}")
        val thumbnailUrl = URL(httpsUrl)
        return CharacterDetailItem(
            name = character.name,
            description = character.description,
            thumbnail = thumbnailUrl,
        )
    }



}