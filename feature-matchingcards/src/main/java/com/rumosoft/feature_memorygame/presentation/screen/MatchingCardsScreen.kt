package com.rumosoft.feature_memorygame.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.rumosoft.feature_memorygame.presentation.screen.state.BuildUI
import com.rumosoft.feature_memorygame.presentation.utils.configOrientation
import com.rumosoft.feature_memorygame.presentation.viewmodel.MatchingCardsViewModel
import timber.log.Timber

@Composable
fun MatchingCardsRoute(
    viewModel: MatchingCardsViewModel = hiltViewModel(),
    onWin: () -> Unit = {},
    onLose: () -> Unit = {},
) {
    val orientation = configOrientation()
    var orientationPassed by rememberSaveable { mutableStateOf(false) }
    if (!orientationPassed) { // To prevent it to be initialized multiple times on orientation change
        LaunchedEffect(Unit) {
            viewModel.retrieveBoard(orientation)
            orientationPassed = true
        }
    }
    val uiState by viewModel.uiState.collectAsState()

    uiState.BuildUI(
        onCardSelected = viewModel::onCardSelected,
        onWin = {
            viewModel.reset()
            onWin()
        },
        onLose = {
            viewModel.reset()
            onLose()
        },
    )
}
