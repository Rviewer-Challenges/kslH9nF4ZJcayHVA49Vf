package com.rumosoft.feature_memorygame.domain.usecase

import com.rumosoft.feature_memorygame.domain.entity.Board
import com.rumosoft.feature_memorygame.domain.entity.BoardFactory
import com.rumosoft.feature_memorygame.domain.entity.Level
import com.rumosoft.feature_memorygame.domain.entity.Level.Difficult
import com.rumosoft.feature_memorygame.domain.entity.Level.Easy
import com.rumosoft.feature_memorygame.domain.entity.Level.Medium
import com.rumosoft.feature_memorygame.domain.entity.Orientation
import com.rumosoft.feature_memorygame.domain.entity.Orientation.Portrait
import javax.inject.Inject

class GetBoardUseCase @Inject constructor(
    private val boardFactory: BoardFactory,
    private val getCardsUseCase: GetCardsUseCase,
) {
    operator fun invoke(level: Level, orientation: Orientation): Board {
        return when (level) {
            Easy -> boardFactory.getBoard(
                cards = getCardsUseCase(numCards = 16),
                numColumns = 4
            )
            Medium -> boardFactory.getBoard(
                cards = getCardsUseCase(numCards = 24),
                numColumns = if (orientation == Portrait) 4 else 6
            )
            Difficult -> boardFactory.getBoard(
                cards = getCardsUseCase(30),
                numColumns = if (orientation == Portrait) 5 else 6
            )
        }
    }
}
