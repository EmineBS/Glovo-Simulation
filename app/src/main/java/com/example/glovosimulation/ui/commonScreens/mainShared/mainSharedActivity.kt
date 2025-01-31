package com.example.glovosimulation.ui.commonScreens.mainShared

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.glovosimulation.navigation.NavigationActions
import com.example.glovosimulation.ui.commonScreens.mainShared.components.MainSharedBody
import com.example.glovosimulation.ui.theme.GlovoSimulationTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainSharedActivity(navigationActions: NavigationActions){

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        content = {
            MainSharedBody(navigationActions = navigationActions)
        })
}