package com.devlomi.meayar.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import com.devlomi.meayar.theme.MeayarPrimitives
import com.devlomi.meayar.theme.MeayarTheme

enum class MeayarAvatarSize(val size: Dp, val indicatorSize: Dp) {
    XS(24.dp, 6.dp),
    SM(32.dp, 8.dp),
    MD(40.dp, 10.dp),
    LG(48.dp, 12.dp),
    XL(56.dp, 14.dp)
}

enum class MeayarPresenceStatus {
    Online,
    Busy,
    Offline
}

/**
 * Official MEAYAR Avatar component.
 * Displays user initials with brand background and optional presence badge.
 */
@Composable
fun MeayarAvatar(
    name: String,
    modifier: Modifier = Modifier,
    painter: Painter? = null,
    size: MeayarAvatarSize = MeayarAvatarSize.MD,
    status: MeayarPresenceStatus? = null,
    onlineStatusText: String = "متصل",
    busyStatusText: String = "مشغول",
    offlineStatusText: String = "غير متصل"
) {
    val colors = MeayarTheme.colors
    val typography = MeayarTheme.typography

    // Extract initials (first letters of words, e.g. "أحمد علي" -> "أ ع")
    val initials = remember(name) {
        name.trim().split(" ")
            .filter { it.isNotEmpty() }
            .take(2)
            .map { it.first() }
            .joinToString(" ")
    }

    val textStyle = when (size) {
        MeayarAvatarSize.XS -> typography.text2xs
        MeayarAvatarSize.SM -> typography.textXs
        MeayarAvatarSize.MD -> typography.textSmMedium
        MeayarAvatarSize.LG -> typography.textMdMedium
        MeayarAvatarSize.XL -> typography.heading2xs
    }

    val statusDesc = when (status) {
        MeayarPresenceStatus.Online -> onlineStatusText
        MeayarPresenceStatus.Busy -> busyStatusText
        MeayarPresenceStatus.Offline -> offlineStatusText
        null -> null
    }

    Box(
        modifier = modifier
            .size(size.size)
            .semantics(mergeDescendants = true) {
                contentDescription = if (statusDesc != null) "$name، $statusDesc" else name
            },
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(size.size)
                .clip(CircleShape)
                .background(colors.backgroundBrandSubtle),
            contentAlignment = Alignment.Center
        ) {
            if (painter != null) {
                Image(
                    painter = painter,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                Text(
                    text = initials,
                    style = textStyle,
                    color = colors.textBrand
                )
            }
        }

        if (status != null) {
            val statusColor = when (status) {
                MeayarPresenceStatus.Online -> MeayarPrimitives.Green600
                MeayarPresenceStatus.Busy -> MeayarPrimitives.Red600
                MeayarPresenceStatus.Offline -> MeayarPrimitives.Neutral300
            }

            Box(
                modifier = Modifier
                    .size(size.indicatorSize)
                    .align(Alignment.BottomEnd)
                    .clip(CircleShape)
                    .background(statusColor)
                    .border(1.5.dp, colors.backgroundSurface, CircleShape)
            )
        }
    }
}
