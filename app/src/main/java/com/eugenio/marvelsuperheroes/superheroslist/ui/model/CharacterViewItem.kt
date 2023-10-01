package com.eugenio.marvelsuperheroes.superheroslist.ui.model

import java.net.URL

data class CharacterViewItem(
    val id: Int,
    val name: String,
    val comics: Int,
    val thumbnail: URL,
)
