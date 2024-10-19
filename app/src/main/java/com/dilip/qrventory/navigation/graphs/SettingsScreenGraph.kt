package com.dilip.qrventory.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.dilip.qrventory.navigation.Graph
import com.dilip.qrventory.navigation.MainRouteScreen
import com.dilip.qrventory.navigation.SettingsRouteScreen
import com.dilip.qrventory.presentation.about.AboutScreen
import com.dilip.qrventory.presentation.settings.device_assignee.DeviceAssigneeScreen
import com.dilip.qrventory.presentation.settings.device_location.DeviceLocationScreen
import com.dilip.qrventory.presentation.settings.device_type.DeviceTypeScreen

fun NavGraphBuilder.settingsNavGraph(rootNavController: NavHostController) {
    navigation(
        route = Graph.SettingsGraph,
        startDestination = MainRouteScreen.SettingsScreen.route,
    ) {
        composable(route = SettingsRouteScreen.AboutScreen.route) {
            AboutScreen(rootNavController)
        }
        composable(route = SettingsRouteScreen.DeviceAssignee.route) {
            DeviceAssigneeScreen(rootNavController)
        }
        composable(route = SettingsRouteScreen.DeviceLocation.route) {
            DeviceLocationScreen(rootNavController)
        }
        composable(route = SettingsRouteScreen.DeviceType.route) {
            DeviceTypeScreen(rootNavController)
        }
    }
}
