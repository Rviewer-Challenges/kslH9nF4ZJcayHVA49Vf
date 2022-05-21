package com.rumosoft.feature_memorygame.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rumosoft.feature_memorygame.domain.entity.GameCard
import com.rumosoft.feature_memorygame.domain.entity.Level
import com.rumosoft.feature_memorygame.domain.entity.Orientation
import com.rumosoft.feature_memorygame.domain.entity.flipCard
import com.rumosoft.feature_memorygame.domain.usecase.GetBoardUseCase
import com.rumosoft.feature_memorygame.presentation.navigation.destination.MatchingCardsDestination
import com.rumosoft.feature_memorygame.presentation.viewmodel.state.Loading
import com.rumosoft.feature_memorygame.presentation.viewmodel.state.MatchingCardsState
import com.rumosoft.feature_memorygame.presentation.viewmodel.state.Ready
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.temporal.ChronoUnit
import javax.inject.Inject

private const val ONE_MINUTE = 60
private const val ONE_SEC_IN_MILLIS = 1_000L

@HiltViewModel
class MatchingCardsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getBoardUseCase: GetBoardUseCase,
) : ViewModel() {
    val uiState: StateFlow<MatchingCardsState> get() = _uiState
    private val _uiState = MutableStateFlow<MatchingCardsState>(Loading)

    private val level: Level = checkNotNull(
        Level.getByValue(savedStateHandle[MatchingCardsDestination.levelArg])
    )
    private var startingTime: Instant? = null
    private var finished = false

    fun retrieveBoardInfo(orientation: Orientation) {
        val boardInfo = getBoardUseCase(level, orientation)
        startingTime = Instant.now()
        val remainingTime = ONE_MINUTE - ChronoUnit.SECONDS.between(Instant.now(), startingTime)
        _uiState.update {
            Ready(
                level = level,
                board = boardInfo,
                time = remainingTime,
                remainingPairs = boardInfo.cards.size / 2,
            )
        }
        viewModelScope.launch {
            startCounter()
        }
    }

    private suspend fun startCounter() {
        while (!finished) {
            delay(ONE_SEC_IN_MILLIS)
            val remainingTime = ONE_MINUTE - ChronoUnit.SECONDS.between(startingTime, Instant.now())
            (_uiState.value as? Ready)?.let { state ->
                _uiState.update {
                    state.copy(time = remainingTime)
                }
            }
            if (remainingTime <= 0) {
                finished = true
            }
        }
    }

    fun onCardSelected(card: GameCard) {
        _uiState.update { state ->
            if (state is Ready) {
                state.copy(board = state.board.flipCard(card))
            } else {
                state
            }
        }
    }
}