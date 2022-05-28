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

internal class LoseScreenKtTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun loseScreen_loseMessage_IsVisible() {
        lateinit var loseMessage: String
        composeTestRule.setContent {
            loseMessage = stringResource(id = R.string.lose_message)
            LoseScreen()
        }

        composeTestRule.onNodeWithText(loseMessage).assertIsDisplayed()
    }

    @Test
    fun loseScreen_restartButton_IsVisible() {
        lateinit var restartMessage: String
        composeTestRule.setContent {
            restartMessage = stringResource(id = R.string.restart)
            LoseScreen()
        }

        composeTestRule.onNodeWithText(restartMessage).assertIsDisplayed()
    }

    @Test
    fun loseScreen_restartButton_invokesCallback() {
        lateinit var restartMessage: String
        val onRestart: () -> Unit = mockk()
        justRun { onRestart() }
        composeTestRule.setContent {
            restartMessage = stringResource(id = R.string.restart)
            LoseScreen(onRestart = onRestart)
        }

        composeTestRule.onNodeWithText(restartMessage).performClick()

        verify { onRestart() }
    }
}