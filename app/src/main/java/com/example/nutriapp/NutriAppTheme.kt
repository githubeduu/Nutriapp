package com.example.nutriapp

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.example.nutriapp.ui.theme.*


@Composable
fun NutriAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    // Define the color scheme based on the theme
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(4.dp),
        large = RoundedCornerShape(0.dp)
    )

    val whiteColor = Color(0xFFFFFFFF)
    val lightBlueColor = Color(0xFFADD8E6)
    val whiteToLightBlueGradient = Brush.verticalGradient(
        colors = listOf(whiteColor, lightBlueColor),
        startY = 0f,
        endY = 1000f
    )

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        shapes = shapes
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(whiteToLightBlueGradient)
        ) {
            content()
        }
    }
}