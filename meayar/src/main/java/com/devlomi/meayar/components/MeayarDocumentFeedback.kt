package com.devlomi.meayar.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.devlomi.meayar.icons.MeayarIcons
import com.devlomi.meayar.theme.MeayarTheme

/**
 * Official MEAYAR Document Card component.
 */
@Composable
fun MeayarDocumentCard(
    title: String,
    fileType: String,
    fileSize: String,
    onDownloadClick: () -> Unit,
    modifier: Modifier = Modifier,
    downloadButtonText: String = "تحميل"
) {
    val colors = MeayarTheme.colors
    val typography = MeayarTheme.typography
    val shape = RoundedCornerShape(10.dp)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape)
            .background(colors.backgroundSurface)
            .border(BorderStroke(1.dp, colors.borderSubtle), shape)
            .padding(14.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(colors.backgroundBrandSubtle),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = MeayarIcons.Document,
                        contentDescription = null,
                        tint = colors.iconBrand,
                        modifier = Modifier.size(20.dp)
                    )
                }

                Column {
                    Text(
                        text = title,
                        style = typography.textSmBold,
                        color = colors.textPrimary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = "$fileType • $fileSize",
                        style = typography.text2xs,
                        color = colors.textTertiary
                    )
                }
            }

            MeayarButton(
                onClick = onDownloadClick,
                size = MeayarButtonSize.SM,
                variant = MeayarButtonVariant.Outlined
            ) {
                Text(text = downloadButtonText)
            }
        }
    }
}

/**
 * Official MEAYAR Page Feedback component.
 */
@Composable
fun MeayarPageFeedback(
    modifier: Modifier = Modifier,
    questionText: String = "هل كانت هذه الصفحة مفيدة؟",
    yesButtonText: String = "نعم 👍",
    noButtonText: String = "لا",
    thankYouText: String = "شكراً لمشاركتك! تساعدنا آراؤكم في تطوير الخدمات الرقمية.",
    onFeedbackSubmitted: ((Boolean) -> Unit)? = null
) {
    val colors = MeayarTheme.colors
    val typography = MeayarTheme.typography
    var submitted by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(colors.backgroundSubtle)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        if (!submitted) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = questionText,
                    style = typography.textSmMedium,
                    color = colors.textPrimary
                )

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    MeayarButton(
                        onClick = {
                            submitted = true
                            onFeedbackSubmitted?.invoke(true)
                        },
                        size = MeayarButtonSize.SM,
                        variant = MeayarButtonVariant.Outlined
                    ) {
                        Text(text = yesButtonText)
                    }

                    MeayarButton(
                        onClick = {
                            submitted = true
                            onFeedbackSubmitted?.invoke(false)
                        },
                        size = MeayarButtonSize.SM,
                        variant = MeayarButtonVariant.Ghost
                    ) {
                        Text(text = noButtonText)
                    }
                }
            }
        } else {
            Text(
                text = thankYouText,
                style = typography.textSmMedium,
                color = colors.textBrand
            )
        }
    }
}
