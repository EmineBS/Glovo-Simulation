package com.example.glovosimulation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.rememberNavController
import com.example.glovosimulation.navigation.NavigationActions
import com.example.glovosimulation.ui.theme.GlovoSimulationTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var navigationActions: NavigationActions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            LaunchedEffect(navController) {
                navigationActions.setNavController(navController)
            }

            GlovoSimulationTheme {
                MainNavigation(navHostController = navController, navigationActions = navigationActions)
            }
        }
    }
}