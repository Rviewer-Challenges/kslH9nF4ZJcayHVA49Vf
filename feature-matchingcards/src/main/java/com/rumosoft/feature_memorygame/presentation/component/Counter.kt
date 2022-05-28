package com.rumosoft.feature_memorygame.presentation.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.rumosoft.library_components.presentation.theme.MemoryGameTheme

@Composable
internal fun Counter(text: String) {
    Text(
        text = text,
        style = MemoryGameTheme.typography.body1,
    )
}