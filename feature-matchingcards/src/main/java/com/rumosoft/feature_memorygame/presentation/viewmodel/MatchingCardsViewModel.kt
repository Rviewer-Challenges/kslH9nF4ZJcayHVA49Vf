package com.rumosoft.feature_memorygame.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rumosoft.feature_memorygame.domain.entity.GameCard
import com.rumosoft.feature_memorygame.domain.entity.Level
import com.rumosoft.feature_memorygame.domain.entity.Orientation
import com.rumosoft.feature_memorygame.domain.entity.flipCard
import com.rumosoft.feature_memorygame.domain.entity.isReversed
import com.rumosoft.feature_memorygame.domain.entity.matched
import com.rumosoft.feature_memorygame.domain.entity.numPairs
import com.rumosoft.feature_memorygame.domain.entity.resetCards
import com.rumosoft.feature_memorygame.domain.usecase.GetBoardUseCase
import com.rumosoft.feature_memorygame.presentation.navigation.destination.MatchingCardsDestination
import com.rumosoft.feature_memorygame.presentation.viewmodel.state.Loading
import com.rumosoft.feature_memorygame.presentation.viewmodel.state.Lose
import com.rumosoft.feature_memorygame.presentation.viewmodel.state.MatchingCardsState
import com.rumosoft.feature_memorygame.presentation.viewmodel.state.Ready
import com.rumosoft.feature_memorygame.presentation.viewmodel.state.Win
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
    private var flippedCards: Pair<GameCard?, GameCard?> = null to null

    fun retrieveBoard(orientation: Orientation) {
        val board = getBoardUseCase(level, orientation)
        startingTime = Instant.now()
        val remainingTime = ONE_MINUTE - ChronoUnit.SECONDS.between(Instant.now(), startingTime)
        _uiState.update {
            Ready(
                level = level,
                board = board,
                time = remainingTime,
                remainingPairs = board.numPairs,
            )
        }
        viewModelScope.launch {
            startCounter()
        }
    }

    private suspend fun startCounter() {
        while (true) {
            delay(ONE_SEC_IN_MILLIS)
            val remainingTime = ONE_MINUTE - ChronoUnit.SECONDS.between(startingTime, Instant.now())
            (_uiState.value as? Ready)?.let { state ->
                _uiState.update {
                    state.copy(time = remainingTime)
                }
            }
            if (remainingTime <= 0) {
                _uiState.update { Lose }
            }
        }
    }

    fun onCardSelected(card: GameCard) {
        _uiState.update { state ->
            if (state is Ready && card.isReversed()) {
                val (first, second) = flippedCards
                if (first != null && second != null) {
                    flippedCards = card to null
                    state.copy(
                        board = state.board.resetCards(first, second).flipCard(card)
                    )
                } else {
                    val matched = checkMatches(card)
                    val remainingPairs = if (matched) {
                        state.remainingPairs - 1
                    } else {
                        state.remainingPairs
                    }
                    if (remainingPairs == 0) {
                        Win
                    } else {
                        state.copy(
                            board = state.board.flipCard(card).let {
                                if (matched) it.matched(card.characterId) else it
                            },
                            remainingPairs = remainingPairs
                        )
                    }
                }
            } else {
                state
            }
        }
    }

    fun reset() {
        _uiState.update {
            Loading
        }
    }

    private fun checkMatches(card: GameCard): Boolean {
        var matched = false
        val (first, _) = flippedCards
        flippedCards = if (first == null) {
            card to null
        } else {
            if (first.characterId == card.characterId) {
                matched = true
            }
            first to card
        }
        return matched
    }
}
