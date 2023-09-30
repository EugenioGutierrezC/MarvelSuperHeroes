package com.eugenio.marvelsuperheroes.core.utils

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class HashMD5Test {

    private val hashMD5 = HashMD5()

    @Test
    fun `Correct generated hash MD5 with timeStamp, privateKey and apiKey`() {
        val timeStamp = "1655351172200"
        val privateKey = "8745a4v"
        val apiKey = "782496rg1"

        val expectedHash = "a13dcb04d6535142bf3bb776b47fd735"
        val result = hashMD5.generateMarvelHash(timeStamp, privateKey, apiKey)

        assertEquals(expectedHash, result)
    }

    @Test
    fun `Incorrect generated hash MD5 with timeStamp, privateKey and apiKey`() {
        val timeStamp = "999999"
        val privateKey = "8745a4v"
        val apiKey = "782496rg1"

        val expectedHash = "a13dcb04d6535142bf3bb776b47fd735"
        val result = hashMD5.generateMarvelHash(timeStamp, privateKey, apiKey)

        assertNotEquals(expectedHash, result)
    }

    @Test
    fun `generateMarvelHash throws exception when timestamp is empty`() {
        val timeStamp = ""
        val privateKey = "8745a4v"
        val apiKey = "782496rg1"

        assertThrows(IllegalArgumentException::class.java) {
            hashMD5.generateMarvelHash(timeStamp, privateKey, apiKey)
        }
    }

    @Test
    fun `generateMarvelHash throws exception when privateKey is empty`() {
        val timeStamp = "999999"
        val privateKey = ""
        val apiKey = "782496rg1"

        assertThrows(IllegalArgumentException::class.java) {
            hashMD5.generateMarvelHash(timeStamp, privateKey, apiKey)
        }
    }

    @Test
    fun `generateMarvelHash throws exception when apiKey is empty`() {
        val timeStamp = "999999"
        val privateKey = "8745a4v"
        val apiKey = ""

        assertThrows(IllegalArgumentException::class.java) {
            hashMD5.generateMarvelHash(timeStamp, privateKey, apiKey)
        }
    }
}