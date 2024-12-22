package com.dilip.qrventory.navigation

object Graph {
    const val RootGraph = "rootGraph"
    const val AuthGraph = "authGraph"
    const val MainScreenGraph = "mainScreenGraph"
    const val SettingsGraph = "settingsGraph"
    const val DevicesGraph = "devicesGraph"
}

sealed class AuthRouteScreen(var route: String) {
    data object AuthScreen : AuthRouteScreen("auth")
    data object ProfileScreen : AuthRouteScreen("profile")
}

sealed class MainRouteScreen(var route: String) {
    data object HomeScreen : MainRouteScreen("home")
    data object SettingsScreen : SettingsRouteScreen("settings")
    data object DevicesScreen : DevicesRouteScreen("devices")
}

sealed class SettingsRouteScreen(var route: String) {
    data object AboutScreen : SettingsRouteScreen("about")
    data object DeviceAssignee : SettingsRouteScreen("deviceAssignee")
    data object DeviceLocation : SettingsRouteScreen("deviceLocation")
    data object DeviceType : SettingsRouteScreen("deviceType")
}

sealed class DevicesRouteScreen(var route: String) {
    data object AddDevice : DevicesRouteScreen("add_device")
    data object EditDevice : DevicesRouteScreen("edit_device/{deviceId}")
}
