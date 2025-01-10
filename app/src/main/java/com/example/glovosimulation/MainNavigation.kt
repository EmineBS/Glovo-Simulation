package com.example.glovosimulation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.glovosimulation.navigation.NavigationActions
import com.example.glovosimulation.navigation.NavigationDestination
import com.example.glovosimulation.ui.entryPoint.EntryPointActivity
import com.example.glovosimulation.ui.particularScreens.Profile.ProfileActivity
import com.example.glovosimulation.ui.theme.GlovoSimulationTheme

@Composable
fun MainNavigation(
    navHostController: NavHostController,
    navigationActions: NavigationActions,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navHostController,
        startDestination = NavigationDestination.EntryPoint.route,
        modifier = modifier
    ) {
        composable(NavigationDestination.EntryPoint.route) {
            GlovoSimulationTheme {
                EntryPointActivity(){
                    navigationActions.navigateToProfile()
                }
            }
        }

        composable(NavigationDestination.Profile.route) {
            GlovoSimulationTheme {
                ProfileActivity(){
                    navigationActions.navigateToEntryPoint()
                }
            }
        }
        // Other navigation destinations will go here
    }
}