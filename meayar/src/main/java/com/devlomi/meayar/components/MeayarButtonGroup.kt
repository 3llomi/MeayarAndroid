package com.devlomi.meayar.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.devlomi.meayar.theme.MeayarTheme

/**
 * Data item definition for [MeayarSegmentedButton] and [MeayarButtonGroup].
 */
data class MeayarButtonGroupItem(
    val title: String,
    val leadingIcon: (@Composable () -> Unit)? = null,
    val trailingIcon: (@Composable () -> Unit)? = null,
    val enabled: Boolean = true
)

typealias MeayarSegmentedButtonItem = MeayarButtonGroupItem

/**
 * Official MEAYAR Segmented Button (Button Group) component.
 *
 * Supports connected segmented action groups and single selection toggle groups.
 * Outer corners respect RTL/LTR layout direction automatically with crisp inner dividers.
 */
@Composable
fun MeayarSegmentedButton(
    items: List<MeayarButtonGroupItem>,
    onItemSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    selectedIndex: Int? = null,
    size: MeayarButtonSize = MeayarButtonSize.MD,
    variant: MeayarButtonVariant = MeayarButtonVariant.Outlined
) {
    val specs = getButtonSpecs(size)
    val colors = MeayarTheme.colors
    val typography = MeayarTheme.typography

    val textStyle = when (size) {
        MeayarButtonSize.XL -> typography.textLgMedium
        MeayarButtonSize.LG -> typography.textMdMedium
        MeayarButtonSize.MD -> typography.textSmMedium
        MeayarButtonSize.SM -> typography.textSm
        MeayarButtonSize.XS -> typography.textXs
    }

    val overallShape = RoundedCornerShape(specs.cornerRadius)

    Row(
        modifier = modifier
            .height(IntrinsicSize.Min)
            .clip(overallShape)
            .border(1.dp, colors.borderDefault, overallShape)
    ) {
        items.forEachIndexed { index, item ->
            val isSelected = selectedIndex == index
            val isFirst = index == 0
            val isLast = index == items.size - 1

            // Compose RoundedCornerShape automatically mirrors topStart/topEnd based on LayoutDirection
            val itemShape = RoundedCornerShape(
                topStart = if (isFirst) specs.cornerRadius else 0.dp,
                bottomStart = if (isFirst) specs.cornerRadius else 0.dp,
                topEnd = if (isLast) specs.cornerRadius else 0.dp,
                bottomEnd = if (isLast) specs.cornerRadius else 0.dp
            )

            val (bgColor, contentColor) = when {
                !item.enabled -> Pair(colors.backgroundDisabled, colors.textDisabled)
                isSelected -> Pair(colors.backgroundBrand, colors.textInverse)
                variant == MeayarButtonVariant.Secondary -> Pair(colors.backgroundBrandSubtle, colors.textBrand)
                else -> Pair(colors.backgroundSurface, colors.textPrimary)
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .defaultMinSize(minHeight = specs.height)
                    .clip(itemShape)
                    .background(bgColor)
                    .clickable(
                        enabled = item.enabled,
                        role = if (selectedIndex != null) Role.RadioButton else Role.Button
                    ) {
                        onItemSelected(index)
                    }
                    .semantics {
                        if (selectedIndex != null) {
                            selected = isSelected
                        }
                    }
                    .padding(horizontal = specs.horizontalPadding, vertical = 4.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(specs.iconGap)
                ) {
                    if (item.leadingIcon != null) {
                        Box(
                            modifier = Modifier.defaultMinSize(minWidth = specs.iconSize, minHeight = specs.iconSize),
                            contentAlignment = Alignment.Center
                        ) {
                            item.leadingIcon.invoke()
                        }
                    }

                    ProvideTextStyle(textStyle.copy(color = contentColor)) {
                        Text(text = item.title)
                    }

                    if (item.trailingIcon != null) {
                        Box(
                            modifier = Modifier.defaultMinSize(minWidth = specs.iconSize, minHeight = specs.iconSize),
                            contentAlignment = Alignment.Center
                        ) {
                            item.trailingIcon.invoke()
                        }
                    }
                }
            }

            // Inner divider between buttons
            if (!isLast) {
                Box(
                    modifier = Modifier
                        .width(1.dp)
                        .fillMaxHeight()
                        .background(if (isSelected || (selectedIndex == index + 1)) colors.borderBrand else colors.borderDefault)
                )
            }
        }
    }
}

/**
 * Backward-compatible alias for [MeayarSegmentedButton].
 */
@Composable
fun MeayarButtonGroup(
    items: List<MeayarButtonGroupItem>,
    selectedIndex: Int? = null,
    onItemSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    size: MeayarButtonSize = MeayarButtonSize.MD,
    variant: MeayarButtonVariant = MeayarButtonVariant.Outlined
) {
    MeayarSegmentedButton(
        items = items,
        onItemSelected = onItemSelected,
        modifier = modifier,
        selectedIndex = selectedIndex,
        size = size,
        variant = variant
    )
}
