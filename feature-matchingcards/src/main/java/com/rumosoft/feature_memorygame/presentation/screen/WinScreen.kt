package com.rumosoft.feature_memorygame.presentation.screen

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.rumosoft.feature_memorygame.R
import com.rumosoft.feature_memorygame.presentation.component.EndResult
import com.rumosoft.library_components.presentation.theme.MemoryGameTheme

@Composable
internal fun WinScreen(
    modifier: Modifier = Modifier,
    onRestart: () -> Unit = {},
) {
    EndResult(
        text = stringResource(id = R.string.win_message),
        drawable = com.rumosoft.library_simpsons_data.R.drawable.win,
        buttonText = stringResource(id = R.string.restart),
        modifier = modifier,
        buttonAction = onRestart,
    )
}

@Preview
@Composable
fun WinScreenPreview() {
    MemoryGameTheme {
        Surface {
            WinScreen()
        }
    }
}
