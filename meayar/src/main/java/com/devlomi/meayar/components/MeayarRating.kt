package com.devlomi.meayar.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.devlomi.meayar.theme.MeayarPrimitives
import com.devlomi.meayar.theme.MeayarTheme

import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics

private val StarFilledVector: ImageVector by lazy {
    ImageVector.Builder(
        name = "StarFilled",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 24f,
        viewportHeight = 24f
    ).apply {
        path(fill = SolidColor(Color.Black), pathFillType = PathFillType.NonZero) {
            moveTo(12f, 17.27f)
            lineTo(18.18f, 21f)
            lineTo(16.54f, 13.97f)
            lineTo(22f, 9.24f)
            lineTo(14.81f, 8.63f)
            lineTo(12f, 2f)
            lineTo(9.19f, 8.63f)
            lineTo(2f, 9.24f)
            lineTo(7.46f, 13.97f)
            lineTo(5.82f, 21f)
            close()
        }
    }.build()
}

/**
 * Official MEAYAR Rating component.
 */
@Composable
fun MeayarRating(
    rating: Int,
    modifier: Modifier = Modifier,
    maxStars: Int = 5,
    starSize: Dp = 22.dp,
    ratingDescriptionFormatter: (rating: Int, maxStars: Int) -> String = { r, m -> "التقييم: $r من $m نجوم" },
    starActionDescriptionFormatter: (starNumber: Int, maxStars: Int) -> String = { num, max -> "تقييم $num من $max" },
    onRatingChange: ((Int) -> Unit)? = null
) {
    val goldColor = MeayarPrimitives.SyrianGold500
    val mutedColor = MeayarTheme.colors.backgroundMuted

    Row(
        modifier = modifier.semantics(mergeDescendants = true) {
            contentDescription = ratingDescriptionFormatter(rating, maxStars)
        },
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(maxStars) { index ->
            val starNumber = index + 1
            val isFilled = starNumber <= rating

            Icon(
                imageVector = StarFilledVector,
                contentDescription = null,
                tint = if (isFilled) goldColor else mutedColor,
                modifier = Modifier
                    .size(starSize)
                    .then(
                        if (onRatingChange != null) {
                            Modifier
                                .semantics {
                                    contentDescription = starActionDescriptionFormatter(starNumber, maxStars)
                                }
                                .clickable(role = Role.Button) { onRatingChange(starNumber) }
                        } else Modifier
                    )
            )
        }
    }
}
