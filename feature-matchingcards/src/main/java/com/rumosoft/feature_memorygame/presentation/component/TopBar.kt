package com.rumosoft.feature_memorygame.presentation.component

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.rumosoft.feature_memorygame.R
import com.rumosoft.library_components.presentation.theme.MemoryGameTheme

@Composable
fun TopBar(
    apBarText: String,
    leftIcon: Painter,
    leftIconPressed: () -> Unit = {}
) {
    TopAppBar(
        title = { Text(text = apBarText) },
        navigationIcon = {
            IconButton(onClick = { leftIconPressed.invoke() }) {
                Icon(
                    leftIcon,
                    contentDescription = stringResource(id = R.string.screen_title),
                    tint = Color.White,
                )
            }
        },
    )
}

@Preview
@Composable
fun TopBarPreview() {
    MemoryGameTheme {
        TopBar(
            apBarText = "App Bar Text",
            leftIcon = painterResource(id = R.drawable.ic_arrow_back_24)
        )
    }
}
