package com.eugenio.marvelsuperheroes.superherodetail.data.repository

import android.util.Log
import com.eugenio.marvelsuperheroes.BuildConfig
import com.eugenio.marvelsuperheroes.core.utils.HashMD5
import com.eugenio.marvelsuperheroes.core.utils.IoDispatcher
import com.eugenio.marvelsuperheroes.core.data.model.CharactersResponse
import com.eugenio.marvelsuperheroes.core.network.MarvelAPI
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CharacterDetailRepository @Inject constructor(
    private val marvelAPI: MarvelAPI,
    private val hashMD5: HashMD5,
    @IoDispatcher private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
){
    suspend fun getCharacter(characterId: Int): CharactersResponse?{
        return withContext(defaultDispatcher){
            val timeStamp = System.currentTimeMillis().toString()
            val hash = hashMD5.generateMarvelHash(timeStamp, privateKey, apiKey)

            val response = marvelAPI.getCharacterById(characterId = characterId, apiKey, timeStamp, hash)
            val fullUrl = response.raw().request.url.toString()
            Log.d("MarvelAPI", "Calling URL: $fullUrl")

            response.body()
        }
    }

    companion object {
        const val apiKey = BuildConfig.API_KEY
        const val privateKey = BuildConfig.PRIVATE_KEY
    }
}