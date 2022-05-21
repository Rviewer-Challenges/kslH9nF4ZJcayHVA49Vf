package com.rumosoft.feature_memorygame.domain.usecase

import com.rumosoft.feature_memorygame.domain.entity.Board
import com.rumosoft.feature_memorygame.domain.entity.BoardFactoryImpl
import com.rumosoft.feature_memorygame.domain.entity.Level
import com.rumosoft.feature_memorygame.domain.entity.Orientation
import com.rumosoft.library_tests.TestCoroutineExtension
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

@ExperimentalCoroutinesApi
@ExtendWith(TestCoroutineExtension::class)
internal class GetBoardUseCaseTest {
    companion object {
        @JvmStatic
        fun boardInfoGenerator() = listOf(
            Arguments.of(Level.Easy, Orientation.Portrait, 16, 4),
            Arguments.of(Level.Easy, Orientation.Landscape, 16, 4),
            Arguments.of(Level.Medium, Orientation.Portrait, 24, 4),
            Arguments.of(Level.Medium, Orientation.Landscape, 24, 6),
            Arguments.of(Level.Difficult, Orientation.Portrait, 30, 5),
            Arguments.of(Level.Difficult, Orientation.Landscape, 30, 6)
        )
    }

    @ParameterizedTest(name = "Board state should be {2} if level {0} and orientation {1} ")
    @MethodSource("boardInfoGenerator")
    fun `GetInfoUseCase should return BoardInfo`(
        level: Level,
        orientation: Orientation,
        numCards: Int,
        numColumns: Int
    ) = test {
        runTest {
            val result = `when getBoardInfoUseCase is invoked`(level, orientation)

            `then the returned Board is as expected`(numCards, numColumns, result)
        }
    }

    private fun TestScope.`when getBoardInfoUseCase is invoked`(
        level: Level,
        orientation: Orientation
    ) = getBoardUseCase(level, orientation)

    private fun `then the returned Board is as expected`(
        numCards: Int,
        numColumns: Int,
        board: Board
    ) {
        assertEquals(numCards, board.cards.size)
        assertEquals(numColumns, board.columns)
    }

    private fun test(block: TestScope.() -> Unit) {
        TestScope().block()
    }

    private class TestScope(
        val getBoardUseCase: GetBoardUseCase = GetBoardUseCase(BoardFactoryImpl())
    )
}