package com.devlomi.meayar.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

/**
 * Entry point to the MEAYAR (معيار) Design System.
 */
@Composable
fun MeayarTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    colors: MeayarColors = if (darkTheme) darkMeayarColors() else lightMeayarColors(),
    typography: MeayarTypography = MeayarTypography(),
    shapes: MeayarShapes = MeayarShapes(),
    content: @Composable () -> Unit
) {
    // Map MEAYAR tokens to Material 3 ColorScheme for interoperability
    val m3ColorScheme = if (darkTheme) {
        darkColorScheme(
            primary = colors.backgroundBrand,
            onPrimary = colors.textInverse,
            primaryContainer = colors.backgroundBrandSubtle,
            onPrimaryContainer = colors.textBrand,
            surface = colors.backgroundSurface,
            onSurface = colors.textPrimary,
            background = colors.backgroundPage,
            onBackground = colors.textPrimary,
            error = colors.backgroundError,
            onError = colors.textInverse,
            outline = colors.borderDefault
        )
    } else {
        lightColorScheme(
            primary = colors.backgroundBrand,
            onPrimary = colors.textInverse,
            primaryContainer = colors.backgroundBrandSubtle,
            onPrimaryContainer = colors.textBrand,
            surface = colors.backgroundSurface,
            onSurface = colors.textPrimary,
            background = colors.backgroundPage,
            onBackground = colors.textPrimary,
            error = colors.backgroundError,
            onError = colors.textInverse,
            outline = colors.borderDefault
        )
    }

    // Bridge typography to Material 3 so all Text() calls use Qomra Arabic
    val m3Typography = androidx.compose.material3.Typography(
        displayLarge = typography.headingXl,
        displayMedium = typography.headingLg,
        displaySmall = typography.headingMd,
        headlineLarge = typography.headingSm,
        headlineMedium = typography.headingXs,
        headlineSmall = typography.heading2xs,
        titleLarge = typography.textXl,
        titleMedium = typography.textLgMedium,
        titleSmall = typography.textMdMedium,
        bodyLarge = typography.textMd,
        bodyMedium = typography.textSm,
        bodySmall = typography.textXs,
        labelLarge = typography.textSmMedium,
        labelMedium = typography.textXsMedium,
        labelSmall = typography.text2xs
    )

    CompositionLocalProvider(
        LocalMeayarColors provides colors,
        LocalMeayarTypography provides typography,
        LocalMeayarShapes provides shapes
    ) {
        MaterialTheme(
            colorScheme = m3ColorScheme,
            typography = m3Typography,
            content = content
        )
    }
}

/**
 * Convenient object accessor for MEAYAR design tokens.
 */
object MeayarTheme {
    val colors: MeayarColors
        @Composable
        @ReadOnlyComposable
        get() = LocalMeayarColors.current

    val typography: MeayarTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalMeayarTypography.current

    val shapes: MeayarShapes
        @Composable
        @ReadOnlyComposable
        get() = LocalMeayarShapes.current
}
