package com.rumosoft.memorygame.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rumosoft.memorygame.presentation.screen.LevelSelectionScreen

private const val LEVEL_SELECTION = "LevelSelection"

@Composable
fun NavigationHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController, startDestination = LEVEL_SELECTION, modifier = modifier) {
        composable(LEVEL_SELECTION) {
            LevelSelectionScreen()
        }
    }
}
