package com.devlomi.meayar.components

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.devlomi.meayar.icons.MeayarIcons
import com.devlomi.meayar.theme.MeayarTheme

/**
 * Data model for files managed by [MeayarFileUpload].
 */
data class MeayarFileItem(
    val id: String,
    val name: String,
    val sizeString: String,
    val progress: Float = 1f,
    val isError: Boolean = false,
    val errorMessage: String? = null
)

/**
 * Official MEAYAR File Upload Dropzone component.
 *
 * Essential for Syrian e-government services (ID attachments, certificates, civil records).
 */
@Composable
fun MeayarFileUpload(
    onSelectFiles: () -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    isMandatory: Boolean = false,
    titleText: String = "انقر لاختيار ملف من جهازك",
    hintText: String = "PDF, JPG, PNG (الحد الأقصى 10 ميغابايت)",
    uploadIconContentDescription: String = "رفع ملف",
    fileUploadErrorDefaultText: String = "فشل الرفع",
    removeFileContentDescription: String = "حذف الملف",
    files: List<MeayarFileItem> = emptyList(),
    onRemoveFile: ((MeayarFileItem) -> Unit)? = null,
    enabled: Boolean = true
) {
    val colors = MeayarTheme.colors
    val typography = MeayarTheme.typography
    val shape = RoundedCornerShape(10.dp)
    val borderColor = if (enabled) colors.borderBrand.copy(alpha = 0.6f) else colors.borderSubtle

    Column(modifier = modifier.fillMaxWidth()) {
        if (label != null) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                Text(
                    text = label,
                    style = typography.textSmMedium,
                    color = if (enabled) colors.textPrimary else colors.textDisabled
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

        val dashPathEffect = remember { PathEffect.dashPathEffect(floatArrayOf(12f, 8f), 0f) }

        // Dashed Dropzone Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape)
                .background(if (enabled) colors.backgroundSubtle.copy(alpha = 0.5f) else colors.backgroundDisabled)
                .drawBehind {
                    val stroke = Stroke(
                        width = 1.5.dp.toPx(),
                        pathEffect = dashPathEffect
                    )
                    drawRoundRect(
                        color = borderColor,
                        style = stroke,
                        cornerRadius = androidx.compose.ui.geometry.CornerRadius(10.dp.toPx())
                    )
                }
                .clickable(enabled = enabled) { onSelectFiles() }
                .padding(vertical = 24.dp, horizontal = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(colors.backgroundBrandSubtle),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = MeayarIcons.Upload,
                        contentDescription = uploadIconContentDescription,
                        tint = colors.iconBrand,
                        modifier = Modifier.size(24.dp)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = titleText,
                    style = typography.textMdMedium,
                    color = colors.textPrimary
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = hintText,
                    style = typography.textXs,
                    color = colors.textTertiary
                )
            }
        }

        // Attached Files List
        if (files.isNotEmpty()) {
            Spacer(modifier = Modifier.height(12.dp))
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                files.forEach { file ->
                    MeayarAttachedFileRow(
                        file = file,
                        onRemove = { onRemoveFile?.invoke(file) },
                        errorDefaultText = fileUploadErrorDefaultText,
                        removeContentDescription = removeFileContentDescription
                    )
                }
            }
        }
    }
}

/**
 * Renders an individual attached file item row with file icon, progress bar, error state, and remove button.
 */
@Composable
fun MeayarAttachedFileRow(
    file: MeayarFileItem,
    onRemove: () -> Unit,
    modifier: Modifier = Modifier,
    errorDefaultText: String = "فشل الرفع",
    removeContentDescription: String = "حذف الملف"
) {
    val colors = MeayarTheme.colors
    val typography = MeayarTheme.typography
    val shape = RoundedCornerShape(8.dp)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape)
            .background(colors.backgroundSurface)
            .border(1.dp, if (file.isError) colors.borderError else colors.borderSubtle, shape)
            .padding(12.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(if (file.isError) colors.backgroundErrorSubtle else colors.backgroundBrandSubtle),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = MeayarIcons.Document,
                        contentDescription = null,
                        tint = if (file.isError) colors.textError else colors.iconBrand,
                        modifier = Modifier.size(20.dp)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = file.name,
                        style = typography.textSmMedium,
                        color = colors.textPrimary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = if (file.isError) (file.errorMessage ?: errorDefaultText) else file.sizeString,
                        style = typography.textXs,
                        color = if (file.isError) colors.textError else colors.textTertiary
                    )
                }

                IconButton(
                    onClick = onRemove,
                    modifier = Modifier.size(28.dp)
                ) {
                    Icon(
                        imageVector = MeayarIcons.Close,
                        contentDescription = removeContentDescription,
                        tint = colors.iconSecondary,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            if (file.progress < 1f && !file.isError) {
                Spacer(modifier = Modifier.height(8.dp))
                MeayarProgressBar(progress = file.progress)
            }
        }
    }
}
