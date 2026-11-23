package com.devlomi.meayar.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.devlomi.meayar.theme.MeayarTheme

/**
 * Definition of a single column in [MeayarTable].
 */
data class MeayarColumn<T>(
    val title: String,
    val width: Dp = 120.dp,
    val alignment: Alignment = Alignment.CenterStart,
    val content: @Composable (item: T) -> Unit
)

/**
 * Official MEAYAR Data Table component.
 *
 * Features:
 * - Syrian state table styling with rounded border container
 * - Subtly tinted header row with high contrast typography
 * - Alternating zebra striping
 * - Responsive horizontal scrolling for mobile screens
 */
@Composable
fun <T> MeayarTable(
    columns: List<MeayarColumn<T>>,
    items: List<T>,
    modifier: Modifier = Modifier,
    emptyContent: (@Composable () -> Unit)? = null
) {
    val colors = MeayarTheme.colors
    val typography = MeayarTheme.typography
    val shape = RoundedCornerShape(8.dp)
    val scrollState = rememberScrollState()

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape)
            .border(1.dp, colors.borderSubtle, shape)
            .background(colors.backgroundSurface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(scrollState)
        ) {
            // Header Row
            Row(
                modifier = Modifier
                    .background(colors.backgroundSubtle)
                    .padding(vertical = 12.dp, horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                columns.forEach { col ->
                    Box(
                        modifier = Modifier.width(col.width),
                        contentAlignment = col.alignment
                    ) {
                        Text(
                            text = col.title,
                            style = typography.textXsMedium,
                            color = colors.textSecondary
                        )
                    }
                }
            }

            MeayarDivider()

            // Body Rows
            if (items.isEmpty() && emptyContent != null) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    emptyContent()
                }
            } else {
                items.forEachIndexed { index, item ->
                    val isEven = index % 2 == 0
                    val rowBg = if (isEven) colors.backgroundSurface else colors.backgroundSubtle.copy(alpha = 0.4f)

                    Row(
                        modifier = Modifier
                            .background(rowBg)
                            .padding(vertical = 14.dp, horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        columns.forEach { col ->
                            Box(
                                modifier = Modifier.width(col.width),
                                contentAlignment = col.alignment
                            ) {
                                col.content(item)
                            }
                        }
                    }

                    if (index < items.size - 1) {
                        MeayarDivider(color = colors.borderSubtle.copy(alpha = 0.5f))
                    }
                }
            }
        }
    }
}
