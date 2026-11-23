package com.devlomi.meayar.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.devlomi.meayar.icons.MeayarIcons
import com.devlomi.meayar.theme.MeayarTheme

enum class MeayarToastType {
    Success,
    Error,
    Warning,
    Info
}

/**
 * Official MEAYAR Toast / Floating notification component.
 * - Success: --meayar-semantic-background-success-subtle & text-success
 * - Error:   --meayar-semantic-background-error-subtle & text-error
 * - Warning: --meayar-semantic-background-warning-subtle & text-warning
 * - Info:    --meayar-semantic-background-info-subtle & text-info
 */
@Composable
fun MeayarToast(
    title: String,
    modifier: Modifier = Modifier,
    description: String? = null,
    type: MeayarToastType = MeayarToastType.Success,
    closeContentDescription: String = "إغلاق",
    onClose: (() -> Unit)? = null
) {
    val colors = MeayarTheme.colors
    val typography = MeayarTheme.typography

    val (bgSubtle, borderCol, iconVector, textCol) = when (type) {
        MeayarToastType.Success -> ToastVisualTokens(
            colors.backgroundSuccessSubtle,
            colors.textSuccess.copy(alpha = 0.3f),
            MeayarIcons.CheckCircle,
            colors.textSuccess
        )
        MeayarToastType.Error -> ToastVisualTokens(
            colors.backgroundErrorSubtle,
            colors.borderError.copy(alpha = 0.4f),
            MeayarIcons.Warning,
            colors.textError
        )
        MeayarToastType.Warning -> ToastVisualTokens(
            colors.backgroundWarningSubtle,
            colors.textWarning.copy(alpha = 0.3f),
            MeayarIcons.Warning,
            colors.textWarning
        )
        MeayarToastType.Info -> ToastVisualTokens(
            colors.backgroundInfoSubtle,
            colors.textInfo.copy(alpha = 0.3f),
            MeayarIcons.Info,
            colors.textInfo
        )
    }

    val shape = RoundedCornerShape(8.dp)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .shadow(4.dp, shape)
            .clip(shape)
            .background(bgSubtle)
            .border(BorderStroke(1.dp, borderCol), shape)
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = iconVector,
                contentDescription = null,
                tint = textCol,
                modifier = Modifier.size(20.dp)
            )

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = typography.textSmBold,
                    color = textCol
                )
                if (description != null) {
                    Text(
                        text = description,
                        style = typography.textXs,
                        color = colors.textSecondary,
                        modifier = Modifier.padding(top = 2.dp)
                    )
                }
            }

            if (onClose != null) {
                IconButton(
                    onClick = onClose,
                    modifier = Modifier.size(28.dp)
                ) {
                    Icon(
                        imageVector = MeayarIcons.Close,
                        contentDescription = closeContentDescription,
                        tint = colors.iconSecondary,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}

private data class ToastVisualTokens(
    val bgSubtle: Color,
    val borderCol: Color,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val accentCol: Color
)
