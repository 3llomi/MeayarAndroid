package com.devlomi.meayar.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.selection.selectable
import androidx.compose.ui.semantics.Role
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.devlomi.meayar.theme.MeayarTheme

data class MeayarNavigationItem(
    val title: String,
    val icon: ImageVector,
    val badgeCount: Int? = null
)

/**
 * Mobile Bottom Navigation Bar for MEAYAR applications.
 * Anchored to the bottom with navigationBarsPadding and Syrian brand accenting.
 */
@Composable
fun MeayarNavigationBar(
    items: List<MeayarNavigationItem>,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    windowInsets: WindowInsets = WindowInsets.navigationBars
) {
    val colors = MeayarTheme.colors
    val typography = MeayarTheme.typography

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(colors.backgroundSurface)
            .windowInsetsPadding(windowInsets)
    ) {
        // Top divider border
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(colors.borderSubtle)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 64.dp)
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEachIndexed { index, item ->
                val isSelected = selectedIndex == index
                val itemColor by animateColorAsState(
                    if (isSelected) colors.textBrand else colors.textTertiary,
                    label = "nav_item_color"
                )

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(8.dp))
                        .selectable(
                            selected = isSelected,
                            role = Role.Tab,
                            onClick = { onItemSelected(index) }
                        )
                        .padding(vertical = 4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Box(contentAlignment = Alignment.TopEnd) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .background(if (isSelected) colors.backgroundBrandSubtle else androidx.compose.ui.graphics.Color.Transparent)
                                .padding(horizontal = 14.dp, vertical = 4.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = null,
                                tint = itemColor,
                                modifier = Modifier.size(22.dp)
                            )
                        }

                        if (item.badgeCount != null && item.badgeCount > 0) {
                            Box(
                                modifier = Modifier
                                    .size(16.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(colors.backgroundError),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = if (item.badgeCount > 99) "99+" else item.badgeCount.toString(),
                                    style = typography.text2xs,
                                    color = colors.textInverse
                                )
                            }
                        }
                    }

                    Text(
                        text = item.title,
                        style = if (isSelected) typography.textXsMedium else typography.textXs,
                        color = itemColor,
                        maxLines = 1
                    )
                }
            }
        }
    }
}
