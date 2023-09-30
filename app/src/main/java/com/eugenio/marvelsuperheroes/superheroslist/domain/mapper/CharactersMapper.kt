package com.eugenio.marvelsuperheroes.superheroslist.domain.mapper

import com.eugenio.marvelsuperheroes.superheroslist.data.model.CharactersResponse
import com.eugenio.marvelsuperheroes.superheroslist.ui.model.CharacterViewItem
import java.net.URL

class CharactersMapper {

    fun mapToViewItem(character: CharactersResponse.Data.Result): CharacterViewItem {
        val thumbnailUrl = URL("${character.thumbnail.path}.${character.thumbnail.extension}")
        return CharacterViewItem(
            id = character.id.toString(),
            name = character.name,
            comics = character.comics.available.toString(),
            thumbnail = thumbnailUrl
        )
    }

    fun mapToViewItems(characters: List<CharactersResponse.Data.Result>): List<CharacterViewItem> {
        return characters.map { mapToViewItem(it) }
    }

}