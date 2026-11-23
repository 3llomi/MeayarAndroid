package com.devlomi.meayar.components

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.devlomi.meayar.icons.MeayarIcons
import com.devlomi.meayar.theme.MeayarTheme

import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics

/**
 * Official MEAYAR Breadcrumb component.
 */
@Composable
fun MeayarBreadcrumbs(
    items: List<String>,
    onItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    contentDescriptionFormatter: (List<String>) -> String = { breadcrumbs -> "مسار التصفح: ${breadcrumbs.joinToString(" ثم ")}" }
) {
    val colors = MeayarTheme.colors
    val typography = MeayarTheme.typography
    val isRtl = LocalLayoutDirection.current == LayoutDirection.Rtl

    Row(
        modifier = modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .semantics(mergeDescendants = true) {
                contentDescription = contentDescriptionFormatter(items)
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items.forEachIndexed { index, item ->
            val isLast = index == items.size - 1

            Text(
                text = item,
                style = if (isLast) typography.textSmMedium else typography.textSm,
                color = if (isLast) colors.textPrimary else colors.textTertiary,
                modifier = if (!isLast) {
                    Modifier.clickable(role = Role.Button) { onItemClick(index) }
                } else Modifier
            )

            if (!isLast) {
                Icon(
                    imageVector = MeayarIcons.ChevronLeft,
                    contentDescription = null,
                    tint = colors.iconSecondary,
                    modifier = Modifier
                        .size(14.dp)
                        .scale(scaleX = if (isRtl) 1f else -1f, scaleY = 1f)
                )
            }
        }
    }
}

/**
 * Calculates page numbers with smart ellipsis windowing (e.g. 1, 2, 3, ..., 10).
 * -1 represents an ellipsis.
 */
private fun calculatePaginationPages(currentPage: Int, totalPages: Int, maxVisible: Int = 7): List<Int> {
    if (totalPages <= maxVisible) return (1..totalPages).toList()
    val pages = mutableListOf<Int>()
    pages.add(1)
    if (currentPage > 3 && maxVisible >= 5) pages.add(-1)
    val windowSize = if (maxVisible <= 3) 1 else 3
    val start = maxOf(2, currentPage - windowSize / 2)
    val end = minOf(totalPages - 1, currentPage + windowSize / 2)
    for (i in start..end) {
        if (!pages.contains(i)) pages.add(i)
    }
    if (currentPage < totalPages - 2 && maxVisible >= 5) pages.add(-1)
    if (!pages.contains(totalPages)) pages.add(totalPages)
    return pages
}

/**
 * Official MEAYAR Pagination component.
 *
 * Supports centered minimal numbers or full-width layout with text labels ("السابق" / "التالي"),
 * smart windowing for large page sets, responsive compaction for mobile viewports, and full RTL/accessibility support.
 */
@Composable
fun MeayarPagination(
    currentPage: Int,
    totalPages: Int,
    onPageSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    showLabels: Boolean = false,
    previousLabel: String = "السابق",
    nextLabel: String = "التالي",
    previousContentDescription: String = "الصفحة السابقة",
    nextContentDescription: String = "الصفحة التالية",
    pageContentDescriptionFormatter: (pageNumber: Int, isCurrent: Boolean) -> String = { num, isCur -> "صفحة $num${if (isCur) "، الحالية" else ""}" }
) {
    val colors = MeayarTheme.colors
    val typography = MeayarTheme.typography
    val shape = RoundedCornerShape(6.dp)
    val isRtl = LocalLayoutDirection.current == LayoutDirection.Rtl

    BoxWithConstraints(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        val isCompact = maxWidth < 380.dp
        val maxPagesToShow = if (showLabels && isCompact) 3 else 7
        val visiblePages = remember(currentPage, totalPages, maxPagesToShow) {
            calculatePaginationPages(currentPage, totalPages, maxPagesToShow)
        }
        val buttonSize = if (isCompact) 32.dp else 36.dp
        val spacing = if (isCompact) 4.dp else 6.dp

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Prev button
            Box(
                modifier = Modifier
                    .height(buttonSize)
                    .then(if (!showLabels) Modifier.width(buttonSize) else Modifier)
                    .clip(shape)
                    .background(colors.backgroundSurface)
                    .border(1.dp, colors.borderSubtle, shape)
                    .clickable(enabled = currentPage > 1, role = Role.Button) { onPageSelected(currentPage - 1) }
                    .padding(horizontal = if (showLabels) (if (isCompact) 6.dp else 10.dp) else 0.dp)
                    .semantics {
                        contentDescription = previousContentDescription
                    },
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = MeayarIcons.ChevronRight,
                        contentDescription = null,
                        tint = if (currentPage > 1) colors.iconPrimary else colors.iconDisabled,
                        modifier = Modifier
                            .size(16.dp)
                            .scale(scaleX = if (isRtl) 1f else -1f, scaleY = 1f)
                    )
                    if (showLabels) {
                        Text(
                            text = previousLabel,
                            style = if (isCompact) typography.textXsMedium else typography.textSmMedium,
                            color = if (currentPage > 1) colors.textPrimary else colors.textDisabled,
                            maxLines = 1,
                            softWrap = false
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.width(spacing))

            // Page buttons with smart ellipsis
            visiblePages.forEachIndexed { index, pageNumber ->
                if (pageNumber == -1) {
                    Box(
                        modifier = Modifier.size(buttonSize),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "...",
                            style = typography.textSmMedium,
                            color = colors.textTertiary
                        )
                    }
                } else {
                    val isSelected = pageNumber == currentPage
                    Box(
                        modifier = Modifier
                            .size(buttonSize)
                            .clip(shape)
                            .background(if (isSelected) colors.backgroundBrand else colors.backgroundSurface)
                            .then(if (!isSelected) Modifier.border(1.dp, colors.borderSubtle, shape) else Modifier)
                            .clickable(role = Role.Button) { onPageSelected(pageNumber) }
                            .semantics {
                                contentDescription = pageContentDescriptionFormatter(pageNumber, isSelected)
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = pageNumber.toString(),
                            style = if (isSelected) typography.textSmBold else typography.textSm,
                            color = if (isSelected) colors.textInverse else colors.textPrimary
                        )
                    }
                }

                if (index < visiblePages.size - 1) {
                    Spacer(modifier = Modifier.width(spacing))
                }
            }

            Spacer(modifier = Modifier.width(spacing))

            // Next button
            Box(
                modifier = Modifier
                    .height(buttonSize)
                    .then(if (!showLabels) Modifier.width(buttonSize) else Modifier)
                    .clip(shape)
                    .background(colors.backgroundSurface)
                    .border(1.dp, colors.borderSubtle, shape)
                    .clickable(enabled = currentPage < totalPages, role = Role.Button) { onPageSelected(currentPage + 1) }
                    .padding(horizontal = if (showLabels) (if (isCompact) 6.dp else 10.dp) else 0.dp)
                    .semantics {
                        contentDescription = nextContentDescription
                    },
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    if (showLabels) {
                        Text(
                            text = nextLabel,
                            style = if (isCompact) typography.textXsMedium else typography.textSmMedium,
                            color = if (currentPage < totalPages) colors.textPrimary else colors.textDisabled,
                            maxLines = 1,
                            softWrap = false
                        )
                    }
                    Icon(
                        imageVector = MeayarIcons.ChevronLeft,
                        contentDescription = null,
                        tint = if (currentPage < totalPages) colors.iconPrimary else colors.iconDisabled,
                        modifier = Modifier
                            .size(16.dp)
                            .scale(scaleX = if (isRtl) 1f else -1f, scaleY = 1f)
                    )
                }
            }
        }
    }
}
