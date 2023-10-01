package com.eugenio.marvelsuperheroes.superheroslist.domain.mapper

import com.eugenio.marvelsuperheroes.core.data.model.CharactersResponse
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
        val httpsUrl = ensureHttps("${character.thumbnail.path}.${character.thumbnail.extension}")
        val thumbnailUrl = URL(httpsUrl)
        return CharacterViewItem(
            id = character.id,
            name = character.name,
            comics = character.comics.available,
            thumbnail = thumbnailUrl
        )
    }

    @Singleton
    fun mapToViewItems(characters: List<CharactersResponse.Data.Result>): List<CharacterViewItem> {
        return characters.map { mapToViewItem(it) }
    }

    private fun ensureHttps(url: String): String {
        return if (url.startsWith("http://")) {
            url.replace("http://", "https://")
        } else {
            url
        }
    }
}