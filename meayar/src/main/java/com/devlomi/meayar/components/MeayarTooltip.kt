package com.devlomi.meayar.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.devlomi.meayar.theme.MeayarTheme

/**
 * Arrow direction for [MeayarTooltip].
 */
enum class MeayarTooltipPlacement {
    Top,
    Bottom
}

/**
 * Official MEAYAR Tooltip bubble component.
 * - Inverse surface background
 * - High contrast inverse text
 * - 6dp rounded corners with optional contextual icon
 */
@Composable
fun MeayarTooltip(
    text: String,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    backgroundColor: Color = MeayarTheme.colors.backgroundInverse,
    contentColor: Color = MeayarTheme.colors.textInverse
) {
    val typography = MeayarTheme.typography
    val shape = RoundedCornerShape(6.dp)

    Box(
        modifier = modifier
            .shadow(4.dp, shape)
            .clip(shape)
            .background(backgroundColor)
            .padding(horizontal = 10.dp, vertical = 6.dp)
            .semantics(mergeDescendants = true) {
                contentDescription = text
            },
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = contentColor,
                    modifier = Modifier
                        .size(14.dp)
                        .padding(end = 6.dp)
                )
            }
            Text(
                text = text,
                style = typography.textXsMedium,
                color = contentColor
            )
        }
    }
}

/**
 * Interactive Tooltip Anchor Box that reveals a [MeayarTooltip] on click or long-press.
 */
@Composable
fun MeayarTooltipBox(
    tooltipText: String,
    modifier: Modifier = Modifier,
    placement: MeayarTooltipPlacement = MeayarTooltipPlacement.Top,
    icon: ImageVector? = null,
    content: @Composable () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        Box(
            modifier = Modifier.clickable { expanded = !expanded }
        ) {
            content()
        }

        if (expanded) {
            Popup(
                alignment = if (placement == MeayarTooltipPlacement.Top) Alignment.TopCenter else Alignment.BottomCenter,
                onDismissRequest = { expanded = false },
                properties = PopupProperties(focusable = true)
            ) {
                Box(
                    modifier = Modifier.padding(bottom = if (placement == MeayarTooltipPlacement.Top) 36.dp else 0.dp, top = if (placement == MeayarTooltipPlacement.Bottom) 36.dp else 0.dp)
                ) {
                    MeayarTooltip(
                        text = tooltipText,
                        icon = icon
                    )
                }
            }
        }
    }
}

/**
 * Mobile-idiomatic alias for [MeayarTooltip].
 * @deprecated Use [MeayarTooltip] directly.
 */
@Deprecated(
    message = "Use MeayarTooltip directly for consistency across platforms.",
    replaceWith = ReplaceWith("MeayarTooltip(text, modifier, icon, backgroundColor, contentColor)")
)
@Composable
fun MeayarHint(
    text: String,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    backgroundColor: Color = MeayarTheme.colors.backgroundInverse,
    contentColor: Color = MeayarTheme.colors.textInverse
) {
    MeayarTooltip(
        text = text,
        modifier = modifier,
        icon = icon,
        backgroundColor = backgroundColor,
        contentColor = contentColor
    )
}

/**
 * Mobile-idiomatic alias for [MeayarTooltipBox].
 * @deprecated Use [MeayarTooltipBox] directly.
 */
@Deprecated(
    message = "Use MeayarTooltipBox directly for consistency across platforms.",
    replaceWith = ReplaceWith("MeayarTooltipBox(tooltipText, modifier, placement, icon, content)")
)
@Composable
fun MeayarHintBox(
    tooltipText: String,
    modifier: Modifier = Modifier,
    placement: MeayarTooltipPlacement = MeayarTooltipPlacement.Top,
    icon: ImageVector? = null,
    content: @Composable () -> Unit
) {
    MeayarTooltipBox(
        tooltipText = tooltipText,
        modifier = modifier,
        placement = placement,
        icon = icon,
        content = content
    )
}
