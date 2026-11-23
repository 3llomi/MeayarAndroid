package com.devlomi.meayar.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.devlomi.meayar.icons.MeayarIcons
import com.devlomi.meayar.theme.MeayarTheme

data class MeayarStep(
    val title: String,
    val subtitle: String? = null
)

/**
 * Official MEAYAR Stepper component for multi-step service applications.
 */
@Composable
fun MeayarStepper(
    steps: List<MeayarStep>,
    currentStepIndex: Int,
    modifier: Modifier = Modifier,
    completedStatusText: String = "مكتملة",
    currentStatusText: String = "الحالية",
    incompleteStatusText: String = "غير مكتملة",
    stepDescriptionFormatter: (stepNumber: Int, totalSteps: Int, title: String, status: String) -> String = { num, total, title, status ->
        "خطوة $num من $total: $title، $status"
    }
) {
    val colors = MeayarTheme.colors
    val typography = MeayarTheme.typography

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        steps.forEachIndexed { index, step ->
            val isCompleted = index < currentStepIndex
            val isCurrent = index == currentStepIndex

            // Step Indicator Circle
            val circleColor = when {
                isCompleted -> colors.textSuccess
                isCurrent -> colors.backgroundBrand
                else -> colors.backgroundDisabled
            }

            val textColor = when {
                isCompleted || isCurrent -> colors.textInverse
                else -> colors.textTertiary
            }

            val stepStatus = when {
                isCompleted -> completedStatusText
                isCurrent -> currentStatusText
                else -> incompleteStatusText
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(1f)
                    .semantics(mergeDescendants = true) {
                        contentDescription = stepDescriptionFormatter(index + 1, steps.size, step.title, stepStatus)
                    }
            ) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(circleColor),
                    contentAlignment = Alignment.Center
                ) {
                    if (isCompleted) {
                        Icon(
                            imageVector = MeayarIcons.Check,
                            contentDescription = null,
                            tint = colors.textInverse,
                            modifier = Modifier.size(16.dp)
                        )
                    } else {
                        Text(
                            text = (index + 1).toString(),
                            style = typography.textXsMedium,
                            color = textColor
                        )
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = step.title,
                    style = if (isCurrent) typography.textXsMedium else typography.text2xs,
                    color = if (isCurrent) colors.textBrand else colors.textSecondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center
                )

                if (step.subtitle != null) {
                    Text(
                        text = step.subtitle,
                        style = typography.text2xs,
                        color = colors.textTertiary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center
                    )
                }
            }

            // Connecting line to next step (aligned to the 16dp center of the 32dp circle)
            if (index < steps.size - 1) {
                val lineColor = if (index < currentStepIndex) colors.textSuccess else colors.borderSubtle
                Box(
                    modifier = Modifier
                        .weight(0.6f)
                        .padding(top = 15.dp)
                        .height(2.dp)
                        .background(lineColor)
                )
            }
        }
    }
}
