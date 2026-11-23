package com.devlomi.meayar.components

import android.view.View
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.DialogWindowProvider
import com.devlomi.meayar.theme.MeayarTheme

/**
 * Official MEAYAR Modal Dialog component.
 *
 * Prevents LTR flicker by synchronizing the host DialogWindow and DecorView layout direction
 * with [LocalLayoutDirection] from the initial rendering frame.
 */
@Composable
fun MeayarDialog(
    onDismissRequest: () -> Unit,
    title: String,
    onConfirm: () -> Unit,
    modifier: Modifier = Modifier,
    description: String? = null,
    confirmText: String = "تأكيد",
    cancelText: String? = "إلغاء",
    onCancel: (() -> Unit)? = onDismissRequest,
    icon: ImageVector? = null,
    isDestructive: Boolean = false,
    properties: DialogProperties = DialogProperties(),
    content: (@Composable () -> Unit)? = null
) {
    val colors = MeayarTheme.colors
    val typography = MeayarTheme.typography
    val shape = RoundedCornerShape(16.dp)
    val currentLayoutDirection = LocalLayoutDirection.current

    Dialog(
        onDismissRequest = onDismissRequest,
        properties = properties
    ) {
        val dialogView = LocalView.current
        DisposableEffect(currentLayoutDirection, dialogView) {
            val window = (dialogView.parent as? DialogWindowProvider)?.window
            val viewDirection = when (currentLayoutDirection) {
                LayoutDirection.Rtl -> View.LAYOUT_DIRECTION_RTL
                LayoutDirection.Ltr -> View.LAYOUT_DIRECTION_LTR
            }
            window?.decorView?.layoutDirection = viewDirection
            dialogView.layoutDirection = viewDirection
            onDispose {}
        }

        CompositionLocalProvider(
            LocalLayoutDirection provides currentLayoutDirection
        ) {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .clip(shape)
                    .background(colors.backgroundSurface)
                    .border(1.dp, colors.borderSubtle, shape)
                    .padding(24.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    if (icon != null) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(if (isDestructive) colors.backgroundErrorSubtle else colors.backgroundBrandSubtle),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = icon,
                                contentDescription = null,
                                tint = if (isDestructive) colors.textError else colors.iconBrand,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    Text(
                        text = title,
                        style = typography.heading2xs,
                        color = colors.textPrimary
                    )

                    if (description != null) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = description,
                            style = typography.textSm,
                            color = colors.textSecondary
                        )
                    }

                    if (content != null) {
                        Spacer(modifier = Modifier.height(16.dp))
                        content()
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        MeayarButton(
                            onClick = onConfirm,
                            modifier = Modifier.weight(1f),
                            size = MeayarButtonSize.MD,
                            variant = if (isDestructive) MeayarButtonVariant.Destructive else MeayarButtonVariant.Primary
                        ) {
                            Text(text = confirmText)
                        }

                        if (cancelText != null && onCancel != null) {
                            MeayarButton(
                                onClick = onCancel,
                                modifier = Modifier.weight(1f),
                                size = MeayarButtonSize.MD,
                                variant = MeayarButtonVariant.Outlined
                            ) {
                                Text(text = cancelText)
                            }
                        }
                    }
                }
            }
        }
    }
}
