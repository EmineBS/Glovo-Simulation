package com.example.glovosimulation.ui.entryPoint.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.glovosimulation.ui.theme.GlovoSimulationTheme
import com.example.glovosimulation.ui.theme.circularPosition
import com.example.glovosimulation.ui.theme.myShape
import kotlinx.coroutines.launch
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

@Composable
fun centerBodyContent(
    circleDistance: Dp = 150.dp,
    centerCircleSize: Dp = 125.dp,
    outerCircleSize: Dp = 100.dp,
    centerCircleColor: Long,
    outerCircleColor: Long,
) {
    SimpleCircularLayout(
        modifier = Modifier.size(400.dp)
    ) {
        // Center item
        CircleItem(
            imageUrl = "https://cdn-icons-png.flaticon.com/128/9425/9425772.png",
            text = "Food",
            color = centerCircleColor,
            modifier = Modifier.size(centerCircleSize)
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
                modifier = Modifier
                    .size(outerCircleSize)
                    .circularPosition(
                        radius = circleDistance,
                        angle = angle.toFloat()
                    )
            )
        }
    }
}

@Composable
fun SimpleCircularLayout(
    modifier: Modifier = Modifier,
    radius: Dp = 150.dp,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

@Composable
private fun CircleItem(
    text: String,
    imageUrl: String,
    modifier: Modifier = Modifier,
    color: Long = 0xFFFFFFFF
) {

    var targetOffset by remember { mutableStateOf(Offset.Zero) }

    val animatedOffset by animateOffsetAsState(
        targetValue = targetOffset,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    Box(
        modifier = modifier.then(
            Modifier
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
        ),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .scale(1.08f)
                .myShape("right", 0.825f, 0x1D000000)
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .scale(1.06f)
                .myShape("right", 0.75f, 0x11D000000)
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .myShape("right", 0.8f, color),
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


@Preview(showBackground = true)
@Composable
fun bodyContentPreview() {
    GlovoSimulationTheme {
        centerBodyContent(centerCircleColor = 0xFFFFFFFF, outerCircleColor = 0xFFFFFFFF)
    }
}