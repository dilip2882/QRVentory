package com.dilip.qrventory.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.dilip.qrventory.navigation.DevicesRouteScreen
import com.dilip.qrventory.navigation.Graph
import com.dilip.qrventory.navigation.MainRouteScreen
import com.dilip.qrventory.presentation.devices.DevicesScreen
import com.dilip.qrventory.presentation.devices.device_list.AddDevice

fun NavGraphBuilder.devicesNavGraph(rootNavController: NavHostController) {
    navigation(
        route = Graph.DevicesGraph,
        startDestination = MainRouteScreen.DevicesScreen.route
    ) {
        composable(route = MainRouteScreen.DevicesScreen.route) {
            DevicesScreen(rootNavController)
        }
        composable(route = DevicesRouteScreen.AddDevice.route) {
            AddDevice(navController = rootNavController)
        }
        composable(route = DevicesRouteScreen.EditDevice.route) { backStackEntry ->
            val deviceId = backStackEntry.arguments?.getString("deviceId")
            AddDevice(navController = rootNavController, deviceId = deviceId)
        }
    }
}