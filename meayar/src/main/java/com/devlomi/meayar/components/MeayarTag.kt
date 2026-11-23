package com.devlomi.meayar.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.devlomi.meayar.theme.MeayarPrimitives
import com.devlomi.meayar.theme.MeayarTheme

enum class MeayarTagColor {
    Primary,
    Gray,
    Green,
    Red,
    Yellow,
    Gold,
    Blue
}

enum class MeayarTagHierarchy {
    Filled,
    Secondary,
    Outlined
}

enum class MeayarTagSize {
    Large,
    Medium,
    Small
}

/**
 * Official MEAYAR Tag / Badge component for status and categorization.
 */
@Composable
fun MeayarTag(
    text: String,
    modifier: Modifier = Modifier,
    color: MeayarTagColor = MeayarTagColor.Primary,
    hierarchy: MeayarTagHierarchy = MeayarTagHierarchy.Secondary,
    size: MeayarTagSize = MeayarTagSize.Medium,
    leadingIcon: (@Composable () -> Unit)? = null
) {
    val colors = MeayarTheme.colors
    val typography = MeayarTheme.typography

    val (padding, textStyle, iconSize) = when (size) {
        MeayarTagSize.Large -> Triple(PaddingValues(horizontal = 10.dp, vertical = 6.dp), typography.textSmMedium, 16.dp)
        MeayarTagSize.Medium -> Triple(PaddingValues(horizontal = 8.dp, vertical = 4.dp), typography.textXsMedium, 14.dp)
        MeayarTagSize.Small -> Triple(PaddingValues(horizontal = 6.dp, vertical = 2.dp), typography.text2xsMedium, 12.dp)
    }

    val (bgSecondary, textCol) = when (color) {
        MeayarTagColor.Primary -> Pair(colors.tagPrimaryBackground, colors.tagPrimaryText)
        MeayarTagColor.Green -> Pair(colors.tagGreenBackground, colors.tagGreenText)
        MeayarTagColor.Red -> Pair(colors.tagRedBackground, colors.tagRedText)
        MeayarTagColor.Yellow -> Pair(colors.tagYellowBackground, colors.tagYellowText)
        MeayarTagColor.Gold -> Pair(colors.tagGoldBackground, colors.tagGoldText)
        MeayarTagColor.Blue -> Pair(colors.tagBlueBackground, colors.tagBlueText)
        MeayarTagColor.Gray -> Pair(colors.tagGrayBackground, colors.tagGrayText)
    }

    val (containerColor, contentColor, borderStroke) = when (hierarchy) {
        MeayarTagHierarchy.Filled -> Triple(textCol, colors.textInverse, null)
        MeayarTagHierarchy.Secondary -> Triple(bgSecondary, textCol, null)
        MeayarTagHierarchy.Outlined -> Triple(Color.Transparent, textCol, BorderStroke(1.dp, textCol.copy(alpha = 0.5f)))
    }

    val shape = RoundedCornerShape(4.dp)

    Box(
        modifier = modifier
            .clip(shape)
            .then(if (borderStroke != null) Modifier.border(borderStroke, shape) else Modifier)
            .background(containerColor)
            .padding(padding),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            if (leadingIcon != null) {
                Box(modifier = Modifier.size(iconSize), contentAlignment = Alignment.Center) {
                    leadingIcon()
                }
            }
            Text(
                text = text,
                style = textStyle,
                color = contentColor,
                maxLines = 1,
                softWrap = false
            )
        }
    }
}
