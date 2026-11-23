package com.devlomi.meayar.components

import android.icu.util.Calendar as IcuCalendar
import android.icu.util.IslamicCalendar
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.dp
import com.devlomi.meayar.icons.MeayarIcons
import com.devlomi.meayar.theme.MeayarTheme
import java.text.SimpleDateFormat
import java.util.Calendar as JCalendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

/**
 * Official MEAYAR Date Picker component.
 *
 * Provides a Syrian-styled date selector field that opens a dialog picker
 * built on the official [MeayarCalendar].
 *
 * Supports both [MeayarCalendarType.Gregorian] and [MeayarCalendarType.Hijri],
 * dynamic device or custom [Locale] formatting, full RTL layout, and accessible validation states.
 */
@Composable
fun MeayarDatePicker(
    selectedDateMillis: Long?,
    onDateSelected: (Long?) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    isMandatory: Boolean = false,
    placeholder: String = "اختر التاريخ (يوم / شهر / سنة)...",
    dialogTitle: String? = null,
    confirmButtonText: String = "تأكيد",
    cancelButtonText: String = "إلغاء",
    enabled: Boolean = true,
    isError: Boolean = false,
    errorMessage: String? = null,
    calendarType: MeayarCalendarType = MeayarCalendarType.Gregorian,
    locale: Locale = Locale.getDefault()
) {
    val colors = MeayarTheme.colors
    val typography = MeayarTheme.typography
    var showDialog by remember { mutableStateOf(false) }

    val shape = RoundedCornerShape(6.dp)
    val borderColor = when {
        !enabled -> colors.borderSubtle
        isError -> colors.borderError
        showDialog -> colors.borderFocus
        else -> colors.borderDefault
    }

    val formattedDate = remember(selectedDateMillis, calendarType, locale) {
        selectedDateMillis?.let { millis ->
            when (calendarType) {
                MeayarCalendarType.Gregorian -> {
                    val formatter = SimpleDateFormat("yyyy/MM/dd", locale).apply {
                        timeZone = TimeZone.getTimeZone("UTC")
                    }
                    formatter.format(Date(millis))
                }
                MeayarCalendarType.Hijri -> {
                    val icuCal = IslamicCalendar(locale).apply {
                        timeZone = android.icu.util.TimeZone.getTimeZone("UTC")
                        timeInMillis = millis
                    }
                    val y = icuCal.get(IcuCalendar.YEAR)
                    val m = icuCal.get(IcuCalendar.MONTH) + 1
                    val d = icuCal.get(IcuCalendar.DAY_OF_MONTH)
                    "$y/${m.toString().padStart(2, '0')}/${d.toString().padStart(2, '0')} هـ"
                }
            }
        }
    }

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

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 44.dp)
                .clip(shape)
                .background(if (enabled) colors.backgroundSurface else colors.backgroundDisabled)
                .border(if (showDialog || isError) 1.5.dp else 1.dp, borderColor, shape)
                .clickable(enabled = enabled) { showDialog = true }
                .padding(horizontal = 14.dp, vertical = 10.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Icon(
                        imageVector = MeayarIcons.Calendar,
                        contentDescription = null,
                        tint = colors.iconSecondary,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = formattedDate ?: placeholder,
                        style = typography.textMd,
                        color = if (formattedDate != null) colors.textPrimary else colors.textTertiary
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

    if (showDialog) {
        val defaultMidnight = remember {
            JCalendar.getInstance(TimeZone.getTimeZone("UTC")).apply {
                set(JCalendar.HOUR_OF_DAY, 0)
                set(JCalendar.MINUTE, 0)
                set(JCalendar.SECOND, 0)
                set(JCalendar.MILLISECOND, 0)
            }.timeInMillis
        }
        var tempSelectedDateMillis by remember(selectedDateMillis) {
            mutableStateOf<Long?>(selectedDateMillis ?: defaultMidnight)
        }

        MeayarDialog(
            onDismissRequest = { showDialog = false },
            title = dialogTitle ?: if (calendarType == MeayarCalendarType.Hijri) "اختيار التاريخ الهجري" else "اختيار التاريخ",
            confirmText = confirmButtonText,
            cancelText = cancelButtonText,
            onConfirm = {
                onDateSelected(tempSelectedDateMillis)
                showDialog = false
            },
            onCancel = { showDialog = false },
            icon = MeayarIcons.Calendar
        ) {
            MeayarCalendar(
                selectedDateMillis = tempSelectedDateMillis,
                onDateSelected = { tempSelectedDateMillis = it },
                calendarType = calendarType,
                locale = locale,
                showBorder = false
            )
        }
    }
}

/**
 * Convenient overload of [MeayarDatePicker] accepting year, month (0-based), and day integers.
 */
@Composable
fun MeayarDatePicker(
    selectedYear: Int?,
    selectedMonth: Int?, // 0-based: 0 = January/Muharram, 11 = December/Dhu al-Hijjah
    selectedDay: Int?,
    onDateSelected: (year: Int, month: Int, day: Int) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    isMandatory: Boolean = false,
    placeholder: String = "اختر التاريخ (يوم / شهر / سنة)...",
    dialogTitle: String? = null,
    confirmButtonText: String = "تأكيد",
    cancelButtonText: String = "إلغاء",
    enabled: Boolean = true,
    isError: Boolean = false,
    errorMessage: String? = null,
    calendarType: MeayarCalendarType = MeayarCalendarType.Gregorian,
    locale: Locale = Locale.getDefault()
) {
    val initialMillis = remember(selectedYear, selectedMonth, selectedDay, calendarType) {
        if (selectedYear != null && selectedMonth != null && selectedDay != null) {
            when (calendarType) {
                MeayarCalendarType.Gregorian -> {
                    JCalendar.getInstance(TimeZone.getTimeZone("UTC")).apply {
                        set(JCalendar.YEAR, selectedYear)
                        set(JCalendar.MONTH, selectedMonth)
                        set(JCalendar.DAY_OF_MONTH, selectedDay)
                        set(JCalendar.HOUR_OF_DAY, 0)
                        set(JCalendar.MINUTE, 0)
                        set(JCalendar.SECOND, 0)
                        set(JCalendar.MILLISECOND, 0)
                    }.timeInMillis
                }
                MeayarCalendarType.Hijri -> {
                    IslamicCalendar(locale).apply {
                        timeZone = android.icu.util.TimeZone.getTimeZone("UTC")
                        set(IcuCalendar.YEAR, selectedYear)
                        set(IcuCalendar.MONTH, selectedMonth)
                        set(IcuCalendar.DAY_OF_MONTH, selectedDay)
                        set(IcuCalendar.HOUR_OF_DAY, 0)
                        set(IcuCalendar.MINUTE, 0)
                        set(IcuCalendar.SECOND, 0)
                        set(IcuCalendar.MILLISECOND, 0)
                    }.timeInMillis
                }
            }
        } else null
    }

    MeayarDatePicker(
        selectedDateMillis = initialMillis,
        onDateSelected = { millis ->
            if (millis != null) {
                when (calendarType) {
                    MeayarCalendarType.Gregorian -> {
                        val cal = JCalendar.getInstance(TimeZone.getTimeZone("UTC")).apply {
                            timeInMillis = millis
                        }
                        onDateSelected(
                            cal.get(JCalendar.YEAR),
                            cal.get(JCalendar.MONTH),
                            cal.get(JCalendar.DAY_OF_MONTH)
                        )
                    }
                    MeayarCalendarType.Hijri -> {
                        val cal = IslamicCalendar(locale).apply {
                            timeZone = android.icu.util.TimeZone.getTimeZone("UTC")
                            timeInMillis = millis
                        }
                        onDateSelected(
                            cal.get(IcuCalendar.YEAR),
                            cal.get(IcuCalendar.MONTH),
                            cal.get(IcuCalendar.DAY_OF_MONTH)
                        )
                    }
                }
            }
        },
        modifier = modifier,
        label = label,
        isMandatory = isMandatory,
        placeholder = placeholder,
        dialogTitle = dialogTitle,
        confirmButtonText = confirmButtonText,
        cancelButtonText = cancelButtonText,
        enabled = enabled,
        isError = isError,
        errorMessage = errorMessage,
        calendarType = calendarType,
        locale = locale
    )
}
