package com.eugenio.marvelsuperheroes.core.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.eugenio.marvelsuperheroes.R

@Composable
fun ErrorDialog(onButtonClick: () -> Unit, onDissmissClick: () -> Unit) {
    AlertDialog(
        title = { Text(text = stringResource(id = R.string.connection_error_title)) },
        text = { Text(text = stringResource(id = R.string.connection_error_description)) },
        onDismissRequest = onDissmissClick,
        confirmButton = {
            Button(onClick = onButtonClick) {
                Text(text = stringResource(id = R.string.connection_error_try_again_button))
            }
        },
        dismissButton = {
            TextButton(onClick = onDissmissClick) {
                Text(text = stringResource(id = R.string.connection_error_close_button))
            }
        }
    )
}