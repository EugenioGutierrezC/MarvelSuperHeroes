package com.eugenio.marvelsuperheroes.superherodetail.domain.mapper

import com.eugenio.marvelsuperheroes.core.data.model.CharacterComicsResponse
import com.eugenio.marvelsuperheroes.core.utils.ensureHttps
import com.eugenio.marvelsuperheroes.superherodetail.ui.model.ComicsItem
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.net.URL
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ComicMapper @Inject constructor()  {

    @Singleton
    fun mapToComicsItem(comic: CharacterComicsResponse.Data.Result): ComicsItem {
        val httpsUrl = ensureHttps("${comic.thumbnail.path}.${comic.thumbnail.extension}")
        val thumbnailUrl = URL(httpsUrl)

        val onsaleDate = comic.dates.find { it.type == SALE_DATE }?.date ?: TBA

        return ComicsItem(
            name = comic.title,
            date = onsaleDate,
            thumbnail = thumbnailUrl
        )
    }

    companion object {
        private const val SALE_DATE = "onsaleDate"
        private const val TBA = "TBA"
    }
}