package com.devlomi.meayar.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.selection.toggleable
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.unit.dp
import com.devlomi.meayar.icons.MeayarIcons
import com.devlomi.meayar.theme.MeayarTheme

/**
 * Official MEAYAR Accessibility Toolbar component.
 *
 * Mandatory requirement for official Syrian e-government portals to support
 * citizens with visual impairments, elder citizens, and screen reader users.
 */
@Composable
fun MeayarAccessibilityToolbar(
    onFontScaleDecrease: () -> Unit,
    onFontScaleReset: () -> Unit,
    onFontScaleIncrease: () -> Unit,
    isHighContrast: Boolean,
    onToggleHighContrast: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    titleText: String = "سهولة الاستخدام:",
    decreaseFontContentDescription: String = "تصغير حجم الخط",
    resetFontContentDescription: String = "إعادة ضبط حجم الخط الافتراضي",
    increaseFontContentDescription: String = "تكبير حجم الخط",
    highContrastContentDescription: String = "نمط التباين العالي",
    highContrastLabel: String = "تباين عالي"
) {
    val colors = MeayarTheme.colors
    val typography = MeayarTheme.typography
    val shape = RoundedCornerShape(8.dp)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape)
            .background(colors.backgroundSubtle)
            .border(1.dp, colors.borderSubtle, shape)
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Label
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = MeayarIcons.Info,
                    contentDescription = null,
                    tint = colors.iconBrand,
                    modifier = Modifier.size(18.dp)
                )
                Text(
                    text = titleText,
                    style = typography.textXsMedium,
                    color = colors.textPrimary
                )
            }

            // Controls Row
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                // A- (Decrease)
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(colors.backgroundSurface)
                        .border(1.dp, colors.borderSubtle, RoundedCornerShape(6.dp))
                        .semantics(mergeDescendants = true) {
                            contentDescription = decreaseFontContentDescription
                            role = Role.Button
                        }
                        .clickable(role = Role.Button) { onFontScaleDecrease() },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "A-",
                        style = typography.textXsMedium,
                        color = colors.textPrimary
                    )
                }

                // A (Reset)
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(colors.backgroundSurface)
                        .border(1.dp, colors.borderSubtle, RoundedCornerShape(6.dp))
                        .semantics(mergeDescendants = true) {
                            contentDescription = resetFontContentDescription
                            role = Role.Button
                        }
                        .clickable(role = Role.Button) { onFontScaleReset() },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "A",
                        style = typography.textSmBold,
                        color = colors.textPrimary
                    )
                }

                // A+ (Increase)
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(colors.backgroundSurface)
                        .border(1.dp, colors.borderSubtle, RoundedCornerShape(6.dp))
                        .semantics(mergeDescendants = true) {
                            contentDescription = increaseFontContentDescription
                            role = Role.Button
                        }
                        .clickable(role = Role.Button) { onFontScaleIncrease() },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "A+",
                        style = typography.textMdBold,
                        color = colors.textPrimary
                    )
                }

                Spacer(modifier = Modifier.width(4.dp))
                MeayarVerticalDivider(modifier = Modifier.height(20.dp))
                Spacer(modifier = Modifier.width(4.dp))

                // High Contrast Toggle
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(if (isHighContrast) colors.backgroundBrand else colors.backgroundSurface)
                        .border(1.dp, if (isHighContrast) colors.borderBrand else colors.borderSubtle, RoundedCornerShape(6.dp))
                        .semantics(mergeDescendants = true) {
                            contentDescription = highContrastContentDescription
                            role = Role.Switch
                        }
                        .toggleable(
                            value = isHighContrast,
                            role = Role.Switch,
                            onValueChange = { onToggleHighContrast(it) }
                        )
                        .padding(horizontal = 8.dp, vertical = 6.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = highContrastLabel,
                        style = typography.text2xsMedium,
                        color = if (isHighContrast) colors.textInverse else colors.textPrimary
                    )
                }
            }
        }
    }
}
