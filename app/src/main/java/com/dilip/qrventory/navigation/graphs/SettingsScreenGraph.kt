package com.dilip.qrventory.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.dilip.qrventory.navigation.Graph
import com.dilip.qrventory.navigation.MainRouteScreen
import com.dilip.qrventory.navigation.SettingsRouteScreen
import com.dilip.qrventory.presentation.about.AboutScreen

fun NavGraphBuilder.settingsNavGraph(rootNavController: NavHostController) {
    navigation(
        route = Graph.SettingsGraph,
        startDestination = MainRouteScreen.SettingsScreen.route
    ) {
        composable(route = SettingsRouteScreen.AboutScreen.route) {
            AboutScreen(rootNavController)
        }
    }
}
