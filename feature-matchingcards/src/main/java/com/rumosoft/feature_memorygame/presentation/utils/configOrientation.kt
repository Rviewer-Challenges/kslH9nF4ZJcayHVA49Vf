package com.rumosoft.feature_memorygame.presentation.utils

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import com.rumosoft.feature_memorygame.domain.entity.Orientation

@Composable
fun configOrientation(): Orientation {
    val configuration = LocalConfiguration.current
    return when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            Orientation.Landscape
        }
        else -> {
            Orientation.Portrait
        }
    }
}