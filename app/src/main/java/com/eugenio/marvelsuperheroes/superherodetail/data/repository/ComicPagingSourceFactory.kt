package com.eugenio.marvelsuperheroes.superherodetail.data.repository

import com.eugenio.marvelsuperheroes.core.network.MarvelAPI
import com.eugenio.marvelsuperheroes.core.utils.HashMD5
import javax.inject.Inject

class ComicPagingSourceFactory @Inject constructor(
    private val marvelAPI: MarvelAPI,
    private val hashMD5: HashMD5
) {
    fun create(characterId: Int): ComicPagingSource {
        return ComicPagingSource(marvelAPI, hashMD5, characterId)
    }
}