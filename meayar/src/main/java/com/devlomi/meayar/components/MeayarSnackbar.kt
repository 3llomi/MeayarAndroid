package com.devlomi.meayar.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.devlomi.meayar.icons.MeayarIcons
import com.devlomi.meayar.theme.MeayarPrimitives
import com.devlomi.meayar.theme.MeayarTheme

/**
 * Visual styling variants for [MeayarSnackbar].
 */
enum class MeayarSnackbarStyle {
    /** Dark inverse background standard in Android Material UX */
    Inverse,
    /** Syrian Brand Primary deep green styling */
    Brand,
    /** Semantic subtle styles matching Toast tokens */
    Success,
    Error,
    Warning,
    Info
}

/**
 * Official MEAYAR Snackbar component for Android.
 *
 * Bridges Android's transient bottom notification paradigm with the MEAYAR Design System tokens.
 * Supports action buttons (e.g. "تراجع" / Undo), dismiss actions, and semantic status colorings.
 */
@Composable
fun MeayarSnackbar(
    message: String,
    modifier: Modifier = Modifier,
    actionLabel: String? = null,
    onActionClick: (() -> Unit)? = null,
    onDismissClick: (() -> Unit)? = null,
    dismissContentDescription: String = "إغلاق",
    icon: ImageVector? = null,
    style: MeayarSnackbarStyle = MeayarSnackbarStyle.Inverse
) {
    val colors = MeayarTheme.colors
    val typography = MeayarTheme.typography
    val shape = RoundedCornerShape(8.dp)

    val (bg, textColor, actionColor, defaultIcon) = when (style) {
        MeayarSnackbarStyle.Inverse -> SnackbarVisualTokens(
            colors.backgroundInverse,
            colors.textInverse,
            MeayarPrimitives.SyrianGold400, // High contrast Syrian Gold for action button
            null
        )
        MeayarSnackbarStyle.Brand -> SnackbarVisualTokens(
            colors.backgroundBrand,
            colors.textInverse,
            MeayarPrimitives.SyrianGold400,
            MeayarIcons.CheckCircle
        )
        MeayarSnackbarStyle.Success -> SnackbarVisualTokens(
            colors.backgroundSuccessSubtle,
            colors.textSuccess,
            colors.textBrand,
            MeayarIcons.CheckCircle
        )
        MeayarSnackbarStyle.Error -> SnackbarVisualTokens(
            colors.backgroundErrorSubtle,
            colors.textError,
            colors.textError,
            MeayarIcons.Warning
        )
        MeayarSnackbarStyle.Warning -> SnackbarVisualTokens(
            colors.backgroundWarningSubtle,
            colors.textWarning,
            colors.textWarning,
            MeayarIcons.Warning
        )
        MeayarSnackbarStyle.Info -> SnackbarVisualTokens(
            colors.backgroundInfoSubtle,
            colors.textInfo,
            colors.textBrand,
            MeayarIcons.Info
        )
    }

    val effectiveIcon = icon ?: defaultIcon

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .shadow(6.dp, shape)
            .clip(shape)
            .background(bg)
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                if (effectiveIcon != null) {
                    Icon(
                        imageVector = effectiveIcon,
                        contentDescription = null,
                        tint = if (style == MeayarSnackbarStyle.Inverse || style == MeayarSnackbarStyle.Brand) textColor else actionColor,
                        modifier = Modifier.size(20.dp)
                    )
                }

                Text(
                    text = message,
                    style = typography.textSmMedium,
                    color = textColor,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

            if (actionLabel != null && onActionClick != null) {
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = actionLabel,
                    style = typography.textSmBold,
                    color = actionColor,
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .clickable { onActionClick() }
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }

            if (onDismissClick != null) {
                Spacer(modifier = Modifier.width(4.dp))
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .clickable { onDismissClick() }
                        .padding(6.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = MeayarIcons.Close,
                        contentDescription = dismissContentDescription,
                        tint = textColor.copy(alpha = 0.7f),
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}

/**
 * Custom [SnackbarVisuals] carrying the explicit [MeayarSnackbarStyle] and optional [ImageVector] icon.
 */
data class MeayarSnackbarVisuals(
    override val message: String,
    override val actionLabel: String? = null,
    override val withDismissAction: Boolean = false,
    override val duration: SnackbarDuration = SnackbarDuration.Short,
    val style: MeayarSnackbarStyle = MeayarSnackbarStyle.Inverse,
    val icon: ImageVector? = null
) : SnackbarVisuals

/**
 * Convenience extension on [SnackbarHostState] to launch a styled MEAYAR snackbar
 * (e.g. [MeayarSnackbarStyle.Brand] or [MeayarSnackbarStyle.Inverse]).
 */
suspend fun SnackbarHostState.showMeayarSnackbar(
    message: String,
    actionLabel: String? = null,
    withDismissAction: Boolean = false,
    duration: SnackbarDuration = SnackbarDuration.Short,
    style: MeayarSnackbarStyle = MeayarSnackbarStyle.Inverse,
    icon: ImageVector? = null
): SnackbarResult {
    return showSnackbar(
        MeayarSnackbarVisuals(
            message = message,
            actionLabel = actionLabel,
            withDismissAction = withDismissAction,
            duration = duration,
            style = style,
            icon = icon
        )
    )
}

/**
 * Official MEAYAR Snackbar Host for use inside [androidx.compose.material3.Scaffold].
 *
 * Example:
 * ```kotlin
 * val snackbarHostState = remember { SnackbarHostState() }
 * Scaffold(
 *     snackbarHost = { MeayarSnackbarHost(snackbarHostState) }
 * ) { ... }
 * ```
 */
@Composable
fun MeayarSnackbarHost(
    hostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    style: MeayarSnackbarStyle = MeayarSnackbarStyle.Inverse
) {
    SnackbarHost(
        hostState = hostState,
        modifier = modifier
    ) { data: SnackbarData ->
        val visuals = data.visuals
        val resolvedStyle = if (visuals is MeayarSnackbarVisuals) visuals.style else style
        val resolvedIcon = if (visuals is MeayarSnackbarVisuals) visuals.icon else null

        MeayarSnackbar(
            message = visuals.message,
            actionLabel = visuals.actionLabel,
            onActionClick = { data.performAction() },
            onDismissClick = if (visuals.withDismissAction) { { data.dismiss() } } else null,
            style = resolvedStyle,
            icon = resolvedIcon
        )
    }
}

private data class SnackbarVisualTokens(
    val bg: Color,
    val textColor: Color,
    val actionColor: Color,
    val defaultIcon: androidx.compose.ui.graphics.vector.ImageVector?
)
