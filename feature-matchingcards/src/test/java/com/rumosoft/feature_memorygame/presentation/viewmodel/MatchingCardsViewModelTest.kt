package com.rumosoft.feature_memorygame.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.rumosoft.feature_memorygame.domain.entity.Board
import com.rumosoft.feature_memorygame.domain.entity.GameCard
import com.rumosoft.feature_memorygame.domain.entity.Level
import com.rumosoft.feature_memorygame.domain.entity.Orientation
import com.rumosoft.feature_memorygame.domain.usecase.GetBoardUseCase
import com.rumosoft.feature_memorygame.presentation.navigation.destination.MatchingCardsDestination
import com.rumosoft.feature_memorygame.presentation.viewmodel.state.Loading
import com.rumosoft.feature_memorygame.presentation.viewmodel.state.Ready
import com.rumosoft.library_tests.TestCoroutineExtension
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
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

    private fun TestScope.`given getBoardInfoUseCase returns BoardInfo`(
        level: Level,
        orientation: Orientation
    ) {
        every { getBoardUseCase(level, orientation) } returns
                Board(
                    cards = (1..16).map {
                        GameCard(id = it, characterId = it, name = "name $it", image = "image $it")
                    },
                    columns = 4
                )
    }

    private fun TestScope.`given selectel level`(level: Level) {
        initViewModel(level)
    }

    private fun TestScope.`when retrieveBoardInfo is invoked`(orientation: Orientation) {
        viewModel.retrieveBoard(orientation)
    }

    private fun TestScope.`when selectel level`(level: Level) {
        `given selectel level`(level)
    }

    private fun TestScope.`then getBoardInfoUseCase is invoked`(
        level: Level,
        orientation: Orientation
    ) {
        verify { getBoardUseCase(level, orientation) }
    }

    private fun TestScope.`then uiState is Loading`() {
        assertTrue(viewModel.uiState.value is Loading)
    }

    private fun TestScope.`then uiState is Ready`() {
        assertTrue(viewModel.uiState.value is Ready)
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
        val getBoardUseCase: GetBoardUseCase = mockk(),
    ) {
        lateinit var viewModel: MatchingCardsViewModel

        fun initViewModel(level: Level) {
            savedStateHandle[MatchingCardsDestination.levelArg] = level.value
            viewModel = MatchingCardsViewModel(
                savedStateHandle,
                getBoardUseCase,
            )
        }
    }
}