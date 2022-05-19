package com.rumosoft.feature_memorygame.domain.usecase

import com.rumosoft.feature_memorygame.domain.entity.BoardInfo
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
internal class GetBoardInfoUseCaseTest {
    companion object {
        @JvmStatic
        fun boardInfoGenerator() = listOf(
            Arguments.of(
                Level.Easy, Orientation.Portrait, BoardInfo(
                    cards = 16,
                    columns = 4
                )
            ), Arguments.of(
                Level.Easy, Orientation.Landscape, BoardInfo(
                    cards = 16,
                    columns = 4
                )
            ), Arguments.of(
                Level.Medium, Orientation.Portrait, BoardInfo(
                    cards = 24,
                    columns = 4
                )
            ), Arguments.of(
                Level.Medium, Orientation.Landscape, BoardInfo(
                    cards = 24,
                    columns = 6
                )
            ), Arguments.of(
                Level.Difficult, Orientation.Portrait, BoardInfo(
                    cards = 30,
                    columns = 5
                )
            ), Arguments.of(
                Level.Difficult, Orientation.Landscape, BoardInfo(
                    cards = 30,
                    columns = 6
                )
            )
        )
    }

    @ParameterizedTest(name = "Board state should be {2} if level {0} and orientation {1} ")
    @MethodSource("boardInfoGenerator")
    fun `GetInfoUseCase should return BoardInfo`(
        level: Level,
        orientation: Orientation,
        boardInfo: BoardInfo
    ) = test {
        runTest {
            val result = `when getBoardInfoUseCase is invoked`(level, orientation)

            `then the returned BoardInfo is as expected`(boardInfo, result)
        }
    }

    private fun TestScope.`when getBoardInfoUseCase is invoked`(
        level: Level,
        orientation: Orientation
    ) = getBoardInfoUseCase(level, orientation)

    private fun `then the returned BoardInfo is as expected`(
        boardInfo: BoardInfo,
        result: BoardInfo
    ) {
        assertEquals(boardInfo, result)
    }

    private fun test(block: TestScope.() -> Unit) {
        TestScope().block()
    }

    private class TestScope(
        val getBoardInfoUseCase: GetBoardInfoUseCase = GetBoardInfoUseCase()
    )
}