package com.rumosoft.feature_memorygame.domain.usecase

import com.rumosoft.feature_memorygame.domain.entity.BoardInfo
import com.rumosoft.feature_memorygame.domain.entity.Level
import com.rumosoft.feature_memorygame.domain.entity.Level.Difficult
import com.rumosoft.feature_memorygame.domain.entity.Level.Easy
import com.rumosoft.feature_memorygame.domain.entity.Level.Medium
import com.rumosoft.feature_memorygame.domain.entity.Orientation
import com.rumosoft.feature_memorygame.domain.entity.Orientation.Portrait
import javax.inject.Inject

class GetBoardInfoUseCase @Inject constructor() {
    operator fun invoke(level: Level, orientation: Orientation): BoardInfo {
        return when (level) {
            Easy -> BoardInfo(
                cards = 16,
                columns = 4
            )
            Medium -> BoardInfo(
                cards = 24,
                columns = if (orientation == Portrait) 4 else 6
            )
            Difficult -> BoardInfo(
                cards = 30,
                columns = if (orientation == Portrait) 5 else 6
            )
            else -> throw UnsupportedOperationException("No level selected")
        }
    }
}
