package com.eugenio.marvelsuperheroes.core.ui.multipreview

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    name = "DarkMode",
    uiMode = Configuration.UI_MODE_NIGHT_MASK and Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    backgroundColor = 0xFF0C0B13
)
@Preview(
    name = "LightMode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
annotation class DarkAndLightPreview()
