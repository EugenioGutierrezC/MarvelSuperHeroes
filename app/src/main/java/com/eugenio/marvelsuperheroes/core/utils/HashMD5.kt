package com.eugenio.marvelsuperheroes.core.utils

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.math.BigInteger
import java.security.MessageDigest
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HashMD5 @Inject constructor()  {

    @Singleton
    fun generateMarvelHash(timeStamp: String, privateKey: String, apiKey: String): String {
        require(timeStamp.isNotEmpty()) {
            throw IllegalArgumentException("Timestamp must not be empty")
        }

        require(privateKey.isNotEmpty()) {
            throw IllegalArgumentException("PrivateKey must not be empty")
        }

        require(apiKey.isNotEmpty()) {
            throw IllegalArgumentException("ApiKey must not be empty")
        }

        val combined = timeStamp + privateKey + apiKey
        return combined.toMd5()
    }

    @Singleton
    private fun String.toMd5(): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
    }
}