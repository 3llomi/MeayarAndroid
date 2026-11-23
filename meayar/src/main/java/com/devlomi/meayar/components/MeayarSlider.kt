package com.devlomi.meayar.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.devlomi.meayar.theme.MeayarTheme

/**
 * Official MEAYAR Slider component.
 */
@Composable
fun MeayarSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    steps: Int = 0,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    val colors = MeayarTheme.colors

    Slider(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        enabled = enabled,
        valueRange = valueRange,
        steps = steps,
        colors = SliderDefaults.colors(
            thumbColor = colors.backgroundBrand,
            activeTrackColor = colors.backgroundBrand,
            inactiveTrackColor = colors.backgroundMuted,
            disabledThumbColor = colors.textDisabled,
            disabledActiveTrackColor = colors.backgroundDisabled,
            disabledInactiveTrackColor = colors.backgroundSubtle
        ),
        interactionSource = interactionSource
    )
}
