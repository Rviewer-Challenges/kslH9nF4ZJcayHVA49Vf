package com.rumosoft.feature_memorygame.presentation.screen

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.rumosoft.feature_memorygame.R
import org.junit.Rule
import org.junit.Test

internal class LevelSelectionScreenKtTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun levelSelectionScreen_showsScreenElements() {
        lateinit var selectLevelTitle: String
        lateinit var levelEasyButtonTitle: String
        lateinit var levelMediumButtonTitle: String
        lateinit var levelDifficultButtonTitle: String

        composeTestRule.setContent {
            selectLevelTitle = stringResource(id = R.string.select_level_title)
            levelEasyButtonTitle = stringResource(id = R.string.level_easy)
            levelMediumButtonTitle = stringResource(id = R.string.level_medium)
            levelDifficultButtonTitle = stringResource(id = R.string.level_difficult)

            LevelSelectionScreen()
        }

        composeTestRule.onNodeWithText(selectLevelTitle).assertIsDisplayed()
        composeTestRule.onNodeWithText(levelEasyButtonTitle).assertIsDisplayed()
        composeTestRule.onNodeWithText(levelMediumButtonTitle).assertIsDisplayed()
        composeTestRule.onNodeWithText(levelDifficultButtonTitle).assertIsDisplayed()
    }

}