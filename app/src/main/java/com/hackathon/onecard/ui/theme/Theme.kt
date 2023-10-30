package com.hackathon.onecard.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Shapes
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.LaunchedEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorScheme = darkColorScheme(
    primary = Black,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorPalette = lightColors(
    primary = Black,
    secondary = PurpleGrey40,
    background = lightBackGroundColor
)

@Composable
fun OneCardTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        LightColorPalette
    } else {
        LightColorPalette
    }

    val systemUiController = rememberSystemUiController()
    LaunchedEffect(
        key1 = colors,
        block = {
            systemUiController.setSystemBarsColor(
                color = colors.background,
                darkIcons = !darkTheme,
            )
        }
    )

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}