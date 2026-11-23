package com.devlomi.meayar.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import com.devlomi.meayar.icons.MeayarIcons
import com.devlomi.meayar.theme.MeayarTheme

/**
 * Official MEAYAR Accordion component with state hoisting.
 */
@Composable
fun MeayarAccordion(
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    title: String,
    modifier: Modifier = Modifier,
    collapseContentDescription: String = "طي",
    expandContentDescription: String = "توسيع",
    content: @Composable () -> Unit
) {
    val colors = MeayarTheme.colors
    val typography = MeayarTheme.typography
    val shape = RoundedCornerShape(10.dp)

    val rotationAngle by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f,
        label = "accordion_chevron"
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape)
            .background(colors.backgroundSurface)
            .border(BorderStroke(1.dp, colors.borderSubtle), shape)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onExpandedChange(!expanded) }
                .padding(horizontal = 16.dp, vertical = 14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = typography.textMdMedium,
                color = colors.textPrimary,
                modifier = Modifier.weight(1f)
            )

            Icon(
                imageVector = MeayarIcons.ChevronDown,
                contentDescription = if (expanded) collapseContentDescription else expandContentDescription,
                tint = colors.iconSecondary,
                modifier = Modifier
                    .size(20.dp)
                    .rotate(rotationAngle)
            )
        }

        AnimatedVisibility(
            visible = expanded,
            enter = expandVertically() + fadeIn(),
            exit = shrinkVertically() + fadeOut()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            ) {
                MeayarDivider(modifier = Modifier.padding(bottom = 12.dp))
                content()
            }
        }
    }
}

/**
 * Convenience overload of [MeayarAccordion] with internal state management.
 */
@Composable
fun MeayarAccordion(
    title: String,
    modifier: Modifier = Modifier,
    initiallyExpanded: Boolean = false,
    collapseContentDescription: String = "طي",
    expandContentDescription: String = "توسيع",
    content: @Composable () -> Unit
) {
    var expanded by remember { mutableStateOf(initiallyExpanded) }
    MeayarAccordion(
        expanded = expanded,
        onExpandedChange = { expanded = it },
        title = title,
        modifier = modifier,
        collapseContentDescription = collapseContentDescription,
        expandContentDescription = expandContentDescription,
        content = content
    )
}
