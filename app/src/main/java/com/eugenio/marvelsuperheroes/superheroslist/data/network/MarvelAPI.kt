package com.eugenio.marvelsuperheroes.superheroslist.data.network

import com.eugenio.marvelsuperheroes.superheroslist.data.model.CharactersResponse
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