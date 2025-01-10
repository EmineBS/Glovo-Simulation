package com.example.glovosimulation.ui.entryPoint

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.glovosimulation.ui.entryPoint.components.EntryPointBody
import com.example.glovosimulation.ui.particularScreens.Profile.ProfileActivity
import com.example.glovosimulation.ui.theme.GlovoSimulationTheme

@Composable
fun EntryPointActivity(navigateToProfile:() -> Unit) {
    Scaffold(
        //topBar = { EntryPointTopBar() } ,
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .background(color = Color(0xFFFFB84D))
                    .fillMaxSize()) {
                        EntryPointBody(NavigateToProfile = navigateToProfile)
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    GlovoSimulationTheme {
        EntryPointActivity(navigateToProfile = {})
    }
}