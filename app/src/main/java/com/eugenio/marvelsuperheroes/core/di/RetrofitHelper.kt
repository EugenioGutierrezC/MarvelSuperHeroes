package com.eugenio.marvelsuperheroes.core.di

import com.eugenio.marvelsuperheroes.BuildConfig
import com.eugenio.marvelsuperheroes.core.network.MarvelAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
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