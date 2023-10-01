package com.eugenio.marvelsuperheroes.core.network

import com.eugenio.marvelsuperheroes.core.data.model.CharacterComicsResponse
import com.eugenio.marvelsuperheroes.core.data.model.CharactersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelAPI {

    /**
     * Get all characters from Marvel
     */
    @GET("v1/public/characters")
    suspend fun getCharacters(
        @Query("apikey") apiKey: String,
        @Query("ts") timeStamp: String,
        @Query("hash") hash: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Response<CharactersResponse>

    /**
     * Get specific character by name
     */
    @GET("v1/public/characters/{characterId}")
    suspend fun getCharacterById(
        @Path("characterId") characterId: Int,
        @Query("apikey") apiKey: String,
        @Query("ts") timeStamp: String,
        @Query("hash") hash: String,
    ): Response<CharactersResponse>

    /**
     * Get comics from a character
     */
    @GET("v1/public/characters/{characterId}/comics")
    suspend fun getCharacterComicsById(
        @Path("characterId") characterId: Int,
        @Query("apikey") apiKey: String,
        @Query("ts") timeStamp: String,
        @Query("hash") hash: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Response<CharacterComicsResponse>

    /**
     * Get specific character by name
     */
    @GET("v1/public/characters")
    suspend fun getCharacterByName(
        @Query("nameStartsWith") name: String,
        @Query("apikey") apiKey: String,
        @Query("ts") timeStamp: String,
        @Query("hash") hash: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Response<CharactersResponse>
}