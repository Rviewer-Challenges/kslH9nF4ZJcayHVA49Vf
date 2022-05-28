package com.rumosoft.memorygame.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.rumosoft.feature_memorygame.presentation.navigation.destination.LevelSelectionDestination
import com.rumosoft.feature_memorygame.presentation.navigation.matchingCardsGraph

@Composable
fun NavigationHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController,
        startDestination = LevelSelectionDestination.route,
        modifier = modifier
    ) {
        matchingCardsGraph(navController)
    }
}
