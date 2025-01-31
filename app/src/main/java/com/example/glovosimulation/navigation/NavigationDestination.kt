package com.example.glovosimulation.navigation

sealed class NavigationDestination(val route: String) {
    object EntryPoint : NavigationDestination("entrypoint")
    object Profile : NavigationDestination("profile")
    object MainShared : NavigationDestination("main_shared")
    // Other destinations...

    companion object {
        fun fromRoute(route: String): NavigationDestination {
            return when(route) {
                "entrypoint" -> EntryPoint
                "profile" -> Profile
                "main_shared" -> MainShared
                else -> EntryPoint // Default to EntryPoint if route not found
            }
        }
    }
}