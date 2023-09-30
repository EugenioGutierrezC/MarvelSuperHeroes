package com.eugenio.marvelsuperheroes.superheroslist.domain.mapper

import com.eugenio.marvelsuperheroes.superheroslist.data.model.CharactersResponse
import com.eugenio.marvelsuperheroes.superheroslist.ui.model.CharacterViewItem
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.net.URL
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CharactersMapper @Inject constructor()  {

    @Singleton
    fun mapToViewItem(character: CharactersResponse.Data.Result): CharacterViewItem {
        val thumbnailUrl = URL("${character.thumbnail.path}.${character.thumbnail.extension}")
        return CharacterViewItem(
            id = character.id.toString(),
            name = character.name,
            comics = character.comics.available.toString(),
            thumbnail = thumbnailUrl
        )
    }

    @Singleton
    fun mapToViewItems(characters: List<CharactersResponse.Data.Result>): List<CharacterViewItem> {
        return characters.map { mapToViewItem(it) }
    }

}