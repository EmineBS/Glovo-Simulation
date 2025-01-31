package com.example.glovosimulation.ui.entryPoint.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.glovosimulation.navigation.NavigationActions
import com.example.glovosimulation.navigation.NavigationDestination
import com.example.glovosimulation.ui.theme.GlovoSimulationTheme
import com.example.glovosimulation.ui.theme.circularPosition
import com.example.glovosimulation.ui.theme.myShape

@Composable
fun CenterBodyContent(
    circleDistance: Dp = 130.dp,
    centerCircleSize: Dp = 120.dp,
    outerCircleSize: Dp = 100.dp,
    centerCircleColor: Long = 0xFFFFFFFF,
    outerCircleColor: Long = 0xFFFFFFFF,
    navigateToMainShared: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircleItem(
            imageUrl = "https://cdn-icons-png.flaticon.com/128/9425/9425772.png",
            text = "Food",
            color = centerCircleColor,
            size = centerCircleSize,
            navigateToMainShared = {navigateToMainShared()}
        )

        // Calculate angles for 6 items
        val itemCount = 6
        val startAngle = -Math.PI / 2 // Start from top

        repeat(itemCount) { index ->
            val angle = startAngle + (2 * Math.PI * index / itemCount)
            CircleItem(
                imageUrl = when(index) {
                    0 -> "https://cdn-icons-png.flaticon.com/128/2203/2203183.png"
                    1 -> "https://cdn-icons-png.flaticon.com/128/3514/3514211.png"
                    2 -> "https://cdn-icons-png.flaticon.com/128/11590/11590493.png"
                    3 -> "https://cdn-icons-png.flaticon.com/128/12115/12115151.png"
                    4 -> "https://cdn-icons-png.flaticon.com/128/1655/1655721.png"
                    else -> "https://cdn-icons-png.flaticon.com/128/10578/10578386.png"
                },
                text = when(index) {
                    0 -> "Groceries"
                    1 -> "SuperGlovo"
                    2 -> "Empanadas"
                    3 -> "Package"
                    4 -> "Parapharmacy"
                    else -> "Shops"
                },
                color = outerCircleColor,
                size = outerCircleSize,
                circleDistance = circleDistance,
                angle = angle.toFloat(),
                navigateToMainShared = {navigateToMainShared()}
            )
        }
    }
}

@Composable
private fun CircleItem(
    text: String,
    imageUrl: String,
    color: Long = 0xFFFFFFFF,
    size: Dp,
    circleDistance: Dp = 0.dp,
    angle: Float = 0f,
    navigateToMainShared:() -> Unit
) {

    var targetOffset by remember { mutableStateOf(Offset.Zero) }

    val animatedOffset by animateOffsetAsState(
        targetValue = targetOffset,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ), label = ""
    )

    Box(
        modifier = Modifier
            .size(size)
            .circularPosition(
                radius = circleDistance,
                angle = angle.toFloat()
            )
            .graphicsLayer {
                translationX = animatedOffset.x
                translationY = animatedOffset.y
            }
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragEnd = { targetOffset = Offset.Zero },
                    onDragCancel = { targetOffset = Offset.Zero },
                    onDrag = { change, dragAmount ->
                        targetOffset = targetOffset.plus(Offset(dragAmount.x, dragAmount.y))
                    }
                )
            }
        ,
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .shadow(elevation = 12.dp, shape = CircleShape)
                .clip(CircleShape)
                //.background(color = Color(0x11D00000))
                .background(color = Color(color))
                .clickable { navigateToMainShared() },
            contentAlignment = Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(6.dp, alignment = Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = "",
                    modifier = Modifier.size(42.dp)
                )
                Text(text, style = TextStyle(fontSize = 10.sp))
            }
        }
    }
}

