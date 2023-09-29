package com.eugenio.marvelsuperheroes.core.di

import com.eugenio.marvelsuperheroes.BuildConfig
import com.eugenio.marvelsuperheroes.superheroslist.data.network.MarvelAPI
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RetrofitHelper {

    @Singleton
    @Provides
    fun providerRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideMarvelCharactersClient(retrofit: Retrofit): MarvelAPI {
        return retrofit.create(MarvelAPI::class.java)
    }

}