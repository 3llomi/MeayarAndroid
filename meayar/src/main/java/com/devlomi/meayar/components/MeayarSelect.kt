package com.devlomi.meayar.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import com.devlomi.meayar.icons.MeayarIcons
import com.devlomi.meayar.theme.MeayarTheme

/**
 * Official MEAYAR Select / Dropdown component.
 */
@Composable
fun <T> MeayarSelect(
    items: List<T>,
    selectedItem: T?,
    onItemSelected: (T) -> Unit,
    itemLabel: (T) -> String,
    modifier: Modifier = Modifier,
    label: String? = null,
    isMandatory: Boolean = false,
    placeholder: String = "اختر من القائمة...",
    selectedItemContentDescription: String = "محدد",
    enabled: Boolean = true,
    isError: Boolean = false,
    errorMessage: String? = null
) {
    val colors = MeayarTheme.colors
    val typography = MeayarTheme.typography
    var expanded by remember { mutableStateOf(false) }

    val chevronRotation by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f,
        label = "select_chevron"
    )

    val shape = RoundedCornerShape(6.dp)
    val borderColor = when {
        !enabled -> colors.borderSubtle
        isError -> colors.borderError
        expanded -> colors.borderFocus
        else -> colors.borderDefault
    }
    val borderWidth = if (expanded || isError) 1.5.dp else 1.dp

    Column(modifier = modifier) {
        if (label != null) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 6.dp)
            ) {
                Text(
                    text = label,
                    style = typography.textSmMedium,
                    color = if (enabled) colors.textSecondary else colors.textDisabled
                )
                if (isMandatory) {
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "*",
                        style = typography.textSmMedium,
                        color = colors.textError
                    )
                }
            }
        }

        BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
            val popupWidth = maxWidth
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 44.dp)
                    .clip(shape)
                    .background(if (enabled) colors.backgroundSurface else colors.backgroundDisabled)
                    .border(borderWidth, borderColor, shape)
                    .clickable(enabled = enabled) { expanded = !expanded }
                    .padding(horizontal = 14.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (selectedItem != null) itemLabel(selectedItem) else placeholder,
                    style = typography.textMd,
                    color = if (selectedItem != null) colors.textPrimary else colors.textTertiary,
                    modifier = Modifier.weight(1f)
                )

                Icon(
                    imageVector = MeayarIcons.ChevronDown,
                    contentDescription = null,
                    tint = colors.iconSecondary,
                    modifier = Modifier
                        .size(20.dp)
                        .rotate(chevronRotation)
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .width(popupWidth)
                    .background(colors.backgroundSurface)
                    .border(1.dp, colors.borderSubtle, RoundedCornerShape(8.dp))
            ) {
                items.forEach { item ->
                    val isSelected = item == selectedItem
                    DropdownMenuItem(
                        text = {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = itemLabel(item),
                                    style = if (isSelected) typography.textSmBold else typography.textSm,
                                    color = if (isSelected) colors.textBrand else colors.textPrimary
                                )
                                if (isSelected) {
                                    Icon(
                                        imageVector = MeayarIcons.Check,
                                        contentDescription = selectedItemContentDescription,
                                        tint = colors.iconBrand,
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                            }
                        },
                        onClick = {
                            onItemSelected(item)
                            expanded = false
                        },
                        modifier = Modifier.background(
                            if (isSelected) colors.backgroundBrandSubtle.copy(alpha = 0.5f) else colors.backgroundSurface
                        )
                    )
                }
            }
        }

        if (isError && errorMessage != null) {
            Text(
                text = errorMessage,
                style = typography.textXs,
                color = colors.textError,
                modifier = Modifier.padding(top = 4.dp, start = 4.dp)
            )
        }
    }
}

/**
 * Convenient overload of [MeayarSelect] for simple [String] lists.
 */
@Composable
fun MeayarSelect(
    items: List<String>,
    selectedItem: String?,
    onItemSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    isMandatory: Boolean = false,
    placeholder: String = "اختر من القائمة...",
    selectedItemContentDescription: String = "محدد",
    enabled: Boolean = true,
    isError: Boolean = false,
    errorMessage: String? = null
) {
    MeayarSelect(
        items = items,
        selectedItem = selectedItem,
        onItemSelected = onItemSelected,
        itemLabel = { it },
        modifier = modifier,
        label = label,
        isMandatory = isMandatory,
        placeholder = placeholder,
        selectedItemContentDescription = selectedItemContentDescription,
        enabled = enabled,
        isError = isError,
        errorMessage = errorMessage
    )
}

