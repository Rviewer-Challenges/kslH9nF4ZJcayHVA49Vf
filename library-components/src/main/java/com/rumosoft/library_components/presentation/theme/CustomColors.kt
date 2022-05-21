package com.rumosoft.library_components.presentation.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class CustomColors(
    val colorEasy: Color = PuertoRico,
    val colorMedium: Color = CornflowerBlue,
    val colorDifficult: Color = SunsetOrange,
    val white: Color = White,
)

internal val LocalColors = staticCompositionLocalOf { CustomColors() }
