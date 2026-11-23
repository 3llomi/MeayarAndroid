package com.devlomi.meayar.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.selection.selectable
import androidx.compose.ui.semantics.Role
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.devlomi.meayar.theme.MeayarTheme

enum class MeayarTabStyle {
    Line,
    Pill
}

/**
 * Official MEAYAR Tabs component.
 * Supports Line (bottom indicator) and Pill (segmented control) styles.
 *
 * Implements crisp, flicker-free transitions using matched-alpha color interpolation
 * and decoupled interaction sources.
 */
@Composable
fun MeayarTabs(
    tabs: List<String>,
    selectedIndex: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    style: MeayarTabStyle = MeayarTabStyle.Line
) {
    val colors = MeayarTheme.colors
    val typography = MeayarTheme.typography

    when (style) {
        MeayarTabStyle.Pill -> {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(colors.backgroundSubtle)
                    .padding(4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                tabs.forEachIndexed { index, title ->
                    val isSelected = selectedIndex == index
                    // Matched-RGB alpha interpolation avoids interpolating through dark grey
                    val targetBg = if (isSelected) colors.backgroundSurface else colors.backgroundSurface.copy(alpha = 0f)
                    val targetText = if (isSelected) colors.textBrand else colors.textTertiary

                    val bgColor by animateColorAsState(
                        targetValue = targetBg,
                        animationSpec = tween(durationMillis = 150),
                        label = "tab_pill_bg"
                    )
                    val textColor by animateColorAsState(
                        targetValue = targetText,
                        animationSpec = tween(durationMillis = 150),
                        label = "tab_pill_text"
                    )

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(6.dp))
                            .background(bgColor)
                            .selectable(
                                selected = isSelected,
                                role = Role.Tab,
                                onClick = { onTabSelected(index) }
                            )
                            .padding(vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = title,
                            style = typography.textSmMedium,
                            color = textColor
                        )
                    }
                }
            }
        }
        MeayarTabStyle.Line -> {
            Column(modifier = modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    tabs.forEachIndexed { index, title ->
                        val isSelected = selectedIndex == index
                        val targetText = if (isSelected) colors.textBrand else colors.textTertiary
                        val targetLine = if (isSelected) colors.borderBrand else colors.borderBrand.copy(alpha = 0f)

                        val textColor by animateColorAsState(
                            targetValue = targetText,
                            animationSpec = tween(durationMillis = 150),
                            label = "tab_line_text"
                        )
                        val lineColor by animateColorAsState(
                            targetValue = targetLine,
                            animationSpec = tween(durationMillis = 150),
                            label = "tab_line_indicator"
                        )

                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .selectable(
                                    selected = isSelected,
                                    role = Role.Tab,
                                    onClick = { onTabSelected(index) }
                                )
                                .padding(top = 10.dp, bottom = 8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = title,
                                style = typography.textSmMedium,
                                color = textColor
                            )
                            Box(
                                modifier = Modifier
                                    .padding(top = 8.dp)
                                    .fillMaxWidth(0.8f)
                                    .height(2.dp)
                                    .background(lineColor)
                            )
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(colors.borderSubtle)
                )
            }
        }
    }
}
