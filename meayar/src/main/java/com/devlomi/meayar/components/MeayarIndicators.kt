package com.devlomi.meayar.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.progressBarRangeInfo
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.devlomi.meayar.theme.MeayarTheme

/**
 * Official MEAYAR Linear Progress Bar.
 */
@Composable
fun MeayarProgressBar(
    progress: Float, // 0f .. 1f
    modifier: Modifier = Modifier,
    height: Dp = 6.dp,
    trackColor: Color = MeayarTheme.colors.backgroundMuted,
    progressColor: Color = MeayarTheme.colors.backgroundBrand
) {
    val clampedProgress = progress.coerceIn(0f, 1f)
    val animatedProgress by androidx.compose.animation.core.animateFloatAsState(
        targetValue = clampedProgress,
        animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing),
        label = "progress_anim"
    )
    val shape = RoundedCornerShape(height / 2)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .clip(shape)
            .background(trackColor)
            .semantics {
                progressBarRangeInfo = ProgressBarRangeInfo(clampedProgress, 0f..1f)
            }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(animatedProgress)
                .height(height)
                .clip(shape)
                .background(progressColor)
        )
    }
}

/**
 * Official MEAYAR Skeleton Loader with animated pulsing shimmer.
 */
@Composable
fun MeayarSkeleton(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(8.dp),
    contentDescription: String = "جاري التحميل..."
) {
    val transition = rememberInfiniteTransition(label = "skeleton_shimmer")
    val alpha by transition.animateFloat(
        initialValue = 0.3f,
        targetValue = 0.85f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "skeleton_alpha"
    )

    val baseColor = MeayarTheme.colors.backgroundDisabled

    Box(
        modifier = modifier
            .clip(shape)
            .graphicsLayer { this.alpha = alpha }
            .background(baseColor)
            .semantics {
                this.contentDescription = contentDescription
            }
    )
}

/**
 * Size specifications for [MeayarSpinner].
 */
enum class MeayarSpinnerSize(val diameter: Dp, val strokeWidth: Dp) {
    XS(16.dp, 2.dp),
    SM(20.dp, 2.5.dp),
    MD(28.dp, 3.dp),
    LG(36.dp, 3.5.dp),
    XL(48.dp, 4.dp)
}

/**
 * Official MEAYAR Circular Spinner Loader.
 * - Normal: Spinner/Color/track (borderSubtle), Spinner/Color/indicator (backgroundBrand), Spinner/Text/label (textSecondary)
 * - Inverse: Spinner/Color/track-inverse (white subtle), Spinner/Color/indicator-inverse (white), Spinner/Text/label-inverse (white)
 */
@Composable
fun MeayarSpinner(
    modifier: Modifier = Modifier,
    size: MeayarSpinnerSize = MeayarSpinnerSize.MD,
    label: String? = null,
    isInverse: Boolean = false,
    loadingContentDescription: String = "جاري التحميل...",
    trackColor: Color = if (isInverse) Color.White.copy(alpha = 0.25f) else MeayarTheme.colors.borderSubtle,
    indicatorColor: Color = if (isInverse) Color.White else MeayarTheme.colors.backgroundBrand,
    labelColor: Color = if (isInverse) Color.White else MeayarTheme.colors.textSecondary
) {
    val typography = MeayarTheme.typography
    val transition = rememberInfiniteTransition(label = "meayar_spinner_anim")
    val rotation by transition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 900, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "meayar_spinner_rotation"
    )

    val spinnerCanvas = @Composable {
        Canvas(
            modifier = Modifier.size(size.diameter)
        ) {
            val stroke = Stroke(width = size.strokeWidth.toPx(), cap = StrokeCap.Round)
            // Background track
            drawCircle(
                color = trackColor,
                style = stroke
            )
            // Active rotating indicator arc
            drawArc(
                color = indicatorColor,
                startAngle = rotation,
                sweepAngle = 90f,
                useCenter = false,
                style = stroke
            )
        }
    }

    if (label == null) {
        Box(
            modifier = modifier.semantics {
                contentDescription = loadingContentDescription
            },
            contentAlignment = Alignment.Center
        ) {
            spinnerCanvas()
        }
    } else {
        Row(
            modifier = modifier.semantics(mergeDescendants = true) {
                contentDescription = "$label، $loadingContentDescription"
            },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            spinnerCanvas()
            Text(
                text = label,
                style = when (size) {
                    MeayarSpinnerSize.XS, MeayarSpinnerSize.SM -> typography.textXsMedium
                    MeayarSpinnerSize.MD -> typography.textSmMedium
                    MeayarSpinnerSize.LG, MeayarSpinnerSize.XL -> typography.textMdMedium
                },
                color = labelColor
            )
        }
    }
}

/**
 * Official MEAYAR Count Badge.
 */
@Composable
fun MeayarBadge(
    count: Int,
    modifier: Modifier = Modifier,
    containerColor: Color = MeayarTheme.colors.backgroundError,
    contentColor: Color = MeayarTheme.colors.textInverse
) {
    val typography = MeayarTheme.typography
    val text = if (count > 99) "99+" else count.toString()

    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(containerColor)
            .padding(horizontal = 6.dp, vertical = 2.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = typography.text2xsMedium,
            color = contentColor
        )
    }
}
