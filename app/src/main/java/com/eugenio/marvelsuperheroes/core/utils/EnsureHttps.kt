package com.eugenio.marvelsuperheroes.core.utils

fun ensureHttps(url: String): String {
    return if (url.startsWith("http://")) {
        url.replace("http://", "https://")
    } else {
        url
    }
}