package com.dilip.qrventory.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dilip.common.R
import com.dilip.presentation.components.NavBar
import com.dilip.presentation.components.NavigationItem
import com.dilip.qrventory.navigation.MainNavGraph
import com.dilip.qrventory.navigation.MainRouteScreen
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow

@OptIn(ExperimentalAnimationGraphicsApi::class)
@Composable
fun MainScreen(
    rootNavController: NavHostController,
    homeNavController: NavHostController = rememberNavController(),
) {
    val showBottomNavEvent = Channel<Boolean>()

    val homeAnimatedIcon = AnimatedImageVector.animatedVectorResource(R.drawable.ic_home)
    val devicesAnimatedIcon = AnimatedImageVector.animatedVectorResource(R.drawable.ic_devices)
    val settingsAnimatedIcon = AnimatedImageVector.animatedVectorResource(R.drawable.ic_settings)
    val navigationItem = remember {
        listOf(
            NavigationItem(homeAnimatedIcon, text = "Home"),
            NavigationItem(devicesAnimatedIcon, text = "Devices"),
            NavigationItem(settingsAnimatedIcon, text = "Settings"),
        )
    }

    val backStackState = homeNavController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableIntStateOf(0)
    }

    selectedItem = when (backStackState?.destination?.route) {
        MainRouteScreen.HomeScreen.route -> 0
        MainRouteScreen.DevicesScreen.route -> 1
        MainRouteScreen.SettingsScreen.route -> 2
        else -> 0
    }

    // Hide the bottom navigation when the user is in the details screen
    val isBarVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route == MainRouteScreen.HomeScreen.route ||
            backStackState?.destination?.route == MainRouteScreen.DevicesScreen.route ||
            backStackState?.destination?.route == MainRouteScreen.SettingsScreen.route
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (isBarVisible) {
                val bottomNavVisible by produceState(initialValue = true) {
                    showBottomNavEvent.receiveAsFlow().collectLatest { value = it }
                }
                AnimatedVisibility(
                    visible = bottomNavVisible,
                    enter = expandVertically(),
                    exit = shrinkVertically(),
                ) {
                    NavBar(
                        items = navigationItem,
                        selectedItem = selectedItem,
                        onItemClick = { index ->
                            when (index) {
                                0 -> navigateToTab(
                                    navController = homeNavController,
                                    route = MainRouteScreen.HomeScreen.route,
                                )

                                1 -> navigateToTab(
                                    navController = homeNavController,
                                    route = MainRouteScreen.DevicesScreen.route,
                                )

                                2 -> navigateToTab(
                                    navController = homeNavController,
                                    route = MainRouteScreen.SettingsScreen.route,
                                )
                            }
                        },
                    )
                }
            }
        },
    ) { innerPadding ->
        MainNavGraph(
            rootNavController = rootNavController,
            homeNavController = homeNavController,
            innerPadding = innerPadding,
        )
    }
}

private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { screenRoute ->
            popUpTo(screenRoute) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}
