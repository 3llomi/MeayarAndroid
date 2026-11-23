package com.devlomi.meayar.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.sp
import com.devlomi.meayar.R

/**
 * MEAYAR Official Typography Foundation.
 * Based on Qomra Arabic with RTL text-direction support.
 */
val QomraArabicFamily = FontFamily(
    Font(R.font.qomra_arabic_regular, FontWeight.Normal),
    Font(R.font.qomra_arabic_medium, FontWeight.Medium),
    Font(R.font.qomra_arabic_bold, FontWeight.SemiBold),
    Font(R.font.qomra_arabic_bold, FontWeight.Bold)
)

@Immutable
data class MeayarTypography(
    // Heading Scales
    val headingXl: TextStyle = TextStyle(
        fontFamily = QomraArabicFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 40.sp,
        lineHeight = 48.sp,
        textDirection = TextDirection.ContentOrRtl
    ),
    val headingLg: TextStyle = TextStyle(
        fontFamily = QomraArabicFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp,
        lineHeight = 44.sp,
        textDirection = TextDirection.ContentOrRtl
    ),
    val headingMd: TextStyle = TextStyle(
        fontFamily = QomraArabicFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        textDirection = TextDirection.ContentOrRtl
    ),
    val headingSm: TextStyle = TextStyle(
        fontFamily = QomraArabicFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        textDirection = TextDirection.ContentOrRtl
    ),
    val headingXs: TextStyle = TextStyle(
        fontFamily = QomraArabicFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        textDirection = TextDirection.ContentOrRtl
    ),
    val heading2xs: TextStyle = TextStyle(
        fontFamily = QomraArabicFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
        lineHeight = 24.sp,
        textDirection = TextDirection.ContentOrRtl
    ),

    // Body / Text Scales
    val textXl: TextStyle = TextStyle(
        fontFamily = QomraArabicFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        lineHeight = 28.sp,
        textDirection = TextDirection.ContentOrRtl
    ),
    val textLg: TextStyle = TextStyle(
        fontFamily = QomraArabicFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 28.sp,
        textDirection = TextDirection.ContentOrRtl
    ),
    val textLgMedium: TextStyle = TextStyle(
        fontFamily = QomraArabicFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        lineHeight = 28.sp,
        textDirection = TextDirection.ContentOrRtl
    ),
    val textMd: TextStyle = TextStyle(
        fontFamily = QomraArabicFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        textDirection = TextDirection.ContentOrRtl
    ),
    val textMdMedium: TextStyle = TextStyle(
        fontFamily = QomraArabicFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        textDirection = TextDirection.ContentOrRtl
    ),
    val textMdBold: TextStyle = TextStyle(
        fontFamily = QomraArabicFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        textDirection = TextDirection.ContentOrRtl
    ),
    val textSm: TextStyle = TextStyle(
        fontFamily = QomraArabicFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        textDirection = TextDirection.ContentOrRtl
    ),
    val textSmMedium: TextStyle = TextStyle(
        fontFamily = QomraArabicFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        textDirection = TextDirection.ContentOrRtl
    ),
    val textSmBold: TextStyle = TextStyle(
        fontFamily = QomraArabicFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        textDirection = TextDirection.ContentOrRtl
    ),
    val textXs: TextStyle = TextStyle(
        fontFamily = QomraArabicFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 18.sp,
        textDirection = TextDirection.ContentOrRtl
    ),
    val textXsMedium: TextStyle = TextStyle(
        fontFamily = QomraArabicFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 18.sp,
        textDirection = TextDirection.ContentOrRtl
    ),
    val text2xs: TextStyle = TextStyle(
        fontFamily = QomraArabicFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        lineHeight = 14.sp,
        textDirection = TextDirection.ContentOrRtl
    ),
    val text2xsMedium: TextStyle = TextStyle(
        fontFamily = QomraArabicFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 10.sp,
        lineHeight = 14.sp,
        textDirection = TextDirection.ContentOrRtl
    )
)

val LocalMeayarTypography = staticCompositionLocalOf { MeayarTypography() }
