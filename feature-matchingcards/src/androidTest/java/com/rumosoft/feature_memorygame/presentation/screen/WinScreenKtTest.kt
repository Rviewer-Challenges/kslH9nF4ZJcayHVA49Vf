package com.rumosoft.feature_memorygame.presentation.screen

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.rumosoft.feature_memorygame.R
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.Rule
import org.junit.Test

internal class WinScreenKtTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun winScreen_winMessage_IsVisible() {
        lateinit var winMessage: String
        composeTestRule.setContent {
            winMessage = stringResource(id = R.string.win_message)
            WinScreen()
        }

        composeTestRule.onNodeWithText(winMessage).assertIsDisplayed()
    }

    @Test
    fun winScreen_restartButton_IsVisible() {
        lateinit var restartMessage: String
        composeTestRule.setContent {
            restartMessage = stringResource(id = R.string.restart)
            WinScreen()
        }

        composeTestRule.onNodeWithText(restartMessage).assertIsDisplayed()
    }

    @Test
    fun winScreen_restartButton_invokesCallback() {
        lateinit var restartMessage: String
        val onRestart: () -> Unit = mockk()
        justRun { onRestart() }
        composeTestRule.setContent {
            restartMessage = stringResource(id = R.string.restart)
            WinScreen(onRestart = onRestart)
        }

        composeTestRule.onNodeWithText(restartMessage).performClick()

        verify { onRestart() }
    }
}