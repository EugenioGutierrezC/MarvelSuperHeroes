package com.eugenio.marvelsuperheroes.superherodetail.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.eugenio.marvelsuperheroes.R
import com.eugenio.marvelsuperheroes.core.ui.multipreview.DarkAndLightPreview
import com.eugenio.marvelsuperheroes.core.ui.shimmer.shimmerEffect

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
fun CharacterInformationSkeleton() {
    Column {
        Box(
            modifier = Modifier
                .height(LocalConfiguration.current.screenHeightDp.dp * 0.25f)
                .fillMaxWidth()
                .shimmerEffect()
        )

        Spacer(modifier = Modifier.height(8.dp))

        SkeletonText(length = 15, style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(8.dp))

        SkeletonText(length = 40, style = MaterialTheme.typography.bodyMedium, lines = 3)
    }
}

@Composable
fun SkeletonText(length: Int, style: TextStyle, lines: Int = 1) {
    repeat(lines) {
        Box(
            modifier = Modifier
                .height(style.fontSize.value.dp)
                .width((style.fontSize.value * length).dp)
                .shimmerEffect()
        )
        Spacer(modifier = Modifier.height(4.dp))
    }
}

@Composable
@DarkAndLightPreview
fun CharacterInformationPreview() {
    CharacterInformation("", "Spiderma", "Tu vecino y amigo")
}

@Composable
@DarkAndLightPreview
fun CharacterInfoSkeletonPreview() {
    CharacterInformationSkeleton()
}