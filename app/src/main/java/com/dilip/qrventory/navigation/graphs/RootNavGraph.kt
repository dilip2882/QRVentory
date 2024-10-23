package com.dilip.qrventory.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dilip.qrventory.navigation.Graph
import com.dilip.qrventory.presentation.MainScreen
import com.dilip.qrventory.presentation.authentication.AuthScreen

@Composable
fun RootNavGraph(startDestination: String) {
    val rootNavController = rememberNavController()

    NavHost(
        navController = rootNavController,
        startDestination = startDestination,
    ) {
        composable(route = Graph.AuthenticationGraph) {
            AuthScreen(onLogin = { phoneNumber ->
                rootNavController.navigate(Graph.MainScreenGraph)
            })
        }
        composable(route = Graph.MainScreenGraph) {
            MainScreen(rootNavController = rootNavController)
        }
        settingsNavGraph(rootNavController)
        devicesNavGraph(rootNavController)
    }
}
