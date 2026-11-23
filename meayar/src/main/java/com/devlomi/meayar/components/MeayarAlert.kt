package com.devlomi.meayar.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.devlomi.meayar.icons.MeayarIcons
import com.devlomi.meayar.theme.MeayarTheme

/**
 * Official Alert styles for the Syrian Digital Identity design system.
 */
enum class MeayarAlertStyle {
    Primary,    // Syrian Brand Green filled (#094239)
    Secondary,  // Brand subtle filled (#D8DFDE)
    Neutral,    // Neutral subtle gray filled (#F6F8FA)
    Simple      // Transparent bordered banner
}

/**
 * Official MEAYAR Alert component.
 * Supports brand alert styles (Primary, Secondary, Neutral, Simple).
 */
@Composable
fun MeayarAlert(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    style: MeayarAlertStyle = MeayarAlertStyle.Secondary,
    dismissContentDescription: String = "إغلاق",
    onDismiss: (() -> Unit)? = null
) {
    val colors = MeayarTheme.colors
    val typography = MeayarTheme.typography

    val (bg, textPrimaryCol, textDescCol, borderCol, iconCol) = when (style) {
        MeayarAlertStyle.Primary -> AlertVisualTokens(
            colors.backgroundBrand,
            colors.textInverse,
            colors.textInverse.copy(alpha = 0.85f),
            null,
            colors.textInverse
        )
        MeayarAlertStyle.Secondary -> AlertVisualTokens(
            colors.backgroundBrandSubtle,
            colors.textBrand,
            colors.textSecondary,
            BorderStroke(1.dp, colors.borderBrand.copy(alpha = 0.3f)),
            colors.iconBrand
        )
        MeayarAlertStyle.Neutral -> AlertVisualTokens(
            colors.backgroundSubtle,
            colors.textPrimary,
            colors.textSecondary,
            BorderStroke(1.dp, colors.borderSubtle),
            colors.iconSecondary
        )
        MeayarAlertStyle.Simple -> AlertVisualTokens(
            Color.Transparent,
            colors.textPrimary,
            colors.textSecondary,
            BorderStroke(1.dp, colors.borderDefault),
            colors.iconBrand
        )
    }

    val shape = RoundedCornerShape(8.dp)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape)
            .background(bg)
            .then(if (borderCol != null) Modifier.border(borderCol, shape) else Modifier)
            .padding(14.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.Top
        ) {
            Icon(
                imageVector = MeayarIcons.Info,
                contentDescription = null,
                tint = iconCol,
                modifier = Modifier.size(20.dp)
            )

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = typography.textSmBold,
                    color = textPrimaryCol
                )
                Text(
                    text = description,
                    style = typography.textXs,
                    color = textDescCol,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }

            if (onDismiss != null) {
                IconButton(
                    onClick = onDismiss,
                    modifier = Modifier.size(28.dp)
                ) {
                    Icon(
                        imageVector = MeayarIcons.Close,
                        contentDescription = dismissContentDescription,
                        tint = textDescCol,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}

private data class AlertVisualTokens(
    val bg: Color,
    val textPrimaryCol: Color,
    val textDescCol: Color,
    val borderCol: BorderStroke?,
    val iconCol: Color
)
