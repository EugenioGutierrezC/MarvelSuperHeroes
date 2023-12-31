package com.eugenio.marvelsuperheroes.superheroslist.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.eugenio.marvelsuperheroes.R
import com.eugenio.marvelsuperheroes.core.ui.CircularAsyncImage
import com.eugenio.marvelsuperheroes.core.ui.multipreview.DarkAndLightPreview
import com.eugenio.marvelsuperheroes.core.ui.shimmer.shimmerEffect
import java.net.URL

@Composable
fun CharacterRow(
    modifier: Modifier = Modifier,
    characterName: String,
    totalComics: Int,
    imageUrl: URL
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        CircularAsyncImage(path = imageUrl.toString(), contentDescription = characterName)
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = characterName,
                style = if (characterName.length > 20) MaterialTheme.typography.headlineSmall else MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(8.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_comic_24),
                    contentDescription = "Comics"
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = totalComics.toString(),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun CharacterRowShimmer() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .shimmerEffect()
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(40.dp)
                    .padding(8.dp)
                    .shimmerEffect()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .shimmerEffect()
                )

                Spacer(modifier = Modifier.width(4.dp))

                Box(
                    modifier = Modifier
                        .width(50.dp)
                        .height(16.dp)
                        .shimmerEffect()
                )
            }
        }
    }
}


@Composable
@DarkAndLightPreview
fun CharacterRowPreview() {
    val url =
        URL("https://i.annihil.us/u/prod/marvel/i/mg/9/50/4ce5a385a2e82/standard_fantastic.jpg")
    CharacterRow(Modifier,"Spiderman", 575, url)
}

@Composable
@DarkAndLightPreview
fun CharacterRowSkeletonPreview() {
    CharacterRowShimmer()
}