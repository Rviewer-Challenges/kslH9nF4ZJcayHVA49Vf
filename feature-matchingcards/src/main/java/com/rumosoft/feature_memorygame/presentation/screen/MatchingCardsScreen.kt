package com.rumosoft.feature_memorygame.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rumosoft.feature_memorygame.R
import com.rumosoft.feature_memorygame.presentation.utils.configOrientation
import com.rumosoft.feature_memorygame.presentation.viewmodel.MatchingCardsViewModel
import com.rumosoft.feature_memorygame.presentation.viewmodel.state.Loading
import com.rumosoft.feature_memorygame.presentation.viewmodel.state.MatchingCardsState
import com.rumosoft.feature_memorygame.presentation.viewmodel.state.Ready

@Composable
fun MatchingCardsRoute(
    viewModel: MatchingCardsViewModel = hiltViewModel()
) {
    viewModel.retrieveBoardInfo(configOrientation())
    val uiState by viewModel.uiState.collectAsState()
    MatchingCardsScreen(uiState)
}

@Composable
internal fun MatchingCardsScreen(uiState: MatchingCardsState) {
    when (uiState) {
        Loading -> MatchingCardsLoading()
        is Ready -> MatchingCardsBoard(uiState)
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
private fun MatchingCardsBoard(uiState: Ready) {
    Column {
        Text("Matching Cards Level: ${uiState.level} - BoardInfo: ${uiState.boardInfo}")
        val cardsDescription = stringResource(id = R.string.cards)
        LazyVerticalGrid(
            columns = GridCells.Fixed(uiState.boardInfo.columns),
            modifier = Modifier.semantics { contentDescription = cardsDescription }
        ) {
            items(count = uiState.boardInfo.cards) { index ->
                Card(
                    backgroundColor = Color.Blue,
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxSize()
                ) {
                    Text(
                        "${index + 1}",
                        fontSize = 35.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}
