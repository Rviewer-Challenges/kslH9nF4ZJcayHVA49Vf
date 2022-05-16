package com.rumosoft.library_components.presentation.component

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
    Button(
        onClick = onClick,
        modifier = modifier
            .defaultMinSize(
                minWidth = 160.dp,
                minHeight = 96.dp
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

@Preview
@Composable
fun PreviewButton() {
    MemoryGameTheme {
        BigRoundedButton("Easy")
    }
}
