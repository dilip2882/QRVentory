package com.dilip.qrventory.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dilip.qrventory.navigation.graphs.devicesNavGraph
import com.dilip.qrventory.navigation.graphs.settingsNavGraph
import com.dilip.qrventory.presentation.devices.DevicesScreen
import com.dilip.qrventory.presentation.home.HomeScreen
import com.dilip.qrventory.presentation.settings.SettingsScreen

@Composable
fun MainNavGraph(
    rootNavController: NavHostController,
    homeNavController: NavHostController,
    innerPadding: PaddingValues,
) {
    NavHost(
        navController = homeNavController,
        route = Graph.MainScreenGraph,
        startDestination = MainRouteScreen.HomeScreen.route,
        modifier = Modifier.padding(innerPadding),
        enterTransition = { fadeIn() },
        exitTransition = { fadeOut() },
        popEnterTransition = { fadeIn() },
        popExitTransition = { fadeOut() },
    ) {
        composable(route = MainRouteScreen.HomeScreen.route) {
            HomeScreen(rootNavController)
        }
        composable(route = MainRouteScreen.DevicesScreen.route) {
            DevicesScreen(rootNavController)
        }
        composable(route = MainRouteScreen.SettingsScreen.route) {
            SettingsScreen(rootNavController)
        }
        settingsNavGraph(rootNavController)
        devicesNavGraph(rootNavController)
    }
}
