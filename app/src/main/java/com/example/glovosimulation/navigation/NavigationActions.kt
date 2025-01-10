package com.example.glovosimulation.navigation

import androidx.navigation.NavHostController
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationActions @Inject constructor() {
    private var _navController: NavHostController? = null

    fun setNavController(navController: NavHostController) {
        _navController = navController
    }

    fun navigateToEntryPoint() {
        _navController?.navigate(NavigationDestination.EntryPoint.route) {
            // Clear back stack when going to entry point
            popUpTo(0) { inclusive = true }
        }
    }

    fun navigateToProfile() {
        _navController?.navigate(NavigationDestination.Profile.route)
    }
}