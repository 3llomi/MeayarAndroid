package com.devlomi.meayar.sample.catalog

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.devlomi.meayar.components.MeayarCard
import com.devlomi.meayar.theme.MeayarPrimitives
import com.devlomi.meayar.theme.MeayarTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FoundationsScreen(modifier: Modifier = Modifier) {
    val colors = MeayarTheme.colors
    val typography = MeayarTheme.typography

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(colors.backgroundPage),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 96.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        item {
            Column {
                Text(
                    text = "أساسيات النظام (Foundations)",
                    style = typography.headingMd,
                    color = colors.textPrimary
                )
                Text(
                    text = "القيم المستخرجة مباشرة من معيار: ألوان الهوية السورية، درجات الخطوط، والحواف المنحنية.",
                    style = typography.textSm,
                    color = colors.textSecondary
                )
            }
        }

        // Syrian State Green Palette
        item {
            MeayarCard {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(
                        text = "الأخضر السوري (Syrian Green Primitives)",
                        style = typography.heading2xs,
                        color = colors.textPrimary
                    )

                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        ColorSwatch("Brand 400", MeayarPrimitives.SyrianGreen400, "#094239", isDark = true)
                        ColorSwatch("Green 500", MeayarPrimitives.SyrianGreen500, "#002623", isDark = true)
                        ColorSwatch("Green 300", MeayarPrimitives.SyrianGreen300, "#427F77", isDark = true)
                        ColorSwatch("Green 200", MeayarPrimitives.SyrianGreen200, "#3B877B", isDark = false)
                        ColorSwatch("Green 100", MeayarPrimitives.SyrianGreen100, "#53A99C", isDark = false)
                        ColorSwatch("Green 0", MeayarPrimitives.SyrianGreen0, "#D8DFDE", isDark = false)
                    }
                }
            }
        }

        // Syrian Gold Palette
        item {
            MeayarCard {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(
                        text = "الذهب السوري (Syrian Gold Primitives)",
                        style = typography.heading2xs,
                        color = colors.textPrimary
                    )

                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        ColorSwatch("Gold 800", MeayarPrimitives.SyrianGold800, "#4D4532", isDark = true)
                        ColorSwatch("Gold 700", MeayarPrimitives.SyrianGold700, "#706649", isDark = true)
                        ColorSwatch("Gold 500", MeayarPrimitives.SyrianGold500, "#B8A678", isDark = false)
                        ColorSwatch("Gold 400", MeayarPrimitives.SyrianGold400, "#C6B893", isDark = false)
                        ColorSwatch("Gold 300", MeayarPrimitives.SyrianGold300, "#D4CAAE", isDark = false)
                        ColorSwatch("Gold 200", MeayarPrimitives.SyrianGold200, "#E3DBC9", isDark = false)
                    }
                }
            }
        }

        // Typography Scale
        item {
            MeayarCard {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(
                        text = "سلّم الخطوط (Qomra Arabic)",
                        style = typography.heading2xs,
                        color = colors.textPrimary
                    )

                    Text("Heading XL (40sp / 48lh Bold)", style = typography.headingXl, color = colors.textPrimary)
                    Text("Heading LG (36sp / 44lh Bold)", style = typography.headingLg, color = colors.textPrimary)
                    Text("Heading MD (32sp / 40lh Bold)", style = typography.headingMd, color = colors.textPrimary)
                    Text("Heading SM (28sp / 36lh SemiBold)", style = typography.headingSm, color = colors.textPrimary)
                    Text("Heading XS (24sp / 32lh SemiBold)", style = typography.headingXs, color = colors.textPrimary)
                    Text("Heading 2XS (20sp / 24lh Medium)", style = typography.heading2xs, color = colors.textPrimary)

                    Spacer(modifier = Modifier.height(8.dp))

                    Text("Text XL (20sp) — نص كبير للقراءة والعناوين الفرعية", style = typography.textXl, color = colors.textSecondary)
                    Text("Text LG (18sp) — نص مقروء ومريح للفقرات البارزة", style = typography.textLg, color = colors.textSecondary)
                    Text("Text MD (16sp) — النص الأساسي الافتراضي لجميع الواجهات", style = typography.textMd, color = colors.textSecondary)
                    Text("Text SM (14sp) — النصوص المساعدة وحقول الإدخال والأزرار", style = typography.textSm, color = colors.textSecondary)
                    Text("Text XS (12sp) — التسميات الإيضاحية والشارات والتواريخ", style = typography.textXs, color = colors.textSecondary)
                    Text("Text 2XS (10sp) — الملاحظات المصغرة والأرقام الدقيقة", style = typography.text2xs, color = colors.textSecondary)
                }
            }
        }
    }
}

@Composable
private fun ColorSwatch(
    name: String,
    color: Color,
    hex: String,
    isDark: Boolean
) {
    val typography = MeayarTheme.typography
    Box(
        modifier = Modifier
            .size(width = 96.dp, height = 72.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(color)
            .border(1.dp, Color.Black.copy(alpha = 0.08f), RoundedCornerShape(8.dp))
            .padding(8.dp),
        contentAlignment = Alignment.BottomStart
    ) {
        Column {
            Text(
                text = name,
                style = typography.textXsMedium,
                color = if (isDark) Color.White else Color(0xFF0D0D12)
            )
            Text(
                text = hex,
                style = typography.text2xs,
                color = if (isDark) Color.White.copy(alpha = 0.8f) else Color(0xFF36394A)
            )
        }
    }
}
