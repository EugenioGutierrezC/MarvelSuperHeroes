package com.eugenio.marvelsuperheroes.superherodetail.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.eugenio.marvelsuperheroes.R
import com.eugenio.marvelsuperheroes.core.ui.multipreview.DarkAndLightPreview

@Composable
fun CharacterInformation(imagePath: String, title: String, description: String) {
    Column {
        AsyncImage(
            model = imagePath,
            contentDescription = null,
            modifier = Modifier
                .height(LocalConfiguration.current.screenHeightDp.dp * 0.25f)
                .fillMaxWidth(),
            placeholder = painterResource(id = R.drawable.marvel_logo_square),
            error = painterResource(id = R.drawable.marvel_logo_square)
        )

        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
        )

        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(vertical = 8.dp)
        )
    }
}

@Composable
@DarkAndLightPreview
fun CharacterInformationPreview() {
    CharacterInformation("", "Spiderma", "Tu vecino y amigo")
}