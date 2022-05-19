package com.rumosoft.feature_memorygame.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.rumosoft.feature_memorygame.domain.entity.Level
import com.rumosoft.feature_memorygame.domain.entity.Orientation
import com.rumosoft.feature_memorygame.domain.usecase.GetBoardInfoUseCase
import com.rumosoft.feature_memorygame.presentation.navigation.destination.MatchingCardsDestination
import com.rumosoft.feature_memorygame.presentation.viewmodel.state.Loading
import com.rumosoft.feature_memorygame.presentation.viewmodel.state.MatchingCardsState
import com.rumosoft.feature_memorygame.presentation.viewmodel.state.Ready
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class MatchingCardsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getBoardInfoUseCase: GetBoardInfoUseCase,
) : ViewModel() {
    val uiState: StateFlow<MatchingCardsState> get() = _uiState
    private val _uiState = MutableStateFlow<MatchingCardsState>(Loading)

    val level: Level = checkNotNull(
        Level.getByValue(savedStateHandle[MatchingCardsDestination.levelArg])
    )

    fun retrieveBoardInfo(orientation: Orientation) {
        _uiState.update {
            Ready(
                level = level,
                boardInfo = getBoardInfoUseCase.invoke(level, orientation)
            )
        }
    }
}