package com.devlomi.meayar.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.devlomi.meayar.theme.MeayarTheme

/**
 * Official MEAYAR Input / Text Field component.
 *
 * Features:
 * - Label with mandatory star (*) indicator
 * - Helper text or error message with semantic coloring
 * - Leading and trailing icon slots
 * - Direction-aware typing with [forceLtrContent] for phone numbers, IBANs, IDs, and URLs
 */
@Composable
fun MeayarTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    isMandatory: Boolean = false,
    placeholder: String? = null,
    helperText: String? = null,
    isError: Boolean = false,
    errorMessage: String? = null,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    forceLtrContent: Boolean = false,
    singleLine: Boolean = true,
    minLines: Int = 1,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    val colors = MeayarTheme.colors
    val typography = MeayarTheme.typography
    val isFocused by interactionSource.collectIsFocusedAsState()

    val borderColor = when {
        !enabled -> colors.borderSubtle
        isError -> colors.borderError
        isFocused -> colors.borderFocus
        else -> colors.borderDefault
    }

    val borderWidth = if (isFocused || isError) 1.5.dp else 1.dp
    val shape = RoundedCornerShape(6.dp)

    Column(modifier = modifier) {
        // Label with optional mandatory indicator
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

        // Input Container
        val containerModifier = if (singleLine) {
            Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 44.dp)
        } else {
            Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 88.dp)
        }

        Box(
            modifier = containerModifier
                .clip(shape)
                .background(if (enabled) colors.backgroundSurface else colors.backgroundDisabled)
                .border(borderWidth, borderColor, shape)
                .padding(horizontal = 14.dp, vertical = if (singleLine) 0.dp else 10.dp),
            contentAlignment = if (singleLine) Alignment.CenterStart else Alignment.TopStart
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = if (singleLine) Alignment.CenterVertically else Alignment.Top,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                if (leadingIcon != null) {
                    Box(
                        modifier = Modifier
                            .padding(top = if (singleLine) 0.dp else 2.dp)
                            .size(20.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        leadingIcon()
                    }
                }

                val isRtl = LocalLayoutDirection.current == LayoutDirection.Rtl
                val textDirection = if (forceLtrContent) {
                    TextDirection.Ltr
                } else if (isRtl) {
                    TextDirection.Rtl
                } else {
                    TextDirection.Ltr
                }
                val effectiveTextStyle = typography.textMd.copy(
                    color = if (enabled) colors.textPrimary else colors.textDisabled,
                    textDirection = textDirection
                )

                CompositionLocalProvider(
                    LocalLayoutDirection provides if (forceLtrContent) LayoutDirection.Ltr else LocalLayoutDirection.current
                ) {
                    BasicTextField(
                        value = value,
                        onValueChange = onValueChange,
                        modifier = Modifier.weight(1f),
                        enabled = enabled,
                        readOnly = readOnly,
                        textStyle = effectiveTextStyle,
                        cursorBrush = SolidColor(colors.textBrand),
                        keyboardOptions = keyboardOptions,
                        keyboardActions = keyboardActions,
                        visualTransformation = visualTransformation,
                        interactionSource = interactionSource,
                        singleLine = singleLine,
                        minLines = minLines,
                        maxLines = maxLines,
                        decorationBox = { innerTextField ->
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = if (singleLine) Alignment.CenterStart else Alignment.TopStart
                            ) {
                                if (value.isEmpty() && placeholder != null) {
                                    Text(
                                        text = placeholder,
                                        style = effectiveTextStyle.copy(color = colors.textTertiary),
                                        maxLines = if (singleLine) 1 else maxLines,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                                innerTextField()
                            }
                        }
                    )
                }

                if (trailingIcon != null) {
                    Box(
                        modifier = Modifier
                            .padding(top = if (singleLine) 0.dp else 2.dp)
                            .size(20.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        trailingIcon()
                    }
                }
            }
        }

        // Helper or Error Text
        val activeHelperText = if (isError) errorMessage ?: helperText else helperText
        if (!activeHelperText.isNullOrEmpty()) {
            Text(
                text = activeHelperText,
                style = typography.textXs,
                color = if (isError) colors.textError else colors.textTertiary,
                modifier = Modifier.padding(top = 4.dp, start = 4.dp, end = 4.dp)
            )
        }
    }
}
