package com.rumosoft.library_components.presentation.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rumosoft.library_components.presentation.theme.MemoryGameTheme

@Composable
fun BigRoundedButton(
    text: String,
    modifier: Modifier = Modifier,
    textColor: Color = MaterialTheme.colors.onPrimary,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    onClick: () -> Unit = {}
) {
    val configuration = LocalConfiguration.current
    Button(
        onClick = onClick,
        modifier = modifier
            .defaultMinSize(
                minWidth = 160.dp,
                minHeight = calcMinHeight(configuration)
            )
            .clip(RoundedCornerShape(50)),
        colors = colors,
    ) {
        Text(
            text = text,
            color = textColor,
            style = MaterialTheme.typography.h4
        )
    }
}

@Composable
private fun calcMinHeight(configuration: Configuration) =
    if (configuration.orientation != Configuration.ORIENTATION_LANDSCAPE) 96.dp
    else 72.dp

@Preview
@Composable
fun PreviewButton() {
    MemoryGameTheme {
        BigRoundedButton("Easy")
    }
}
