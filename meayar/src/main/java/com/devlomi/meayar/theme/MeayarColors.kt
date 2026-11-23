package com.devlomi.meayar.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

/**
 * MEAYAR (معيار) Design System Color Palette.
 * Contains semantic and primitive tokens for the Syrian Digital Identity design system.
 */
@Immutable
data class MeayarColors(
    // Text Semantic Tokens
    val textPrimary: Color,
    val textSecondary: Color,
    val textTertiary: Color,
    val textDisabled: Color,
    val textInverse: Color,
    val textBrand: Color,
    val textError: Color,
    val textSuccess: Color,
    val textWarning: Color,
    val textInfo: Color,

    // Background Semantic Tokens
    val backgroundPage: Color,
    val backgroundSurface: Color,
    val backgroundSubtle: Color,
    val backgroundMuted: Color,
    val backgroundInverse: Color,
    val backgroundDisabled: Color,
    val backgroundBrand: Color,
    val backgroundBrandHover: Color,
    val backgroundBrandSubtle: Color,
    val backgroundError: Color,
    val backgroundErrorSubtle: Color,
    val backgroundSuccessSubtle: Color,
    val backgroundWarningSubtle: Color,
    val backgroundInfoSubtle: Color,

    // Border Semantic Tokens
    val borderDefault: Color,
    val borderSubtle: Color,
    val borderStrong: Color,
    val borderBrand: Color,
    val borderError: Color,
    val borderFocus: Color,

    // Icon Semantic Tokens
    val iconPrimary: Color,
    val iconSecondary: Color,
    val iconDisabled: Color,
    val iconBrand: Color,
    val iconInverse: Color,

    // Tag / Status Palettes
    val tagPrimaryBackground: Color,
    val tagPrimaryText: Color,
    val tagGreenBackground: Color,
    val tagGreenText: Color,
    val tagRedBackground: Color,
    val tagRedText: Color,
    val tagYellowBackground: Color,
    val tagYellowText: Color,
    val tagGoldBackground: Color,
    val tagGoldText: Color,
    val tagBlueBackground: Color,
    val tagBlueText: Color,
    val tagGrayBackground: Color,
    val tagGrayText: Color,

    val isDark: Boolean
)

// Raw Syrian State Identity Primitives
object MeayarPrimitives {
    val SyrianGreen900 = Color(0xFF000606)
    val SyrianGreen700 = Color(0xFF001312)
    val SyrianGreen500 = Color(0xFF002623)
    val SyrianGreen400 = Color(0xFF094239) // Main Syrian Brand Green
    val SyrianGreen300 = Color(0xFF427F77)
    val SyrianGreen200 = Color(0xFF3B877B)
    val SyrianGreen100 = Color(0xFF53A99C)
    val SyrianGreen0 = Color(0xFFD8DFDE)

    val SyrianGold800 = Color(0xFF4D4532)
    val SyrianGold700 = Color(0xFF706649)
    val SyrianGold500 = Color(0xFFB8A678)
    val SyrianGold400 = Color(0xFFC6B893)
    val SyrianGold300 = Color(0xFFD4CAAE)
    val SyrianGold200 = Color(0xFFE3DBC9)
    val SyrianGold0 = Color(0xFFFFFFFF)

    val Red600 = Color(0xFFCC2A1E)
    val Red500 = Color(0xFFEB3B2F)
    val Red400 = Color(0xFFF97066)
    val Red50 = Color(0xFFFEF3F2)

    val Green700 = Color(0xFF274A12)
    val Green600 = Color(0xFF367311)
    val Green400 = Color(0xFF61FF00)
    val Green50 = Color(0xFFF2FFEB)

    val Blue600 = Color(0xFF167BD9)
    val Blue400 = Color(0xFF2697FF)
    val Blue50 = Color(0xFFEBF5FF)

    val Neutral900 = Color(0xFF0D0D12)
    val Neutral800 = Color(0xFF1A1B25)
    val Neutral700 = Color(0xFF272835)
    val Neutral600 = Color(0xFF36394A)
    val Neutral500 = Color(0xFF666D80)
    val Neutral400 = Color(0xFF818898)
    val Neutral300 = Color(0xFFA4ACB9)
    val Neutral200 = Color(0xFFC1C7D0)
    val Neutral100 = Color(0xFFDFE1E7)
    val Neutral50 = Color(0xFFECEFF3)
    val Neutral25 = Color(0xFFF6F8FA)
    val White = Color(0xFFFFFFFF)
}

fun lightMeayarColors(): MeayarColors = MeayarColors(
    textPrimary = Color(0xFF0D0D12),
    textSecondary = Color(0xFF36394A),
    textTertiary = Color(0xFF666D80),
    textDisabled = Color(0xFFA4ACB9),
    textInverse = Color(0xFFFFFFFF),
    textBrand = Color(0xFF094239),
    textError = Color(0xFFCC2A1E),
    textSuccess = Color(0xFF367311),
    textWarning = Color(0xFF4A3204),
    textInfo = Color(0xFF167BD9),

    backgroundPage = Color(0xFFFFFFFF),
    backgroundSurface = Color(0xFFFFFFFF),
    backgroundSubtle = Color(0xFFF6F8FA),
    backgroundMuted = Color(0xFFECEFF3),
    backgroundInverse = Color(0xFF0D0D12),
    backgroundDisabled = Color(0xFFDFE1E7),
    backgroundBrand = Color(0xFF094239),
    backgroundBrandHover = Color(0xFF002623),
    backgroundBrandSubtle = Color(0xFFD8DFDE),
    backgroundError = Color(0xFFCC2A1E),
    backgroundErrorSubtle = Color(0xFFFEF3F2),
    backgroundSuccessSubtle = Color(0xFFF2FFEB),
    backgroundWarningSubtle = Color(0xFFFAE9C8),
    backgroundInfoSubtle = Color(0xFFEBF5FF),

    borderDefault = Color(0xFFC1C7D0),
    borderSubtle = Color(0xFFDFE1E7),
    borderStrong = Color(0xFF818898),
    borderBrand = Color(0xFF094239),
    borderError = Color(0xFFF97066),
    borderFocus = Color(0xFF094239),

    iconPrimary = Color(0xFF0D0D12),
    iconSecondary = Color(0xFF666D80),
    iconDisabled = Color(0xFFA4ACB9),
    iconBrand = Color(0xFF094239),
    iconInverse = Color(0xFFFFFFFF),

    tagPrimaryBackground = Color(0xFFD8DFDE),
    tagPrimaryText = Color(0xFF094239),
    tagGreenBackground = Color(0xFFF2FFEB),
    tagGreenText = Color(0xFF367311),
    tagRedBackground = Color(0xFFFEF3F2),
    tagRedText = Color(0xFFCC2A1E),
    tagYellowBackground = Color(0xFFFAE9C8),
    tagYellowText = Color(0xFF4A3204),
    tagGoldBackground = Color(0xFFE3DBC9),
    tagGoldText = Color(0xFF706649),
    tagBlueBackground = Color(0xFFEBF5FF),
    tagBlueText = Color(0xFF167BD9),
    tagGrayBackground = Color(0xFFECEFF3),
    tagGrayText = Color(0xFF36394A),

    isDark = false
)

fun darkMeayarColors(): MeayarColors = MeayarColors(
    textPrimary = Color(0xFFFFFFFF),
    textSecondary = Color(0xFFC1C7D0),
    textTertiary = Color(0xFFA4ACB9),
    textDisabled = Color(0xFF36394A),
    textInverse = Color(0xFF0D0D12),
    textBrand = Color(0xFF53A99C),
    textError = Color(0xFFF97066),
    textSuccess = Color(0xFF61FF00),
    textWarning = Color(0xFFF5B231),
    textInfo = Color(0xFF2697FF),

    backgroundPage = Color(0xFF0D0D12),
    backgroundSurface = Color(0xFF1A1B25),
    backgroundSubtle = Color(0xFF1A1B25),
    backgroundMuted = Color(0xFF272835),
    backgroundInverse = Color(0xFFFFFFFF),
    backgroundDisabled = Color(0xFF272835),
    backgroundBrand = Color(0xFF3B877B),
    backgroundBrandHover = Color(0xFF53A99C),
    backgroundBrandSubtle = Color(0xFF001312),
    backgroundError = Color(0xFFEB3B2F),
    backgroundErrorSubtle = Color(0xFF330D07),
    backgroundSuccessSubtle = Color(0xFF274A12),
    backgroundWarningSubtle = Color(0xFF330D07),
    backgroundInfoSubtle = Color(0xFF0B2D4C),

    borderDefault = Color(0xFF272835),
    borderSubtle = Color(0xFF1A1B25),
    borderStrong = Color(0xFF666D80),
    borderBrand = Color(0xFF3B877B),
    borderError = Color(0xFFEB3B2F),
    borderFocus = Color(0xFF53A99C),

    iconPrimary = Color(0xFFFFFFFF),
    iconSecondary = Color(0xFFA4ACB9),
    iconDisabled = Color(0xFF36394A),
    iconBrand = Color(0xFF53A99C),
    iconInverse = Color(0xFF0D0D12),

    tagPrimaryBackground = Color(0xFF001312),
    tagPrimaryText = Color(0xFF53A99C),
    tagGreenBackground = Color(0xFF274A12),
    tagGreenText = Color(0xFF61FF00),
    tagRedBackground = Color(0xFF330D07),
    tagRedText = Color(0xFFF97066),
    tagYellowBackground = Color(0xFF330D07),
    tagYellowText = Color(0xFFF5B231),
    tagGoldBackground = Color(0xFF4D4532),
    tagGoldText = Color(0xFFC6B893),
    tagBlueBackground = Color(0xFF0B2D4C),
    tagBlueText = Color(0xFF2697FF),
    tagGrayBackground = Color(0xFF272835),
    tagGrayText = Color(0xFFC1C7D0),

    isDark = true
)

val LocalMeayarColors = staticCompositionLocalOf { lightMeayarColors() }
