package com.devlomi.meayar.components

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.devlomi.meayar.icons.MeayarIcons
import com.devlomi.meayar.theme.MeayarTheme

/**
 * Official MEAYAR Cookie Consent & Privacy Notice Banner component.
 * - Cookie Consent/Background/default: colors.backgroundSurface
 * - Cookie Consent/Border/default: colors.borderSubtle
 * - Cookie Consent/Text/title: colors.textPrimary
 * - Cookie Consent/Text/body: colors.textSecondary
 * - Cookie Consent/Icon/default: colors.iconBrand
 * - Cookie Consent/Background/icon: colors.backgroundBrandSubtle
 */
@Composable
fun MeayarCookieConsent(
    onAcceptAll: () -> Unit,
    modifier: Modifier = Modifier,
    title: String = "إشعار الخصوصية وملفات تعريف الارتباط",
    description: String = "تستخدم بوابة معيار للخدمات الحكومية ملفات تعريف الارتباط الأساسية لضمان أمان الجلسات وتقديم الخدمات الرقمية الموثوقة وفق معايير الخصوصية الوطنية.",
    acceptText: String = "قبول الكل",
    customizeText: String? = "تخصيص الإعدادات",
    onCustomize: (() -> Unit)? = null,
    declineText: String? = "الضرورية فقط",
    onDecline: (() -> Unit)? = null,
    icon: ImageVector = MeayarIcons.Shield,
    isFloating: Boolean = true
) {
    val colors = MeayarTheme.colors
    val typography = MeayarTheme.typography
    val shape = RoundedCornerShape(12.dp)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .then(if (isFloating) Modifier.shadow(8.dp, shape) else Modifier)
            .clip(shape)
            .background(colors.backgroundSurface)
            .border(BorderStroke(1.dp, colors.borderSubtle), shape)
            .semantics(mergeDescendants = true) {
                contentDescription = "$title: $description"
            }
            .padding(18.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                // Syrian brand icon badge
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

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = title,
                        style = typography.heading2xs,
                        color = colors.textPrimary
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = description,
                        style = typography.textSm,
                        color = colors.textSecondary
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Action Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                MeayarButton(
                    onClick = onAcceptAll,
                    size = MeayarButtonSize.SM,
                    variant = MeayarButtonVariant.Primary
                ) {
                    Text(text = acceptText)
                }

                if (customizeText != null && onCustomize != null) {
                    MeayarButton(
                        onClick = onCustomize,
                        size = MeayarButtonSize.SM,
                        variant = MeayarButtonVariant.Outlined
                    ) {
                        Text(text = customizeText)
                    }
                }

                if (declineText != null && onDecline != null) {
                    MeayarButton(
                        onClick = onDecline,
                        size = MeayarButtonSize.SM,
                        variant = MeayarButtonVariant.Ghost
                    ) {
                        Text(text = declineText)
                    }
                }
            }
        }
    }
}
