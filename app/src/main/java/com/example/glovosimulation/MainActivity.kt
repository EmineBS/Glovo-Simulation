package com.example.glovosimulation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.glovosimulation.navigation.NavigationActions
import com.example.glovosimulation.ui.entryPoint.EntryPointActivity
import com.example.glovosimulation.ui.entryPoint.components.bottomScrollable
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
                MainNavigation(navHostController  = navController, navigationActions = navigationActions)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GlovoSimulationTheme {
        EntryPointActivity(navigateToProfile = {})
    }
}