package com.devlomi.meayar.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.devlomi.meayar.icons.MeayarIcons
import com.devlomi.meayar.theme.MeayarTheme

/**
 * Standard MEAYAR Card container for content modules.
 */
@Composable
fun MeayarCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    val colors = MeayarTheme.colors
    val shape = RoundedCornerShape(12.dp)

    Box(
        modifier = modifier
            .clip(shape)
            .background(colors.backgroundSurface)
            .border(BorderStroke(1.dp, colors.borderSubtle), shape)
            .then(if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier)
            .padding(16.dp)
    ) {
        content()
    }
}

/**
 * Meta detail item for Syrian public services (e.g. Duration, Fee, Delivery Channel).
 */
data class MeayarServiceMeta(
    val icon: ImageVector,
    val label: String,
    val value: String
)

/**
 * Official MEAYAR Service Card (بطاقة الخدمة الحكومية).
 * Specifically designed for Syrian digital service portals:
 * Displays title, description, metadata (duration, fee, channel), status tag,
 * and primary "ابدأ الخدمة" action button with optional details link.
 */
@Composable
fun MeayarServiceCard(
    title: String,
    description: String,
    onStartService: () -> Unit,
    modifier: Modifier = Modifier,
    onViewDetails: (() -> Unit)? = null,
    startServiceText: String = "ابدأ الخدمة",
    viewDetailsText: String = "تفاصيل الخدمة",
    tagText: String? = null,
    tagColor: MeayarTagColor = MeayarTagColor.Primary,
    metaList: List<MeayarServiceMeta> = emptyList(),
    icon: ImageVector? = null
) {
    val colors = MeayarTheme.colors
    val typography = MeayarTheme.typography
    val shape = RoundedCornerShape(12.dp)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape)
            .background(colors.backgroundSurface)
            .border(BorderStroke(1.dp, colors.borderSubtle), shape)
            .padding(20.dp)
    ) {
        // Top Header: Icon & Tag
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (icon != null) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(colors.backgroundBrandSubtle),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = colors.iconBrand,
                        modifier = Modifier.size(24.dp)
                    )
                }
            } else {
                Spacer(modifier = Modifier.size(1.dp))
            }

            if (tagText != null) {
                MeayarTag(
                    text = tagText,
                    color = tagColor,
                    hierarchy = MeayarTagHierarchy.Secondary
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Title & Description
        Text(
            text = title,
            style = typography.heading2xs,
            color = colors.textPrimary
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = description,
            style = typography.textSm,
            color = colors.textSecondary
        )

        // Metadata: Fee, Duration, Channel
        if (metaList.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(colors.backgroundSubtle)
                    .padding(horizontal = 12.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                metaList.forEach { meta ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            imageVector = meta.icon,
                            contentDescription = null,
                            tint = colors.iconSecondary,
                            modifier = Modifier.size(16.dp)
                        )
                        Column {
                            Text(
                                text = meta.label,
                                style = typography.text2xs,
                                color = colors.textTertiary
                            )
                            Text(
                                text = meta.value,
                                style = typography.textXsMedium,
                                color = colors.textPrimary
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Action Buttons: Primary Start + Secondary Details
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            MeayarButton(
                onClick = onStartService,
                modifier = Modifier.weight(1f),
                size = MeayarButtonSize.MD,
                variant = MeayarButtonVariant.Primary
            ) {
                Text(text = startServiceText)
            }

            if (onViewDetails != null) {
                MeayarButton(
                    onClick = onViewDetails,
                    size = MeayarButtonSize.MD,
                    variant = MeayarButtonVariant.Ghost
                ) {
                    Text(text = viewDetailsText)
                }
            }
        }
    }
}
