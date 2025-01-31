package com.example.glovosimulation.ui.particularScreens.Profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.glovosimulation.navigation.NavigationActions
import com.example.glovosimulation.ui.particularScreens.Profile.components.ProfileBody

@Composable
fun ProfileActivity(navigationActions: NavigationActions) {
    Scaffold(
        //topBar = { EntryPointTopBar() } ,
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .background(color = Color(0xFFFFB84D))
                    .fillMaxSize()) {
                ProfileBody(navigationActions = navigationActions)
            }
        }
    )
}
