package com.rumosoft.feature_memorygame.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.rumosoft.feature_memorygame.domain.entity.Board
import com.rumosoft.feature_memorygame.domain.entity.GameCard
import com.rumosoft.feature_memorygame.domain.entity.Level
import com.rumosoft.feature_memorygame.domain.entity.Orientation
import com.rumosoft.feature_memorygame.domain.usecase.GetBoardUseCase
import com.rumosoft.feature_memorygame.domain.usecase.GetCountDownUseCase
import com.rumosoft.feature_memorygame.presentation.component.CardFace
import com.rumosoft.feature_memorygame.presentation.navigation.destination.MatchingCardsDestination
import com.rumosoft.feature_memorygame.presentation.viewmodel.state.Loading
import com.rumosoft.feature_memorygame.presentation.viewmodel.state.Ready
import com.rumosoft.library_tests.TestCoroutineExtension
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
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
            `given selected level`(easy)
            `given getBoardInfoUseCase returns Board`(easy, portrait)

            `when retrieveBoard is invoked`(portrait)

            `then getBoardInfoUseCase is invoked`(easy, portrait)
        }

    @Test
    fun `getBoardInfoUseCase is invoked when retrieveBoardInfo gets invoked on Difficult Landscape`() =
        test {
            `given selected level`(difficult)
            `given getBoardInfoUseCase returns Board`(difficult, landscape)

            `when retrieveBoard is invoked`(landscape)

            `then getBoardInfoUseCase is invoked`(difficult, landscape)
        }

    @Test
    fun `uiState is Loading when the ViewModel is created`() = test {
        `when selected level`(easy)

        `then uiState is Loading`()
    }

    @Test
    fun `uiState is Ready after retrieveBoarInfo is invoked`() = test {
        `given selected level`(easy)
        `given getBoardInfoUseCase returns Board`(easy, landscape)

        `when retrieveBoard is invoked`(landscape)

        `then uiState is Ready`()
    }

    @Test
    fun `onCardSelected with the first card should flip it`() = test {
        `given selected level`(easy)
        `given getBoardInfoUseCase returns Board`(easy, portrait)
        `given retrieveBoard is invoked`(portrait)
        `given first card is reversed`()

        `when calling onCardSelected on first card`()

        `then the first card is revealed`()
    }

    private fun TestScope.`given getBoardInfoUseCase returns Board`(
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

    private fun TestScope.`given selected level`(level: Level) {
        initViewModel(level)
    }

    private fun TestScope.`given retrieveBoard is invoked`(orientation: Orientation) =
        `when retrieveBoard is invoked`(orientation)

    private fun TestScope.`given first card is reversed`() {
        assertEquals(CardFace.Back, viewModel.getFirstCard().face)
    }

    private fun TestScope.`when retrieveBoard is invoked`(orientation: Orientation) {
        viewModel.retrieveBoard(orientation)
    }

    private fun TestScope.`when calling onCardSelected on first card`() {
        val firstCard = viewModel.getFirstCard()
        viewModel.onCardSelected(firstCard)
    }

    private fun TestScope.`when selected level`(level: Level) {
        `given selected level`(level)
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

    private fun TestScope.`then the first card is revealed`() {
        assertEquals(CardFace.Front, viewModel.getFirstCard().face)
    }

    private fun MatchingCardsViewModel.getFirstCard(): GameCard =
        (uiState.value as Ready).board.cards.first()

    private fun test(block: TestScope.() -> Unit) {
        TestScope().block()
    }

    private class TestScope(
        val easy: Level = Level.Easy,
        val difficult: Level = Level.Difficult,
        val portrait: Orientation = Orientation.Portrait,
        val landscape: Orientation = Orientation.Landscape,
        val savedStateHandle: SavedStateHandle = SavedStateHandle(),
        val getBoardUseCase: GetBoardUseCase = mockk(),
        val getCountDownUseCase: GetCountDownUseCase = mockk<GetCountDownUseCase>().also {
            every { it.invoke(any()) } returns flowOf(1L)
        },
    ) {
        lateinit var viewModel: MatchingCardsViewModel

        fun initViewModel(level: Level) {
            savedStateHandle[MatchingCardsDestination.levelArg] = level.value
            viewModel = MatchingCardsViewModel(
                savedStateHandle,
                getBoardUseCase,
                getCountDownUseCase
            )
        }
    }
}