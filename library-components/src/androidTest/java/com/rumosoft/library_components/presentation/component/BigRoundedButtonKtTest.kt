package com.rumosoft.library_components.presentation.component

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.Rule
import org.junit.Test

internal class BigRoundedButtonKtTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun whenOnBigRoundedButtonClicked_onClickLambdaGetsCalled() {
        val buttonText = "Whatever"
        val onClick: () -> Unit = mockk()
        justRun { onClick() }

        composeTestRule.setContent {
            BigRoundedButton(text = buttonText, onClick = onClick)
        }

        composeTestRule.onNodeWithText(buttonText).performClick()
        verify { onClick() }
    }
}