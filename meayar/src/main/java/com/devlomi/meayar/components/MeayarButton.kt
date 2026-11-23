package com.devlomi.meayar.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.devlomi.meayar.theme.MeayarTheme

enum class MeayarButtonVariant {
    Primary,
    Secondary,
    Outlined,
    Ghost,
    Destructive
}

enum class MeayarButtonSize {
    XL,
    LG,
    MD,
    SM,
    XS
}

internal data class MeayarButtonSpecs(
    val height: Dp,
    val horizontalPadding: Dp,
    val iconGap: Dp,
    val iconSize: Dp,
    val cornerRadius: Dp
)

internal fun getButtonSpecs(size: MeayarButtonSize): MeayarButtonSpecs = when (size) {
    MeayarButtonSize.XL -> MeayarButtonSpecs(
        height = 60.dp,
        horizontalPadding = 24.dp,
        iconGap = 12.dp,
        iconSize = 24.dp,
        cornerRadius = 6.dp
    )
    MeayarButtonSize.LG -> MeayarButtonSpecs(
        height = 48.dp,
        horizontalPadding = 20.dp,
        iconGap = 10.dp,
        iconSize = 20.dp,
        cornerRadius = 6.dp
    )
    MeayarButtonSize.MD -> MeayarButtonSpecs(
        height = 44.dp,
        horizontalPadding = 16.dp,
        iconGap = 8.dp,
        iconSize = 20.dp,
        cornerRadius = 6.dp
    )
    MeayarButtonSize.SM -> MeayarButtonSpecs(
        height = 32.dp,
        horizontalPadding = 12.dp,
        iconGap = 8.dp,
        iconSize = 16.dp,
        cornerRadius = 6.dp
    )
    MeayarButtonSize.XS -> MeayarButtonSpecs(
        height = 26.dp,
        horizontalPadding = 10.dp,
        iconGap = 6.dp,
        iconSize = 14.dp,
        cornerRadius = 4.dp
    )
}

/**
 * Official MEAYAR Button component conforming to Syrian design system specifications.
 * Supports XL, LG, MD, SM, and XS sizes, loading state, icon slots, and RTL automatic flow.
 */
@Composable
fun MeayarButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    variant: MeayarButtonVariant = MeayarButtonVariant.Primary,
    size: MeayarButtonSize = MeayarButtonSize.MD,
    enabled: Boolean = true,
    loading: Boolean = false,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable () -> Unit
) {
    val specs = getButtonSpecs(size)
    val colors = MeayarTheme.colors
    val typography = MeayarTheme.typography

    val textStyle: TextStyle = when (size) {
        MeayarButtonSize.XL -> typography.textLgMedium
        MeayarButtonSize.LG -> typography.textMdMedium
        MeayarButtonSize.MD -> typography.textSmMedium
        MeayarButtonSize.SM -> typography.textSm
        MeayarButtonSize.XS -> typography.textXs
    }

    val (containerColor, contentColor, border) = when (variant) {
        MeayarButtonVariant.Primary -> Triple(
            colors.backgroundBrand,
            colors.textInverse,
            null
        )
        MeayarButtonVariant.Secondary -> Triple(
            colors.backgroundBrandSubtle,
            colors.textBrand,
            null
        )
        MeayarButtonVariant.Outlined -> Triple(
            Color.Transparent,
            colors.textBrand,
            BorderStroke(1.dp, colors.borderBrand)
        )
        MeayarButtonVariant.Ghost -> Triple(
            Color.Transparent,
            colors.textBrand,
            null
        )
        MeayarButtonVariant.Destructive -> Triple(
            colors.backgroundError,
            colors.textInverse,
            null
        )
    }

    val buttonColors = ButtonDefaults.buttonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = colors.backgroundDisabled,
        disabledContentColor = colors.textDisabled
    )

    Button(
        onClick = onClick,
        modifier = modifier.defaultMinSize(minHeight = specs.height),
        enabled = enabled && !loading,
        shape = RoundedCornerShape(specs.cornerRadius),
        colors = buttonColors,
        border = if (enabled && !loading) border else null,
        elevation = null,
        contentPadding = PaddingValues(horizontal = specs.horizontalPadding),
        interactionSource = interactionSource
    ) {
        if (loading) {
            CircularProgressIndicator(
                modifier = Modifier.size(specs.iconSize),
                color = contentColor,
                strokeWidth = 2.dp
            )
        } else {
            Row(
                horizontalArrangement = Arrangement.spacedBy(specs.iconGap),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (leadingIcon != null) {
                    Box(modifier = Modifier.size(specs.iconSize), contentAlignment = Alignment.Center) {
                        leadingIcon()
                    }
                }
                ProvideTextStyle(textStyle.copy(color = contentColor)) {
                    content()
                }
                if (trailingIcon != null) {
                    Box(modifier = Modifier.size(specs.iconSize), contentAlignment = Alignment.Center) {
                        trailingIcon()
                    }
                }
            }
        }
    }
}

/**
 * Convenient text overload for [MeayarButton].
 */
@Composable
fun MeayarButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    variant: MeayarButtonVariant = MeayarButtonVariant.Primary,
    size: MeayarButtonSize = MeayarButtonSize.MD,
    enabled: Boolean = true,
    loading: Boolean = false,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    MeayarButton(
        onClick = onClick,
        modifier = modifier,
        variant = variant,
        size = size,
        enabled = enabled,
        loading = loading,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        interactionSource = interactionSource
    ) {
        Text(text = text)
    }
}

/**
 * Official MEAYAR Icon-Only Button component.
 */
@Composable
fun MeayarIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    variant: MeayarButtonVariant = MeayarButtonVariant.Ghost,
    size: MeayarButtonSize = MeayarButtonSize.MD,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable () -> Unit
) {
    val specs = getButtonSpecs(size)
    val colors = MeayarTheme.colors

    val (containerColor, contentColor, border) = when (variant) {
        MeayarButtonVariant.Primary -> Triple(colors.backgroundBrand, colors.textInverse, null)
        MeayarButtonVariant.Secondary -> Triple(colors.backgroundBrandSubtle, colors.textBrand, null)
        MeayarButtonVariant.Outlined -> Triple(Color.Transparent, colors.textBrand, BorderStroke(1.dp, colors.borderBrand))
        MeayarButtonVariant.Ghost -> Triple(Color.Transparent, colors.iconSecondary, null)
        MeayarButtonVariant.Destructive -> Triple(colors.backgroundError, colors.textInverse, null)
    }

    Button(
        onClick = onClick,
        modifier = modifier.size(specs.height),
        enabled = enabled,
        shape = RoundedCornerShape(specs.cornerRadius),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = colors.backgroundDisabled,
            disabledContentColor = colors.textDisabled
        ),
        border = if (enabled) border else null,
        elevation = null,
        contentPadding = PaddingValues(0.dp),
        interactionSource = interactionSource
    ) {
        Box(modifier = Modifier.size(specs.iconSize), contentAlignment = Alignment.Center) {
            content()
        }
    }
}

