package com.devlomi.meayar.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.toggleable
import androidx.compose.ui.semantics.Role
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.devlomi.meayar.icons.MeayarIcons
import com.devlomi.meayar.theme.MeayarTheme

/**
 * Official MEAYAR Checkbox component.
 */
@Composable
fun MeayarCheckbox(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: String? = null,
    description: String? = null
) {
    val colors = MeayarTheme.colors
    val typography = MeayarTheme.typography

    val bgColor by animateColorAsState(
        when {
            !enabled -> colors.backgroundDisabled
            checked -> colors.backgroundBrand
            else -> colors.backgroundSurface
        },
        label = "cb_bg"
    )

    val borderColor by animateColorAsState(
        when {
            !enabled -> colors.borderSubtle
            checked -> colors.borderBrand
            else -> colors.borderDefault
        },
        label = "cb_border"
    )

    val shape = RoundedCornerShape(6.dp)

    val checkboxControl = @Composable {
        Box(
            modifier = Modifier
                .size(24.dp)
                .clip(shape)
                .background(bgColor)
                .border(if (checked) 0.dp else 1.5.dp, borderColor, shape)
                .then(
                    if (onCheckedChange != null && label == null) {
                        Modifier.toggleable(
                            value = checked,
                            enabled = enabled,
                            role = Role.Checkbox,
                            onValueChange = onCheckedChange
                        )
                    } else Modifier
                ),
            contentAlignment = Alignment.Center
        ) {
            if (checked) {
                Icon(
                    imageVector = MeayarIcons.Check,
                    contentDescription = null,
                    tint = colors.textInverse,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }

    if (label == null && description == null) {
        Box(modifier = modifier) { checkboxControl() }
    } else {
        Row(
            modifier = modifier
                .then(
                    if (onCheckedChange != null) {
                        Modifier.toggleable(
                            value = checked,
                            enabled = enabled,
                            role = Role.Checkbox,
                            onValueChange = onCheckedChange
                        )
                    } else Modifier
                )
                .padding(vertical = 4.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            checkboxControl()
            Column {
                if (label != null) {
                    Text(
                        text = label,
                        style = typography.textSmMedium,
                        color = if (enabled) colors.textPrimary else colors.textDisabled
                    )
                }
                if (description != null) {
                    Text(
                        text = description,
                        style = typography.textXs,
                        color = if (enabled) colors.textTertiary else colors.textDisabled,
                        modifier = Modifier.padding(top = 2.dp)
                    )
                }
            }
        }
    }
}

/**
 * Official MEAYAR Radio Button component.
 */
@Composable
fun MeayarRadioButton(
    selected: Boolean,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: String? = null,
    description: String? = null
) {
    val colors = MeayarTheme.colors
    val typography = MeayarTheme.typography

    val borderColor by animateColorAsState(
        when {
            !enabled -> colors.borderSubtle
            selected -> colors.borderBrand
            else -> colors.borderDefault
        },
        label = "rb_border"
    )

    val radioControl = @Composable {
        Box(
            modifier = Modifier
                .size(24.dp)
                .clip(CircleShape)
                .background(if (enabled) colors.backgroundSurface else colors.backgroundDisabled)
                .border(if (selected) 2.dp else 1.5.dp, borderColor, CircleShape)
                .then(
                    if (onClick != null && label == null) {
                        Modifier.selectable(
                            selected = selected,
                            enabled = enabled,
                            role = Role.RadioButton,
                            onClick = onClick
                        )
                    } else Modifier
                ),
            contentAlignment = Alignment.Center
        ) {
            if (selected) {
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .clip(CircleShape)
                        .background(if (enabled) colors.backgroundBrand else colors.textDisabled)
                )
            }
        }
    }

    if (label == null && description == null) {
        Box(modifier = modifier) { radioControl() }
    } else {
        Row(
            modifier = modifier
                .then(
                    if (onClick != null) {
                        Modifier.selectable(
                            selected = selected,
                            enabled = enabled,
                            role = Role.RadioButton,
                            onClick = onClick
                        )
                    } else Modifier
                )
                .padding(vertical = 4.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            radioControl()
            Column {
                if (label != null) {
                    Text(
                        text = label,
                        style = typography.textSmMedium,
                        color = if (enabled) colors.textPrimary else colors.textDisabled
                    )
                }
                if (description != null) {
                    Text(
                        text = description,
                        style = typography.textXs,
                        color = if (enabled) colors.textTertiary else colors.textDisabled,
                        modifier = Modifier.padding(top = 2.dp)
                    )
                }
            }
        }
    }
}

/**
 * Official MEAYAR Toggle Switch component.
 */
@Composable
fun MeayarSwitch(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: String? = null,
    description: String? = null
) {
    val colors = MeayarTheme.colors
    val typography = MeayarTheme.typography

    val trackColor by animateColorAsState(
        when {
            !enabled -> colors.backgroundDisabled
            checked -> colors.backgroundBrand
            else -> colors.backgroundMuted
        },
        label = "switch_track"
    )

    val thumbOffset by animateDpAsState(
        targetValue = if (checked) 17.dp else 2.dp,
        label = "switch_thumb"
    )

    val switchControl = @Composable {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
            Box(
                modifier = Modifier
                    .size(width = 40.dp, height = 24.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(trackColor)
                    .then(
                        if (onCheckedChange != null && label == null) {
                            Modifier.toggleable(
                                value = checked,
                                enabled = enabled,
                                role = Role.Switch,
                                onValueChange = onCheckedChange
                            )
                        } else Modifier
                    )
                    .padding(horizontal = 1.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Box(
                    modifier = Modifier
                        .offset { IntOffset(thumbOffset.roundToPx(), 0) }
                        .size(20.dp)
                        .clip(CircleShape)
                        .background(colors.backgroundSurface)
                )
            }
        }
    }

    if (label == null && description == null) {
        Box(modifier = modifier) { switchControl() }
    } else {
        Row(
            modifier = modifier
                .then(
                    if (onCheckedChange != null) {
                        Modifier.toggleable(
                            value = checked,
                            enabled = enabled,
                            role = Role.Switch,
                            onValueChange = onCheckedChange
                        )
                    } else Modifier
                )
                .padding(vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                if (label != null) {
                    Text(
                        text = label,
                        style = typography.textSmMedium,
                        color = if (enabled) colors.textPrimary else colors.textDisabled
                    )
                }
                if (description != null) {
                    Text(
                        text = description,
                        style = typography.textXs,
                        color = if (enabled) colors.textTertiary else colors.textDisabled,
                        modifier = Modifier.padding(top = 2.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            switchControl()
        }
    }
}
