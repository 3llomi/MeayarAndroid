package com.devlomi.meayar.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devlomi.meayar.theme.MeayarTheme

/**
 * Official MEAYAR OTP Input component for verification codes.
 * Enforces LTR layout direction so numeric codes flow cleanly left-to-right.
 */
@Composable
fun MeayarOtpInput(
    otpValue: String,
    onOtpChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    length: Int = 4,
    enabled: Boolean = true,
    isError: Boolean = false,
    onComplete: (() -> Unit)? = null
) {
    val colors = MeayarTheme.colors
    val typography = MeayarTheme.typography
    val focusRequester = remember { FocusRequester() }

    Box(
        modifier = modifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null
        ) { focusRequester.requestFocus() },
        contentAlignment = Alignment.Center
    ) {
        // Digit boxes displayed in LTR order
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(length) { index ->
                    val digit = otpValue.getOrNull(index)?.toString() ?: ""
                    val isCurrent = index == otpValue.length
                    val shape = RoundedCornerShape(8.dp)

                    val borderColor = when {
                        isError -> colors.borderError
                        isCurrent -> colors.borderFocus
                        digit.isNotEmpty() -> colors.borderBrand
                        else -> colors.borderDefault
                    }

                    val borderWidth = if (isCurrent || isError) 2.dp else 1.dp

                    Box(
                        modifier = Modifier
                            .size(width = 48.dp, height = 54.dp)
                            .clip(shape)
                            .background(if (enabled) colors.backgroundSurface else colors.backgroundDisabled)
                            .border(borderWidth, borderColor, shape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = digit,
                            style = typography.headingSm,
                            color = if (isError) colors.textError else colors.textPrimary
                        )
                    }
                }
            }
        }

        // Invisible text field covering the exact bounds for immediate keyboard input
        BasicTextField(
            value = otpValue,
            onValueChange = {
                if (it.length <= length && it.all { char -> char.isDigit() }) {
                    onOtpChange(it)
                    if (it.length == length) {
                        onComplete?.invoke()
                    }
                }
            },
            modifier = Modifier
                .matchParentSize()
                .alpha(0.01f)
                .focusRequester(focusRequester),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            keyboardActions = KeyboardActions(onDone = { onComplete?.invoke() }),
            textStyle = TextStyle(fontSize = 1.sp, color = androidx.compose.ui.graphics.Color.Transparent),
            cursorBrush = SolidColor(androidx.compose.ui.graphics.Color.Transparent),
            enabled = enabled
        )
    }
}

