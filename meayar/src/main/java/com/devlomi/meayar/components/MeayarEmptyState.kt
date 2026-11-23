package com.devlomi.meayar.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.devlomi.meayar.icons.MeayarIcons
import com.devlomi.meayar.theme.MeayarTheme

enum class MeayarEmptyStateType {
    NoData,
    NoResults,
    Error
}

/**
 * Official MEAYAR Empty State component.
 */
@Composable
fun MeayarEmptyState(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    type: MeayarEmptyStateType = MeayarEmptyStateType.NoData,
    icon: ImageVector? = null,
    actionText: String? = null,
    onActionClick: (() -> Unit)? = null,
    secondaryActionText: String? = null,
    onSecondaryActionClick: (() -> Unit)? = null
) {
    val colors = MeayarTheme.colors
    val typography = MeayarTheme.typography

    val effectiveIcon = icon ?: when (type) {
        MeayarEmptyStateType.NoData -> MeayarIcons.Document
        MeayarEmptyStateType.NoResults -> MeayarIcons.Search
        MeayarEmptyStateType.Error -> MeayarIcons.Warning
    }

    val iconBg = when (type) {
        MeayarEmptyStateType.Error -> colors.backgroundErrorSubtle
        else -> colors.backgroundBrandSubtle
    }

    val iconTint = when (type) {
        MeayarEmptyStateType.Error -> colors.textError
        else -> colors.iconBrand
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(72.dp)
                .clip(CircleShape)
                .background(iconBg),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = effectiveIcon,
                contentDescription = null,
                tint = iconTint,
                modifier = Modifier.size(36.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = title,
            style = typography.heading2xs,
            color = colors.textPrimary,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = description,
            style = typography.textSm,
            color = colors.textSecondary,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        if (actionText != null && onActionClick != null) {
            Spacer(modifier = Modifier.height(20.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                MeayarButton(
                    onClick = onActionClick,
                    size = MeayarButtonSize.MD,
                    variant = MeayarButtonVariant.Primary
                ) {
                    Text(text = actionText)
                }

                if (secondaryActionText != null && onSecondaryActionClick != null) {
                    MeayarButton(
                        onClick = onSecondaryActionClick,
                        size = MeayarButtonSize.MD,
                        variant = MeayarButtonVariant.Ghost
                    ) {
                        Text(text = secondaryActionText)
                    }
                }
            }
        }
    }
}
