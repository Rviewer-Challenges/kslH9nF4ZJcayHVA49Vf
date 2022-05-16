package com.rumosoft.feature_memorygame.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rumosoft.feature_memorygame.R
import com.rumosoft.library_components.presentation.component.BigRoundedButton
import com.rumosoft.library_components.presentation.theme.MemoryGameTheme

@Composable
fun LevelSelectionRoute(onLevelSelected: () -> Unit = {}) {
    LevelSelectionScreen(onLevelSelected)
}

@Composable
fun LevelSelectionScreen(onLevelSelected: () -> Unit = {}) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
    ) {
        Text(
            text = stringResource(id = R.string.select_level_title),
            style = MaterialTheme.typography.h3,
        )
        BigRoundedButton(
            text = stringResource(id = R.string.level_easy),
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = MemoryGameTheme.extraColors.colorEasy),
        )
        BigRoundedButton(
            text = stringResource(id = R.string.level_medium),
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = MemoryGameTheme.extraColors.colorMedium),
        )
        BigRoundedButton(
            text = stringResource(id = R.string.level_difficult),
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = MemoryGameTheme.extraColors.colorDifficult),
        )
    }
}

@Preview(device = Devices.PIXEL_2_XL, showBackground = true)
@Composable
fun DefaultPreview() {
    MemoryGameTheme {
        LevelSelectionScreen()
    }
}