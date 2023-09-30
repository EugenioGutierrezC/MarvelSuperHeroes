package com.eugenio.marvelsuperheroes.core.utils

import java.math.BigInteger
import java.security.MessageDigest
class HashMD5 {
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

    private fun String.toMd5(): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
    }
}