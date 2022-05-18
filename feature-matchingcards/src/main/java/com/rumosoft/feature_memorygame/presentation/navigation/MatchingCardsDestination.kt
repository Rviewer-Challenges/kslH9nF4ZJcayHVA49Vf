package com.rumosoft.feature_memorygame.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.rumosoft.feature_memorygame.presentation.navigation.destination.LevelSelectionDestination
import com.rumosoft.feature_memorygame.presentation.navigation.destination.MatchingCardsDestination
import com.rumosoft.feature_memorygame.presentation.screen.LevelSelectionRoute
import com.rumosoft.feature_memorygame.presentation.screen.MatchingCardsRoute

fun NavGraphBuilder.matchingCardsGraph(navController: NavHostController) {
    composable(route = LevelSelectionDestination.route) {
        LevelSelectionRoute(
            navigateToMatchingCards = { level ->
                navController.navigate("${MatchingCardsDestination.route}/${level.value}")
            }
        )
    }
    composable(
        route = "${MatchingCardsDestination.route}/{${MatchingCardsDestination.levelArg}}",
        arguments = listOf(
            navArgument(MatchingCardsDestination.levelArg) {
                type = NavType.IntType
            }
        )
    ) {
        MatchingCardsRoute()
    }
}