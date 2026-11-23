package com.devlomi.meayar.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.devlomi.meayar.icons.MeayarIcons
import com.devlomi.meayar.theme.MeayarTheme

/**
 * Mobile Top App Bar tailored for MEAYAR Android applications.
 * Automatically mirrors the back icon for Arabic RTL / English LTR layouts
 * and handles status bar window insets.
 */
@Composable
fun MeayarTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    onNavigationClick: (() -> Unit)? = null,
    navigationIcon: (@Composable () -> Unit)? = null,
    navigationContentDescription: String = "رجوع",
    windowInsets: WindowInsets = WindowInsets.statusBars,
    actions: @Composable RowScope.() -> Unit = {}
) {
    val colors = MeayarTheme.colors
    val typography = MeayarTheme.typography
    val isRtl = LocalLayoutDirection.current == LayoutDirection.Rtl

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(colors.backgroundSurface)
            .windowInsetsPadding(windowInsets)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 56.dp)
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Navigation icon (e.g. Back button)
            if (onNavigationClick != null) {
                IconButton(onClick = onNavigationClick) {
                    if (navigationIcon != null) {
                        navigationIcon()
                    } else {
                        // Default back arrow with automatic RTL direction flip
                        Icon(
                            imageVector = MeayarIcons.ArrowBack,
                            contentDescription = navigationContentDescription,
                            tint = colors.iconPrimary,
                            modifier = Modifier.scale(scaleX = if (isRtl) -1f else 1f, scaleY = 1f)
                        )
                    }
                }
            } else {
                Spacer(modifier = Modifier.size(8.dp))
            }

            // Title & optional Subtitle
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp)
            ) {
                Text(
                    text = title,
                    style = typography.heading2xs,
                    color = colors.textPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                if (subtitle != null) {
                    Text(
                        text = subtitle,
                        style = typography.textXs,
                        color = colors.textTertiary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            // Action Items Slot
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                content = actions
            )
        }

        // Subtle bottom border
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(colors.borderSubtle)
        )
    }
}
