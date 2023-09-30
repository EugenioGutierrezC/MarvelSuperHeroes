package com.eugenio.marvelsuperheroes.core.ui

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.eugenio.marvelsuperheroes.R
import com.eugenio.marvelsuperheroes.core.ui.multipreview.DarkAndLightPreview

@Composable
fun CircularAsyncImage(path: String, contentDescription: String, modifier: Modifier = Modifier) {
    AsyncImage(
        model = path,
        contentDescription = contentDescription,
        modifier = modifier
            .clip(CircleShape)
            .size(100.dp),
        placeholder = painterResource(id = R.drawable.marvel_logo_square),
        error = painterResource(id = R.drawable.marvel_logo_square),

    )
}

@DarkAndLightPreview
@Composable
fun CircularAsyncImagePreview() {
    CircularAsyncImage("", "")
}
