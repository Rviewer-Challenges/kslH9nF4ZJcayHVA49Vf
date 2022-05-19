package com.rumosoft.feature_memorygame.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.rumosoft.feature_memorygame.domain.entity.BoardInfo
import com.rumosoft.feature_memorygame.domain.entity.Level
import com.rumosoft.feature_memorygame.domain.entity.Orientation
import com.rumosoft.feature_memorygame.domain.usecase.GetBoardInfoUseCase
import com.rumosoft.feature_memorygame.presentation.navigation.destination.MatchingCardsDestination
import com.rumosoft.feature_memorygame.presentation.viewmodel.state.Loading
import com.rumosoft.feature_memorygame.presentation.viewmodel.state.Ready
import com.rumosoft.library_tests.TestCoroutineExtension
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@ExtendWith(TestCoroutineExtension::class)
internal class MatchingCardsViewModelTest {

    @Test
    fun `getBoardInfoUseCase is invoked when retrieveBoardInfo gets invoked on Easy Portrait`() =
        test {
            `given selectel level`(easy)
            `given getBoardInfoUseCase returns BoardInfo`(easy, portrait)

            `when retrieveBoardInfo is invoked`(portrait)

            `then getBoardInfoUseCase is invoked`(easy, portrait)
        }

    @Test
    fun `getBoardInfoUseCase is invoked when retrieveBoardInfo gets invoked on Difficult Landscape`() =
        test {
            `given selectel level`(difficult)
            `given getBoardInfoUseCase returns BoardInfo`(difficult, landscape)

            `when retrieveBoardInfo is invoked`(landscape)

            `then getBoardInfoUseCase is invoked`(difficult, landscape)
        }

    @Test
    fun `uiState is Loading when the ViewModel is created`() = test {
        `when selectel level`(easy)

        `then uiState is Loading`()
    }

    @Test
    fun `uiState is Ready after retrieveBoarInfo is invoked`() = test {
        `given selectel level`(easy)
        `given getBoardInfoUseCase returns BoardInfo`(easy, landscape)

        `when retrieveBoardInfo is invoked`(landscape)

        `then uiState is Ready`()
    }

    @Test
    fun `counter is set to a minute after retrieveBoarInfo is done`() = test {
        runTest {
            `given selectel level`(medium)
            `given getBoardInfoUseCase returns BoardInfo`(medium, landscape)

            `when retrieveBoardInfo is invoked`(landscape)

            `then the counter is set to secs`(60)
        }
    }

    @Test
    fun `counter is set to a 55 seconds after retrieveBoarInfo is done and 5 seconds have passed`() = test {
        runTest {
            `given selectel level`(medium)
            `given getBoardInfoUseCase returns BoardInfo`(medium, landscape)
            `given retrieveBoardInfo is invoked`(landscape)

            `when seconds have passed`(5)

            `then the counter is set to secs`(55)
        }
    }

    private fun TestScope.`given getBoardInfoUseCase returns BoardInfo`(
        level: Level,
        orientation: Orientation
    ) {
        every { getBoardInfoUseCase(level, orientation) } returns
                BoardInfo(cards = 16, columns = 4)
    }

    private fun TestScope.`given selectel level`(level: Level) {
        initViewModel(level)
    }

    private fun TestScope.`given retrieveBoardInfo is invoked`(orientation: Orientation) {
        `when retrieveBoardInfo is invoked`(orientation)
    }

    private fun TestScope.`when retrieveBoardInfo is invoked`(orientation: Orientation) {
        viewModel.retrieveBoardInfo(orientation)
    }

    private fun TestScope.`when selectel level`(level: Level) {
        `given selectel level`(level)
    }

    private fun kotlinx.coroutines.test.TestScope.`when seconds have passed`(seconds: Int) {
        advanceTimeBy(((seconds * 1000) + 1).toLong())
    }

    private fun TestScope.`then getBoardInfoUseCase is invoked`(
        level: Level,
        orientation: Orientation
    ) {
        verify { getBoardInfoUseCase(level, orientation) }
    }

    private fun TestScope.`then uiState is Loading`() {
        assertTrue(viewModel.uiState.value is Loading)
    }

    private fun TestScope.`then uiState is Ready`() {
        assertTrue(viewModel.uiState.value is Ready)
    }

    private fun TestScope.`then the counter is set to secs`(seconds: Int) {
        `then uiState is Ready`()
        assertEquals(seconds, (viewModel.uiState.value as Ready).time)
    }

    private fun test(block: TestScope.() -> Unit) {
        TestScope().block()
    }

    private class TestScope(
        val easy: Level = Level.Easy,
        val medium: Level = Level.Medium,
        val difficult: Level = Level.Difficult,
        val portrait: Orientation = Orientation.Portrait,
        val landscape: Orientation = Orientation.Landscape,
        val savedStateHandle: SavedStateHandle = SavedStateHandle(),
        val getBoardInfoUseCase: GetBoardInfoUseCase = mockk(),
    ) {
        lateinit var viewModel: MatchingCardsViewModel

        fun initViewModel(level: Level) {
            savedStateHandle[MatchingCardsDestination.levelArg] = level.value
            viewModel = MatchingCardsViewModel(
                savedStateHandle,
                getBoardInfoUseCase,
            )
        }
    }
}