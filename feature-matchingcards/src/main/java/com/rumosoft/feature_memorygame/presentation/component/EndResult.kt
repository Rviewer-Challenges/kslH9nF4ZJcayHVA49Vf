package com.rumosoft.feature_memorygame.presentation.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rumosoft.library_components.presentation.theme.MemoryGameTheme
import com.rumosoft.library_simpsons_data.R

@Composable
fun EndResult(
    text: String,
    @DrawableRes drawable: Int,
    buttonText: String,
    modifier: Modifier = Modifier,
    buttonAction: () -> Unit = {},
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = text,
            style = MemoryGameTheme.typography.h1,
        )
        Image(
            painter = painterResource(id = drawable),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.height(30.dp))
        Button(onClick = buttonAction) {
            Text(buttonText)
        }
    }
}

@Preview
@Composable
fun EndResultPreview() {
    MemoryGameTheme {
        EndResult("You Win!", R.drawable.win, "Restart")
    }
}