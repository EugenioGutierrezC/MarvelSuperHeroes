package com.eugenio.marvelsuperheroes.superheroslist.data.repository

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.eugenio.marvelsuperheroes.BuildConfig
import com.eugenio.marvelsuperheroes.core.utils.HashMD5
import com.eugenio.marvelsuperheroes.core.data.model.CharactersResponse
import com.eugenio.marvelsuperheroes.core.network.MarvelAPI
import javax.inject.Inject

class CharactersPagingSource @Inject constructor(
    private val marvelAPI: MarvelAPI,
    private val hashMD5: HashMD5,
) : PagingSource<Int, CharactersResponse.Data.Result>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharactersResponse.Data.Result> {
        return try {
            val currentPage = params.key ?: 0
            val timeStamp = System.currentTimeMillis().toString()
            val hash = hashMD5.generateMarvelHash(timeStamp, privateKey, apiKey)

            val response = marvelAPI.getCharacters(apiKey, timeStamp, hash, limit20, currentPage * limit20)
            val fullUrl = response.raw().request.url.toString()
            Log.d("MarvelAPI", "Calling URL: $fullUrl")

            LoadResult.Page(
                data = response.body()?.data?.results ?: emptyList(),
                prevKey = if (currentPage == 0) null else currentPage - 1,
                nextKey = if (response.body()?.data?.results.isNullOrEmpty()) null else currentPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
    override fun getRefreshKey(state: PagingState<Int, CharactersResponse.Data.Result>): Int? {
        return state.anchorPosition
    }

    companion object {
        const val apiKey = BuildConfig.API_KEY
        const val privateKey = BuildConfig.PRIVATE_KEY
        const val limit20 = 20
    }
}