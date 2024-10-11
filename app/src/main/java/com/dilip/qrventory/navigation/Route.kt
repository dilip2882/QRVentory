package com.dilip.qrventory.navigation

object Graph {
    const val RootGraph = "rootGraph"
    const val SyncGraph = "SyncGraph"
    const val MainScreenGraph = "mainScreenGraph"
    const val SettingsGraph = "settingsGraph"
    const val DevicesGraph = "devicesGraph"
}

sealed class MainRouteScreen(var route: String) {
    data object HomeScreen : MainRouteScreen("home")
    data object SettingsScreen : SettingsRouteScreen("settings")
    data object DevicesScreen : DevicesRouteScreen("devices")
}

sealed class SettingsRouteScreen(var route: String) {
    data object AboutScreen : SettingsRouteScreen("about")
}

sealed class DevicesRouteScreen(var route: String)
