package com.devlomi.meayar.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

/**
 * MEAYAR (معيار) Design System Corner Radiuses and Shapes.
 */
@Immutable
data class MeayarShapes(
    val none: Shape = RoundedCornerShape(0.dp),
    val extraSmall: Shape = RoundedCornerShape(4.dp),
    val small: Shape = RoundedCornerShape(6.dp),
    val medium: Shape = RoundedCornerShape(10.dp),
    val large: Shape = RoundedCornerShape(12.dp),
    val extraLarge: Shape = RoundedCornerShape(18.dp),
    val xxLarge: Shape = RoundedCornerShape(24.dp),
    val pill: Shape = RoundedCornerShape(999.dp)
)

val LocalMeayarShapes = staticCompositionLocalOf { MeayarShapes() }
