package com.rumosoft.feature_memorygame.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.rumosoft.feature_memorygame.presentation.navigation.destination.LevelSelectionDestination
import com.rumosoft.feature_memorygame.presentation.navigation.destination.LoseDestination
import com.rumosoft.feature_memorygame.presentation.navigation.destination.MatchingCardsDestination
import com.rumosoft.feature_memorygame.presentation.navigation.destination.WinDestination
import com.rumosoft.feature_memorygame.presentation.screen.LevelSelectionRoute
import com.rumosoft.feature_memorygame.presentation.screen.LoseScreen
import com.rumosoft.feature_memorygame.presentation.screen.MatchingCardsRoute
import com.rumosoft.feature_memorygame.presentation.screen.WinScreen

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
        MatchingCardsRoute(
            onBackPressed = {
                navController.popBackStack()
            },
            onWin = {
                navController.navigate(WinDestination.route) {
                    navController.currentDestination?.route?.let {
                        popUpTo(it) {
                            inclusive = true
                        }
                    }
                }
            },
            onLose = {
                navController.navigate(LoseDestination.route) {
                    navController.currentDestination?.route?.let {
                        popUpTo(it) {
                            inclusive = true
                        }
                    }
                }
            },
        )
    }
    composable(
        route = WinDestination.route
    ) {
        WinScreen(
            onRestart = {
                navController.navigate(LevelSelectionDestination.route) {
                    popUpTo(LevelSelectionDestination.route) {
                        inclusive = true
                    }
                }
            }
        )
    }
    composable(
        route = LoseDestination.route
    ) {
        LoseScreen(
            onRestart = {
                navController.navigate(LevelSelectionDestination.route) {
                    popUpTo(LevelSelectionDestination.route) {
                        inclusive = true
                    }
                }
            }
        )
    }
}