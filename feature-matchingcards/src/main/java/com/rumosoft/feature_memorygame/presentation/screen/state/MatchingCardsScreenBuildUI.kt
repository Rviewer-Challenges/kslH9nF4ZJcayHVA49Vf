package com.rumosoft.feature_memorygame.presentation.screen.state

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import com.rumosoft.feature_memorygame.R
import com.rumosoft.feature_memorygame.domain.entity.GameCard
import com.rumosoft.feature_memorygame.presentation.component.Counter
import com.rumosoft.feature_memorygame.presentation.component.GameBoard
import com.rumosoft.feature_memorygame.presentation.viewmodel.state.Loading
import com.rumosoft.feature_memorygame.presentation.viewmodel.state.Lose
import com.rumosoft.feature_memorygame.presentation.viewmodel.state.MatchingCardsState
import com.rumosoft.feature_memorygame.presentation.viewmodel.state.Ready
import com.rumosoft.feature_memorygame.presentation.viewmodel.state.Win
import com.rumosoft.library_components.presentation.theme.MemoryGameTheme
import timber.log.Timber

@Composable
fun MatchingCardsState.BuildUI(
    onCardSelected: (GameCard) -> Unit = {},
    onWin: () -> Unit = {},
    onLose: () -> Unit = {},
) {
    when (this) {
        Loading -> MatchingCardsLoading()
        is Ready -> MatchingCardsReady(
            this,
            onCardSelected
        )
        Win -> onWin()
        Lose -> onLose()
    }
}

@Composable
private fun MatchingCardsLoading() {
    val loadingDescription = stringResource(id = R.string.loading)
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .semantics { contentDescription = loadingDescription }
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun MatchingCardsReady(
    uiState: Ready,
    onCardSelected: (GameCard) -> Unit = {},
) {
    Column {
        Counters(uiState)
        GameBoard(
            uiState = uiState,
            onCardSelected = onCardSelected
        )
    }
}

@Composable
fun Counters(
    uiState: Ready,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(MemoryGameTheme.paddings.medium),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Counter("Moves: ${uiState.moves}")
        Counter("Remaining Pairs: ${uiState.remainingPairs}")
        Counter("Time: ${uiState.time}")
    }
}