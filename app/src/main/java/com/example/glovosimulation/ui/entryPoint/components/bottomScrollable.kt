package com.example.glovosimulation.ui.entryPoint.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.glovosimulation.ui.theme.GlovoSimulationTheme
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.glovosimulation.ui.theme.curvedTopShape
import kotlinx.coroutines.launch

//@Stable
@Composable
fun bottomScrollable() {
    val itemHeight = 50.dp
    val spacing = 6.dp
    val titleHeight = 57.5.dp
    val visibleItems = 15 // Controls both initial and maximum visible items
    val totalItems by remember { mutableIntStateOf(2) }

    val scope = rememberCoroutineScope()

    // Calculate heights based on visibleItems
    val expandedVisibleItems = visibleItems
    val maxExpandedHeight = (itemHeight * expandedVisibleItems) + (spacing * (expandedVisibleItems - 1)) + titleHeight
    val collapsedHeight = titleHeight + itemHeight + spacing // Title + first item
    val initialOffset = maxExpandedHeight - collapsedHeight - 25.dp

    var offset by remember { mutableStateOf(initialOffset) }
    var isDragging by remember { mutableStateOf(false) }

    val snapThreshold = (maxExpandedHeight - collapsedHeight) / 2 + 137.5.dp
    var shouldSnapToTop by remember { mutableStateOf(false) }

    // Handle drag release
    fun onDragEnd() {
        scope.launch {
            val targetOffset = if (shouldSnapToTop) snapThreshold else initialOffset
            offset = targetOffset
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()  // Make surface fill entire screen
            .offset(y = offset)
            .curvedTopShape()
            .pointerInput(Unit) {
                detectVerticalDragGestures(
                    onDragStart = { isDragging = true },
                    onDragEnd = {
                        isDragging = false
                        onDragEnd()
                    },
                    onDragCancel = {
                        isDragging = false
                        onDragEnd()
                    }
                ) { _, dragAmount -> // ----- Movement Speed
                    val newOffset = (offset + dragAmount.dp)
                    shouldSnapToTop = offset>newOffset
                    offset = newOffset.coerceIn(snapThreshold, initialOffset)
                }
            },
    ) {
        Column(
            modifier = Modifier.fillMaxSize() // Make column fill the surface
        ) {
            // Title area
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(titleHeight)
                    .padding(8.dp),
            )

            // Content area
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(spacing),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                items(totalItems) {
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(itemHeight+25.dp),
                        color = Color.White
                    ) {
                        Row(horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalAlignment = Alignment.Top,
                            modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp)) {
                            AsyncImage(
                                model = "https://sell.glovoapp.com/ug/wp-content/uploads/sites/26/2022/11/delivery_with_glovo-314x300.png",
                                contentDescription = "",
                                modifier = Modifier.size(50.dp))
                            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                Card(shape = RoundedCornerShape(4.dp),
                                    colors = CardDefaults.cardColors(Color(0xFFFFB84D)),
                                    modifier = Modifier.padding(vertical = 4.dp)) {
                                    Text("11,48£",
                                        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 12.sp),
                                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 2.dp))
                                }
                                Text("You could've saved with Prime",
                                    style = TextStyle(fontSize = 12.sp)
                                )
                                Text("Try for free",
                                    style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold))
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(1.dp).fillMaxWidth().background(Color.LightGray))
                }
            }
        }
    }
}