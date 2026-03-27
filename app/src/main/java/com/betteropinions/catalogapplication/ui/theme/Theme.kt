package com.betteropinions.catalogapplication.ui.theme

import android.app.Activity
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

// ── CatalogColors ─────────────────────────────────────────────────────────
/**
 * All app-specific colors in one place.
 * Access them in any Composable via [MaterialTheme.catalogColors].
 */
data class CatalogColors(
    // Brand / Primary
    val purple: Color = Purple,
    val purpleLight: Color = PurpleLight,
    val purpleDeep: Color = PurpleDeep,
    val purpleIndicator: Color = PurpleIndicator,

    // Text
    val navyDark: Color = NavyDark,
    val bluePurpleDark: Color = BluePurpleDark,

    // Semantic
    val greenSuccess: Color = GreenSuccess,
    val greenLight: Color = GreenLight,

    // Grays
    val grayLabel: Color = GrayLabel,
    val graySubtitle: Color = GraySubtitle,
    val grayTimer: Color = GrayTimer,
    val grayNav: Color = GrayNav,
    val grayPlaceholder: Color = GrayPlaceholder,
    val grayBorder: Color = GrayBorder,
    val grayBorderLight: Color = GrayBorderLight,
)

val LocalCatalogColors = staticCompositionLocalOf { CatalogColors() }

/** Convenient accessor: `MaterialTheme.catalogColors.purple` */
val MaterialTheme.catalogColors: CatalogColors
    @Composable
    @ReadOnlyComposable
    get() = LocalCatalogColors.current

// ── Material color schemes ─────────────────────────────────────────────────
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

// ── Theme ──────────────────────────────────────────────────────────────────
@Composable
fun CatalogApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
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

    CompositionLocalProvider(LocalCatalogColors provides CatalogColors()) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}