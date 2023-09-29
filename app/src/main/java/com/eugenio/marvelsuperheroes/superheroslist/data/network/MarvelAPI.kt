package com.eugenio.marvelsuperheroes.superheroslist.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MarvelAPI {

    /**
     * Get all characters from Marvel
     */
    @GET("v1/public/characters")
    suspend fun getCharacters(): Response<CharactersResponse>

    /**
     * Get specific character by [characterId]
     */
    @GET("v1/public/characters/{characterId}")
    suspend fun getCharacterById(@Path("characterId") characterId: Int): Response<CharactersResponse>
}