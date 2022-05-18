package com.rumosoft.feature_memorygame.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.rumosoft.feature_memorygame.domain.entity.Level
import com.rumosoft.feature_memorygame.presentation.navigation.destination.MatchingCardsDestination
import com.rumosoft.feature_memorygame.presentation.viewmodel.state.MatchingCardsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MatchingCardsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    val uiState: StateFlow<MatchingCardsState> get() = _uiState
    private val _uiState = MutableStateFlow(MatchingCardsState())

    init {
        _uiState.update {
            it.copy(
                level = checkNotNull(
                    Level.getByValue(savedStateHandle[MatchingCardsDestination.levelArg])
                )
            )
        }
    }
}