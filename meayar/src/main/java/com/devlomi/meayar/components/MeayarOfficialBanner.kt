package com.devlomi.meayar.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.devlomi.meayar.theme.MeayarPrimitives
import com.devlomi.meayar.theme.MeayarTheme

/**
 * Official Syrian State Identity Banner ("الجمهورية العربية السورية — بوابة الخدمات الرقمية").
 * Displayed at the very top of digital public portals.
 */
@Composable
fun MeayarOfficialBanner(
    modifier: Modifier = Modifier,
    title: String = "الجمهورية العربية السورية",
    subtitle: String = "بوابة الخدمات الرقمية الموحدة"
) {
    val colors = MeayarTheme.colors
    val typography = MeayarTheme.typography

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(colors.backgroundBrand)
            .padding(horizontal = 16.dp, vertical = 6.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Gold emblem accent dot
            Box(
                modifier = Modifier
                    .size(6.dp)
                    .clip(CircleShape)
                    .background(MeayarPrimitives.SyrianGold500)
            )

            Text(
                text = "$title • $subtitle",
                style = typography.text2xsMedium,
                color = colors.textInverse
            )

            Box(
                modifier = Modifier
                    .size(6.dp)
                    .clip(CircleShape)
                    .background(MeayarPrimitives.SyrianGold500)
            )
        }
    }
}
