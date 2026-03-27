package com.betteropinions.catalogapplication.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

data class CatalogColors(
    val purple: Color = Purple,
    val purpleLight: Color = PurpleLight,
    val purpleDeep: Color = PurpleDeep,
    val purpleIndicator: Color = PurpleIndicator,

    val navyDark: Color = NavyDark,
    val bluePurpleDark: Color = BluePurpleDark,

    val greenSuccess: Color = GreenSuccess,
    val greenLight: Color = GreenLight,

    val grayLabel: Color = GrayLabel,
    val graySubtitle: Color = GraySubtitle,
    val grayTimer: Color = GrayTimer,
    val grayNav: Color = GrayNav,
    val grayPlaceholder: Color = GrayPlaceholder,
    val grayBorder: Color = GrayBorder,
    val grayBorderLight: Color = GrayBorderLight,
)

val LocalCatalogColors = staticCompositionLocalOf { CatalogColors() }

val MaterialTheme.catalogColors: CatalogColors
    @Composable
    @ReadOnlyComposable
    get() = LocalCatalogColors.current

private val DarkColorScheme = darkColorScheme(
    primary = Purple,
    secondary = PurpleLight,
    tertiary = PurpleDeep,
)

private val LightColorScheme = lightColorScheme(
    primary = Purple,
    secondary = PurpleLight,
    tertiary = PurpleDeep,
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = NavyDark,
    onSurface = NavyDark,
)

@Composable
fun CatalogApplicationTheme(
    content: @Composable () -> Unit,
) {

    val colorScheme = LightColorScheme

    CompositionLocalProvider(LocalCatalogColors provides CatalogColors()) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}