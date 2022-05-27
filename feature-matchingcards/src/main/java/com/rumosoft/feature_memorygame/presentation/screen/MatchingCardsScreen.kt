package com.rumosoft.feature_memorygame.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.rumosoft.feature_memorygame.R
import com.rumosoft.feature_memorygame.presentation.screen.state.BuildUI
import com.rumosoft.feature_memorygame.presentation.utils.configOrientation
import com.rumosoft.feature_memorygame.presentation.viewmodel.MatchingCardsViewModel
import com.rumosoft.library_components.presentation.component.TopBar

@Composable
fun MatchingCardsRoute(
    viewModel: MatchingCardsViewModel = hiltViewModel(),
    onBackPressed: () -> Unit = {},
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

    Scaffold(
        topBar = {
            TopBar(
                apBarText = stringResource(R.string.match_cards_game),
                leftIcon = painterResource(id = com.rumosoft.library_components.R.drawable.ic_arrow_back_24),
                leftIconPressed = onBackPressed,
            )
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
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
    }
}
