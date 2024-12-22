package com.dilip.qrventory.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.dilip.qrventory.navigation.AuthRouteScreen
import com.dilip.qrventory.navigation.Graph
import com.dilip.qrventory.presentation.auth.AuthScreen
import com.dilip.qrventory.presentation.profile.ProfileScreen

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.AuthGraph,
        startDestination = AuthRouteScreen.AuthScreen.route,
    ) {
        composable(route = AuthRouteScreen.AuthScreen.route) {
            AuthScreen(
                navigateToProfileScreen = {
                    navController.navigate(AuthRouteScreen.ProfileScreen.route)
                },
            )
        }
        composable(route = AuthRouteScreen.ProfileScreen.route) {
            ProfileScreen(
                navigateToAuthScreen = {
                    navController.popBackStack()
                    navController.navigate(AuthRouteScreen.ProfileScreen.route)
                },
            )
        }
    }
}
