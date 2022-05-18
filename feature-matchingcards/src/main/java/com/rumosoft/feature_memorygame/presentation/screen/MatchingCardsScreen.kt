package com.rumosoft.feature_memorygame.presentation.screen

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.rumosoft.feature_memorygame.presentation.viewmodel.MatchingCardsViewModel
import com.rumosoft.feature_memorygame.presentation.viewmodel.state.MatchingCardsState

@Composable
fun MatchingCardsRoute(
    viewModel: MatchingCardsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    MatchingCardsScreen(uiState)
}

@Composable
private fun MatchingCardsScreen(uiState: MatchingCardsState) {
    Text("Matching Cards Level: ${uiState.level}")
}