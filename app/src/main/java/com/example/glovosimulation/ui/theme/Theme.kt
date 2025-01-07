package com.example.glovosimulation.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.Morph
import androidx.graphics.shapes.toPath


private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun GlovoSimulationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

fun Modifier.myShape(direction: String): Modifier {
    return this.then(
        Modifier.drawWithCache {
            val triangle = RoundedPolygon(
                numVertices = 3,
                radius = size.minDimension / 2f,
                centerX = size.width - (size.width / 2f),
                centerY = size.height / 2f,
                rounding = CornerRounding(
                    size.minDimension / 10f,
                    smoothing = 0.1f
                )
            )
            val circle = RoundedPolygon(
                numVertices = 30, // Higher number of vertices for a smoother circular shape
                radius = size.minDimension / 2f,
                centerX = size.width - (size.width / 2f),
                centerY = size.height / 2f
            )
            val morph = Morph(start = triangle, end = circle)
            val morphPath = morph.toPath(progress = 0.75f).asComposePath() // Adjust progress to get a closer morph

            onDrawBehind {
                if (direction.equals("right", ignoreCase = true)) {
                    drawPath(morphPath, color = Color(0xFFEEEEEE)) // Use a grey color
                } else {
                    translate(left = size.width - morphPath.getBounds().width) { // Adjust translation to maintain position
                        scale(scaleX = -1f, scaleY = 1f) { // Flip horizontally
                            drawPath(morphPath, color = Color(0xFFEEEEEE)) // Use a grey color
                        }
                    }
                }
            }
        }
    ).background(Color.Transparent) // No background color overlay, just the shape
}

fun Modifier.curvedTopShape(
    backgroundColor: Color = Color.White,
    curveHeight: Float = 40f
): Modifier = this.drawBehind {
    val width = size.width
    val height = size.height

    val path = Path().apply {
        // Start from top-left
        moveTo(0f, curveHeight)
        // Draw curve to top-right
        cubicTo(
            width / 4f, 0f,  // First control point
            3 * width / 4f, 0f,  // Second control point
            width, curveHeight   // End point
        )
        // Complete the rectangle
        lineTo(width, height)
        lineTo(0f, height)
        close()
    }

    drawPath(
        path = path,
        color = backgroundColor
    )
}