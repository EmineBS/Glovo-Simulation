package com.example.glovosimulation.ui.particularScreens.Profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.glovosimulation.ui.theme.GlovoSimulationTheme

@Composable
fun ProfileBody(NavigateBack:() -> Unit) {
    val initialDraggingHeight = 145.dp
    val maxDraggingHeight = 60.dp

    var offset by remember { mutableStateOf(initialDraggingHeight) }
    var isDragging by remember { mutableStateOf(false) }
    var shouldSnapToTop = false


    Column(modifier = Modifier.fillMaxSize()
        .padding(16.dp)) {
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()) {
            Image(imageVector = Icons.Default.Close, contentDescription = "", Modifier.size(30.dp).clickable { NavigateBack() })
            Box(modifier = Modifier
                .size(60.dp, 30.dp)
                .background(color = Color(0xFF309E92), shape = RoundedCornerShape(20.dp))
                //.border(2.dp, Color.Transparent, shape = RoundedCornerShape(16.dp))
                .padding(4.dp),
                contentAlignment = Alignment.Center){
                Text("Help", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 14.sp))
            }
        }
        Text("Hello, Amine!",
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 32.sp),
            modifier = Modifier.padding(top = 36.dp).padding(start = 4.dp))
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .offset(y = offset)
            .pointerInput(Unit){
                detectVerticalDragGestures(
                    onDragStart = { isDragging = true },
                    onDragEnd = {
                        isDragging = false
                        offset = if (shouldSnapToTop) maxDraggingHeight else initialDraggingHeight
                    },
                    onDragCancel = {
                        isDragging = false
                        offset = if (shouldSnapToTop) maxDraggingHeight else initialDraggingHeight
                    }
                ) { _, dragAmount -> // ----- Movement Speed
                    val newOffset = (offset + dragAmount.dp)
                    shouldSnapToTop = offset>newOffset
                    offset = newOffset.coerceIn(maxDraggingHeight, initialDraggingHeight)
                }
            }
            .background(Color.White, shape = RoundedCornerShape(16.dp))
            .border(8.dp, Color.Transparent, shape = RoundedCornerShape(16.dp))
            .padding(20.dp)){
        Column(verticalArrangement = Arrangement.spacedBy(28.dp),
            modifier = Modifier.fillMaxSize())   {
            Text("Profile", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 32.sp))
            Row(horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()) {
                Row(horizontalArrangement = Arrangement.spacedBy(20.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    Image(imageVector = Icons.Default.ShoppingCart, contentDescription = "")
                    Text("Orders", style = TextStyle(fontSize = 20.sp))
                }
                Image(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "")
            }
            Row(horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()) {
                Row(horizontalArrangement = Arrangement.spacedBy(20.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    Image(imageVector = Icons.Default.Person, contentDescription = "")
                    Text("Account", style = TextStyle(fontSize = 20.sp))
                }
                Image(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "")
            }
            Spacer(modifier = Modifier.height(1.dp).fillMaxWidth().background(Color.Gray))
            Row(horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()) {
                Row(horizontalArrangement = Arrangement.spacedBy(20.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    Image(imageVector = Icons.Default.Info, contentDescription = "")
                    Text("Promo codes", style = TextStyle(fontSize = 20.sp))
                }
                Image(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "")
            }
            Row(horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()) {
                Row(horizontalArrangement = Arrangement.spacedBy(20.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    Image(imageVector = Icons.Default.MailOutline, contentDescription = "")
                    Text("Language", style = TextStyle(fontSize = 20.sp))
                }
                Image(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "")
            }
            Row(horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()) {
                Row(horizontalArrangement = Arrangement.spacedBy(20.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    Image(imageVector = Icons.Default.Warning, contentDescription = "")
                    Text("FAQ", style = TextStyle(fontSize = 20.sp))
                }
                Image(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "")
            }
            Row(horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()) {
                Row(horizontalArrangement = Arrangement.spacedBy(20.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    Image(imageVector = Icons.Default.Notifications, contentDescription = "")
                    Text("Notifications", style = TextStyle(fontSize = 20.sp))
                }
                Image(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "")
            }
            Row(horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()) {
                Row(horizontalArrangement = Arrangement.spacedBy(20.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    Image(imageVector = Icons.Default.Delete, contentDescription = "")
                    Text("Delete my account and data", style = TextStyle(fontSize = 20.sp))
                }
                Image(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "")
            }
            Row(horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()) {
                Row(horizontalArrangement = Arrangement.spacedBy(20.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    Image(imageVector = Icons.AutoMirrored.Filled.ExitToApp, contentDescription = "")
                    Text("Log Out", style = TextStyle(fontSize = 20.sp))
                }
                Image(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "")
            }
            Spacer(modifier = Modifier.height(0.dp).fillMaxWidth().background(Color.Gray))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    GlovoSimulationTheme {
        ProfileBody(NavigateBack = {})
    }
}