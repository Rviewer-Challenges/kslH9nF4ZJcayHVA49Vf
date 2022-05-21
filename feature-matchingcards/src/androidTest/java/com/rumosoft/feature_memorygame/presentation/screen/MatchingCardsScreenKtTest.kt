package com.rumosoft.feature_memorygame.presentation.screen

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import com.rumosoft.feature_memorygame.R
import com.rumosoft.feature_memorygame.domain.entity.Board
import com.rumosoft.feature_memorygame.domain.entity.Level
import com.rumosoft.feature_memorygame.presentation.viewmodel.state.Loading
import com.rumosoft.feature_memorygame.presentation.viewmodel.state.Ready
import org.junit.Rule
import org.junit.Test

internal class MatchingCardsScreenKtTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun matchingCardsScreenScreen_withLoadingState_loadingIsVisible() {
        lateinit var loadingDescription: String

        composeTestRule.setContent {
            loadingDescription = stringResource(id = R.string.loading)

            MatchingCardsScreen(uiState = Loading)
        }

        composeTestRule.onNodeWithContentDescription(loadingDescription).assertIsDisplayed()
    }

    @Test
    fun matchingCardsScreenScreen_withReadyState_cardsIsVisible() {
        lateinit var cardsDescription: String

        composeTestRule.setContent {
            cardsDescription = stringResource(id = R.string.cards)

            MatchingCardsScreen(
                uiState = Ready(
                    level = Level.Easy,
                    board = Board(
                        numCards = 16,
                        columns = 4,
                    ),
                )
            )
        }

        composeTestRule.onNodeWithContentDescription(cardsDescription).assertIsDisplayed()
    }

}