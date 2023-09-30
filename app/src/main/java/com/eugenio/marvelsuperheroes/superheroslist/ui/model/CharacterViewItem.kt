package com.eugenio.marvelsuperheroes.superheroslist.ui.model

import java.net.URL

data class CharacterViewItem(
    val id: String,
    val name: String,
    val comics: String,
    val thumbnail: URL,
)
