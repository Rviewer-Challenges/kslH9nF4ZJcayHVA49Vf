package com.rumosoft.feature_memorygame.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.rumosoft.feature_memorygame.presentation.navigation.destination.LevelSelectionDestination
import com.rumosoft.feature_memorygame.presentation.navigation.destination.MatchingCardsDestination
import com.rumosoft.feature_memorygame.presentation.screen.LevelSelectionRoute
import com.rumosoft.feature_memorygame.presentation.screen.MatchingCardsRoute

fun NavGraphBuilder.matchingCardsGraph(navController: NavHostController) {
    composable(route = LevelSelectionDestination.route) {
        LevelSelectionRoute(
            onLevelSelected = {
                navController.navigate(MatchingCardsDestination.route)
            }
        )
    }
    composable(route = MatchingCardsDestination.route) {
        MatchingCardsRoute()
    }
}