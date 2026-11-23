package com.devlomi.meayar.components

import android.icu.text.DateFormatSymbols
import android.icu.util.Calendar as IcuCalendar
import android.icu.util.IslamicCalendar
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.devlomi.meayar.icons.MeayarIcons
import com.devlomi.meayar.theme.MeayarTheme
import java.text.SimpleDateFormat
import java.util.Calendar as JCalendar
import java.util.Locale
import java.util.TimeZone

/**
 * Calendar system supported by MEAYAR Calendar components.
 */
enum class MeayarCalendarType {
    Gregorian,
    Hijri
}

private enum class CalendarViewMode {
    Days, Months, Years
}

private val DefaultHijriArabicMonths = listOf(
    "محرم", "صفر", "ربيع الأول", "ربيع الآخر",
    "جمادى الأولى", "جمادى الآخرة", "رجب", "شعبان",
    "رمضان", "شوال", "ذو القعدة", "ذو الحجة"
)

private val DefaultHijriEnglishMonths = listOf(
    "Muharram", "Safar", "Rabi' al-Awwal", "Rabi' al-Thani",
    "Jumada al-Ula", "Jumada al-Akhirah", "Rajab", "Sha'ban",
    "Ramadan", "Shawwal", "Dhu al-Qi'dah", "Dhu al-Hijjah"
)

/**
 * Official MEAYAR Inline Calendar component.
 *
 * Provides a clean inline calendar with full device/custom locale awareness and calendar type support:
 * - Dynamic calendar types: [MeayarCalendarType.Gregorian] and [MeayarCalendarType.Hijri]
 * - Custom locale parameter: defaults to [Locale.getDefault] or accepts any explicit [Locale]
 * - Dynamic month names (Levant Arabic for Syria, Standard Arabic, English, or Islamic Hijri months)
 * - Dynamic first day of the week based on the selected locale (Saturday in Arab countries)
 * - Quick Month selection mode (3x4 grid)
 * - Quick Year selection mode (scrollable grid)
 * - Zero empty white space and no overlapping artifacts
 */
@Composable
fun MeayarCalendar(
    selectedYear: Int,
    selectedMonth: Int, // 0-based: 0 = January/Muharram, 11 = December/Dhu al-Hijjah
    selectedDay: Int,
    onDateSelected: (year: Int, month: Int, day: Int) -> Unit,
    modifier: Modifier = Modifier,
    calendarType: MeayarCalendarType = MeayarCalendarType.Gregorian,
    locale: Locale = Locale.getDefault(),
    customMonthNames: List<String>? = null,
    customWeekdayLabels: List<String>? = null,
    showBorder: Boolean = true
) {
    val colors = MeayarTheme.colors
    val typography = MeayarTheme.typography
    val shape = RoundedCornerShape(12.dp)
    val isRtl = LocalLayoutDirection.current == LayoutDirection.Rtl

    var currentDisplayYear by rememberSaveable(selectedYear, calendarType) { mutableIntStateOf(selectedYear) }
    var currentDisplayMonth by rememberSaveable(selectedMonth, calendarType) { mutableIntStateOf(selectedMonth) }
    var viewMode by rememberSaveable { mutableStateOf(CalendarViewMode.Days) }

    // Weekday labels localized and ordered by the locale's first day of the week
    val (firstDayOfWeek, weekdayLabels) = remember(locale, customWeekdayLabels) {
        val cal = JCalendar.getInstance(locale)
        val firstDay = cal.firstDayOfWeek
        val labels = customWeekdayLabels ?: (0..6).map { i ->
            val dayOfWeek = ((firstDay - 1 + i) % 7) + 1
            when (dayOfWeek) {
                JCalendar.SATURDAY -> if (locale.language == "ar") "س" else "S"
                JCalendar.SUNDAY -> if (locale.language == "ar") "ح" else "S"
                JCalendar.MONDAY -> if (locale.language == "ar") "ن" else "M"
                JCalendar.TUESDAY -> if (locale.language == "ar") "ث" else "T"
                JCalendar.WEDNESDAY -> if (locale.language == "ar") "ر" else "W"
                JCalendar.THURSDAY -> if (locale.language == "ar") "خ" else "T"
                JCalendar.FRIDAY -> if (locale.language == "ar") "ج" else "F"
                else -> ""
            }
        }
        Pair(firstDay, labels)
    }

    // Localized month names (0 = Jan/Muharram, 11 = Dec/Dhu al-Hijjah)
    val monthNames = remember(locale, calendarType, customMonthNames) {
        if (customMonthNames != null && customMonthNames.size >= 12) {
            customMonthNames
        } else when (calendarType) {
            MeayarCalendarType.Gregorian -> {
                val sdf = SimpleDateFormat("MMMM", locale)
                (0..11).map { m ->
                    val c = JCalendar.getInstance(locale).apply {
                        set(JCalendar.DAY_OF_MONTH, 1)
                        set(JCalendar.MONTH, m)
                    }
                    sdf.format(c.time)
                }
            }
            MeayarCalendarType.Hijri -> {
                runCatching {
                    val cal = IslamicCalendar(locale)
                    val symbols = DateFormatSymbols(cal, locale)
                    val rawMonths = symbols.months
                    if (rawMonths != null && rawMonths.size >= 12) {
                        rawMonths.take(12).toList()
                    } else null
                }.getOrNull() ?: if (locale.language == "ar") DefaultHijriArabicMonths else DefaultHijriEnglishMonths
            }
        }
    }

    // Days in current display month and starting day offset
    val (daysInMonth, startDayOffset) = remember(currentDisplayYear, currentDisplayMonth, firstDayOfWeek, calendarType) {
        when (calendarType) {
            MeayarCalendarType.Gregorian -> {
                val cal = JCalendar.getInstance(locale).apply {
                    set(JCalendar.YEAR, currentDisplayYear)
                    set(JCalendar.MONTH, currentDisplayMonth)
                    set(JCalendar.DAY_OF_MONTH, 1)
                }
                val maxDays = cal.getActualMaximum(JCalendar.DAY_OF_MONTH)
                val monthStartDayOfWeek = cal.get(JCalendar.DAY_OF_WEEK)
                val offset = (monthStartDayOfWeek - firstDayOfWeek + 7) % 7
                Pair(maxDays, offset)
            }
            MeayarCalendarType.Hijri -> {
                val cal = IslamicCalendar(locale).apply {
                    set(IcuCalendar.YEAR, currentDisplayYear)
                    set(IcuCalendar.MONTH, currentDisplayMonth)
                    set(IcuCalendar.DAY_OF_MONTH, 1)
                }
                val maxDays = cal.getActualMaximum(IcuCalendar.DAY_OF_MONTH)
                val monthStartDayOfWeek = cal.get(IcuCalendar.DAY_OF_WEEK)
                val offset = (monthStartDayOfWeek - firstDayOfWeek + 7) % 7
                Pair(maxDays, offset)
            }
        }
    }

    // Today values for highlighting
    val (todayYear, todayMonth, todayDay) = remember(calendarType) {
        when (calendarType) {
            MeayarCalendarType.Gregorian -> {
                val todayCal = JCalendar.getInstance()
                Triple(
                    todayCal.get(JCalendar.YEAR),
                    todayCal.get(JCalendar.MONTH),
                    todayCal.get(JCalendar.DAY_OF_MONTH)
                )
            }
            MeayarCalendarType.Hijri -> {
                val todayCal = IslamicCalendar(locale)
                Triple(
                    todayCal.get(IcuCalendar.YEAR),
                    todayCal.get(IcuCalendar.MONTH),
                    todayCal.get(IcuCalendar.DAY_OF_MONTH)
                )
            }
        }
    }

    val boxModifier = if (showBorder) {
        modifier
            .fillMaxWidth()
            .clip(shape)
            .background(colors.backgroundSurface)
            .border(1.dp, colors.borderSubtle, shape)
            .padding(16.dp)
    } else {
        modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    }

    Box(modifier = boxModifier) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Header: Month & Year navigation + mode toggles
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Previous Month button (active only in Days view, fixed 36dp - never shrinks)
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .clickable(
                            enabled = viewMode == CalendarViewMode.Days,
                            onClick = {
                                if (currentDisplayMonth == 0) {
                                    currentDisplayMonth = 11
                                    currentDisplayYear -= 1
                                } else {
                                    currentDisplayMonth -= 1
                                }
                            }
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = if (isRtl) MeayarIcons.ChevronRight else MeayarIcons.ChevronLeft,
                        contentDescription = "الشهر السابق",
                        tint = if (viewMode == CalendarViewMode.Days) colors.iconPrimary else Color.Transparent,
                        modifier = Modifier.size(20.dp)
                    )
                }

                // Month & Year clickable dropdown selectors (takes all available center space)
                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Month Picker Pill
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = if (viewMode == CalendarViewMode.Months) colors.backgroundBrandSubtle else Color.Transparent,
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .clickable {
                                viewMode = if (viewMode == CalendarViewMode.Months) {
                                    CalendarViewMode.Days
                                } else {
                                    CalendarViewMode.Months
                                }
                            }
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                text = monthNames.getOrElse(currentDisplayMonth) { "" },
                                style = typography.textSmBold,
                                color = if (viewMode == CalendarViewMode.Months) colors.textBrand else colors.textPrimary,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Icon(
                                imageVector = MeayarIcons.ChevronDown,
                                contentDescription = null,
                                tint = if (viewMode == CalendarViewMode.Months) colors.iconBrand else colors.iconSecondary,
                                modifier = Modifier
                                    .size(16.dp)
                                    .rotate(if (viewMode == CalendarViewMode.Months) 180f else 0f)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(4.dp))

                    // Year Picker Pill
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = if (viewMode == CalendarViewMode.Years) colors.backgroundBrandSubtle else Color.Transparent,
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .clickable {
                                viewMode = if (viewMode == CalendarViewMode.Years) {
                                    CalendarViewMode.Days
                                } else {
                                    CalendarViewMode.Years
                                }
                            }
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                text = "$currentDisplayYear",
                                style = typography.textSmBold,
                                color = if (viewMode == CalendarViewMode.Years) colors.textBrand else colors.textPrimary,
                                maxLines = 1
                            )
                            Icon(
                                imageVector = MeayarIcons.ChevronDown,
                                contentDescription = null,
                                tint = if (viewMode == CalendarViewMode.Years) colors.iconBrand else colors.iconSecondary,
                                modifier = Modifier
                                    .size(16.dp)
                                    .rotate(if (viewMode == CalendarViewMode.Years) 180f else 0f)
                            )
                        }
                    }
                }

                // Next Month button (active only in Days view, fixed 36dp - never shrinks)
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .clickable(
                            enabled = viewMode == CalendarViewMode.Days,
                            onClick = {
                                if (currentDisplayMonth == 11) {
                                    currentDisplayMonth = 0
                                    currentDisplayYear += 1
                                } else {
                                    currentDisplayMonth += 1
                                }
                            }
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = if (isRtl) MeayarIcons.ChevronLeft else MeayarIcons.ChevronRight,
                        contentDescription = "الشهر التالي",
                        tint = if (viewMode == CalendarViewMode.Days) colors.iconPrimary else Color.Transparent,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Body Area with view transitions
            AnimatedContent(
                targetState = viewMode,
                transitionSpec = { fadeIn() togetherWith fadeOut() },
                label = "CalendarViewTransition",
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 250.dp)
            ) { mode ->
                when (mode) {
                    CalendarViewMode.Days -> {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            // Weekday Headers
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceAround
                            ) {
                                weekdayLabels.forEach { dayLabel ->
                                    Box(
                                        modifier = Modifier.size(36.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = dayLabel,
                                            style = typography.textXsMedium,
                                            color = colors.textSecondary
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            // Day Cells (6 rows x 7 cols = 42 cells)
                            val totalCells = 42
                            val rows = totalCells / 7

                            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                                for (rowIndex in 0 until rows) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceAround
                                    ) {
                                        for (colIndex in 0 until 7) {
                                            val cellIndex = rowIndex * 7 + colIndex
                                            val dayNumber = cellIndex - startDayOffset + 1

                                            if (dayNumber in 1..daysInMonth) {
                                                val isSelected = currentDisplayYear == selectedYear &&
                                                        currentDisplayMonth == selectedMonth &&
                                                        dayNumber == selectedDay

                                                val isToday = currentDisplayYear == todayYear &&
                                                        currentDisplayMonth == todayMonth &&
                                                        dayNumber == todayDay

                                                Box(
                                                    modifier = Modifier
                                                        .size(36.dp)
                                                        .clip(CircleShape)
                                                        .background(
                                                            when {
                                                                isSelected -> colors.backgroundBrand
                                                                else -> Color.Transparent
                                                            }
                                                        )
                                                        .then(
                                                            if (isToday && !isSelected) {
                                                                Modifier.border(1.5.dp, colors.borderBrand, CircleShape)
                                                            } else Modifier
                                                        )
                                                        .clickable {
                                                            onDateSelected(currentDisplayYear, currentDisplayMonth, dayNumber)
                                                        },
                                                    contentAlignment = Alignment.Center
                                                ) {
                                                    Text(
                                                        text = dayNumber.toString(),
                                                        style = if (isSelected || isToday) typography.textSmBold else typography.textSm,
                                                        color = when {
                                                            isSelected -> colors.textInverse
                                                            isToday -> colors.textBrand
                                                            else -> colors.textPrimary
                                                        }
                                                    )
                                                }
                                            } else {
                                                // Empty cell outside current month
                                                Box(modifier = Modifier.size(36.dp))
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    CalendarViewMode.Months -> {
                        // 4 rows x 3 columns month selector grid
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp),
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {
                            for (row in 0 until 4) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    for (col in 0 until 3) {
                                        val monthIndex = row * 3 + col
                                        val isCurrent = monthIndex == currentDisplayMonth
                                        Box(
                                            modifier = Modifier
                                                .weight(1f)
                                                .height(48.dp)
                                                .clip(RoundedCornerShape(8.dp))
                                                .background(
                                                    if (isCurrent) colors.backgroundBrand else colors.backgroundSubtle
                                                )
                                                .border(
                                                    1.dp,
                                                    if (isCurrent) colors.borderBrand else colors.borderSubtle,
                                                    RoundedCornerShape(8.dp)
                                                )
                                                .clickable {
                                                    currentDisplayMonth = monthIndex
                                                    viewMode = CalendarViewMode.Days
                                                },
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = monthNames.getOrElse(monthIndex) { "" },
                                                style = if (isCurrent) typography.textSmBold else typography.textSm,
                                                color = if (isCurrent) colors.textInverse else colors.textPrimary
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }

                    CalendarViewMode.Years -> {
                        // Scrollable Year selector grid
                        val years = remember(calendarType, currentDisplayYear) {
                            val center = currentDisplayYear
                            ((center - 50)..(center + 50)).toList()
                        }
                        val initialIndex = remember(currentDisplayYear, years) {
                            val idx = years.indexOf(currentDisplayYear)
                            if (idx >= 0) maxOf(0, idx - 4) else 0
                        }
                        val gridState = rememberLazyGridState(initialFirstVisibleItemIndex = initialIndex)

                        LazyVerticalGrid(
                            columns = GridCells.Fixed(3),
                            state = gridState,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(years) { year ->
                                val isCurrent = year == currentDisplayYear
                                Box(
                                    modifier = Modifier
                                        .height(44.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(
                                            if (isCurrent) colors.backgroundBrand else colors.backgroundSubtle
                                        )
                                        .border(
                                            1.dp,
                                            if (isCurrent) colors.borderBrand else colors.borderSubtle,
                                            RoundedCornerShape(8.dp)
                                        )
                                        .clickable {
                                            currentDisplayYear = year
                                            viewMode = CalendarViewMode.Days
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = year.toString(),
                                        style = if (isCurrent) typography.textSmBold else typography.textSm,
                                        color = if (isCurrent) colors.textInverse else colors.textPrimary
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

/**
 * Millisecond timestamp-based overload for modern Compose APIs.
 */
@Composable
fun MeayarCalendar(
    selectedDateMillis: Long?,
    onDateSelected: (Long?) -> Unit,
    modifier: Modifier = Modifier,
    calendarType: MeayarCalendarType = MeayarCalendarType.Gregorian,
    locale: Locale = Locale.getDefault(),
    customMonthNames: List<String>? = null,
    customWeekdayLabels: List<String>? = null,
    showBorder: Boolean = true
) {
    val (year, month, day) = remember(selectedDateMillis, calendarType, locale) {
        val millis = selectedDateMillis ?: System.currentTimeMillis()
        when (calendarType) {
            MeayarCalendarType.Gregorian -> {
                val cal = JCalendar.getInstance(TimeZone.getTimeZone("UTC")).apply {
                    timeInMillis = millis
                }
                Triple(
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
                Triple(
                    cal.get(IcuCalendar.YEAR),
                    cal.get(IcuCalendar.MONTH),
                    cal.get(IcuCalendar.DAY_OF_MONTH)
                )
            }
        }
    }

    MeayarCalendar(
        selectedYear = year,
        selectedMonth = month,
        selectedDay = day,
        calendarType = calendarType,
        locale = locale,
        customMonthNames = customMonthNames,
        customWeekdayLabels = customWeekdayLabels,
        showBorder = showBorder,
        onDateSelected = { y, m, d ->
            val millis = when (calendarType) {
                MeayarCalendarType.Gregorian -> {
                    val cal = JCalendar.getInstance(TimeZone.getTimeZone("UTC")).apply {
                        set(JCalendar.YEAR, y)
                        set(JCalendar.MONTH, m)
                        set(JCalendar.DAY_OF_MONTH, d)
                        set(JCalendar.HOUR_OF_DAY, 0)
                        set(JCalendar.MINUTE, 0)
                        set(JCalendar.SECOND, 0)
                        set(JCalendar.MILLISECOND, 0)
                    }
                    cal.timeInMillis
                }
                MeayarCalendarType.Hijri -> {
                    val cal = IslamicCalendar(locale).apply {
                        timeZone = android.icu.util.TimeZone.getTimeZone("UTC")
                        set(IcuCalendar.YEAR, y)
                        set(IcuCalendar.MONTH, m)
                        set(IcuCalendar.DAY_OF_MONTH, d)
                        set(IcuCalendar.HOUR_OF_DAY, 0)
                        set(IcuCalendar.MINUTE, 0)
                        set(IcuCalendar.SECOND, 0)
                        set(IcuCalendar.MILLISECOND, 0)
                    }
                    cal.timeInMillis
                }
            }
            onDateSelected(millis)
        },
        modifier = modifier
    )
}
