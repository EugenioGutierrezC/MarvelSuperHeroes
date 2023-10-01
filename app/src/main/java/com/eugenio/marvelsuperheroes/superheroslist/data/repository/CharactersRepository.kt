package com.eugenio.marvelsuperheroes.superheroslist.data.repository

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

class CharactersRepository @Inject constructor(
    private val marvelAPI: MarvelAPI,
    private val hashMD5: HashMD5,
    @IoDispatcher private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
){
    private var offset = 0
    suspend fun getAllCharactersEveryTwenty(): CharactersResponse?{
        return withContext(defaultDispatcher){
            val timeStamp = System.currentTimeMillis().toString()
            val hash = hashMD5.generateMarvelHash(timeStamp, privateKey, apiKey)

            val response = marvelAPI.getCharacters(apiKey, timeStamp, hash, limit20, offset)
            val fullUrl = response.raw().request.url.toString()
            Log.d("MarvelAPI", "Calling URL: $fullUrl")

            response.body()
        }
    }

    fun incrementOffsetBy() {
        offset += limit20
    }

    fun resetOffset() {
        offset = 0
    }

    companion object {
        const val apiKey = BuildConfig.API_KEY
        const val privateKey = BuildConfig.PRIVATE_KEY
        const val limit20 = 20
    }
}