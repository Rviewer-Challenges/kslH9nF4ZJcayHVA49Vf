package com.rumosoft.feature_memorygame.presentation.viewmodel.state

import com.rumosoft.feature_memorygame.domain.entity.Board
import com.rumosoft.feature_memorygame.domain.entity.Level

sealed class MatchingCardsState
object Loading: MatchingCardsState()
data class Ready(
    val level: Level,
    val board: Board,
    val cardsFlipped: Int = 0,
    val time: Long,
    val remainingPairs: Int,
): MatchingCardsState()
object Win: MatchingCardsState()
object Lose: MatchingCardsState()

val Ready.movements: Int
    get() = cardsFlipped / 2
