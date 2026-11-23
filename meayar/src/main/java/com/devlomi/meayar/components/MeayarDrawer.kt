package com.devlomi.meayar.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.devlomi.meayar.icons.MeayarIcons
import com.devlomi.meayar.theme.MeayarTheme

/**
 * Data model for navigation items in [MeayarDrawerContent] and [MeayarNavigationDrawer].
 */
data class MeayarDrawerItemData(
    val id: String,
    val title: String,
    val icon: ImageVector,
    val badge: String? = null,
    val isSelected: Boolean = false
)

/**
 * Official MEAYAR Modal Navigation Drawer component.
 *
 * Full RTL-first support: the drawer slides in smoothly from the start edge
 * (from the right in Arabic RTL, from the left in English LTR) with a dimming scrim overlay.
 *
 * @param drawerContent The composable content to display inside the drawer sheet (e.g. [MeayarDrawerContent]).
 * @param modifier Modifier applied to the root container.
 * @param drawerState State controlling whether the drawer is open or closed.
 * @param gesturesEnabled Whether the drawer can be opened or closed by swiping.
 * @param scrimColor Color of the background scrim overlay when the drawer is open.
 * @param content The main screen content over which the drawer slides.
 */
@Composable
fun MeayarModalNavigationDrawer(
    drawerContent: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed),
    gesturesEnabled: Boolean = true,
    scrimColor: Color = Color.Black.copy(alpha = 0.5f),
    windowInsets: WindowInsets = DrawerDefaults.windowInsets,
    content: @Composable () -> Unit
) {
    val colors = MeayarTheme.colors

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet(
                drawerShape = RoundedCornerShape(
                    topStart = 0.dp,
                    bottomStart = 0.dp,
                    topEnd = 16.dp,
                    bottomEnd = 16.dp
                ),
                drawerContainerColor = colors.backgroundSurface,
                drawerContentColor = colors.textPrimary,
                drawerTonalElevation = 0.dp,
                windowInsets = windowInsets
            ) {
                drawerContent()
            }
        },
        modifier = modifier,
        drawerState = drawerState,
        gesturesEnabled = gesturesEnabled,
        scrimColor = scrimColor,
        content = content
    )
}

/**
 * Convenient all-in-one MEAYAR Navigation Drawer that combines the modal drawer
 * container with [MeayarDrawerContent] automatically.
 */
@Composable
fun MeayarNavigationDrawer(
    items: List<MeayarDrawerItemData>,
    onItemSelected: (MeayarDrawerItemData) -> Unit,
    modifier: Modifier = Modifier,
    drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed),
    userName: String? = null,
    userNationalId: String? = null,
    onLogoutClick: (() -> Unit)? = null,
    headerTitle: String = "بوابة الخدمات الحكومية",
    headerSubtitle: String = "الجمهورية العربية السورية",
    footerText: String? = "معيار • الهوية الرقمية السورية",
    nationalIdLabel: String = "الرقم الوطني:",
    logoutText: String = "تسجيل الخروج",
    logoContentDescription: String = "شعار المنصة",
    gesturesEnabled: Boolean = true,
    windowInsets: WindowInsets = DrawerDefaults.windowInsets,
    content: @Composable () -> Unit
) {
    MeayarModalNavigationDrawer(
        drawerContent = {
            MeayarDrawerContent(
                items = items,
                onItemSelected = onItemSelected,
                userName = userName,
                userNationalId = userNationalId,
                onLogoutClick = onLogoutClick,
                headerTitle = headerTitle,
                headerSubtitle = headerSubtitle,
                footerText = footerText,
                nationalIdLabel = nationalIdLabel,
                logoutText = logoutText,
                logoContentDescription = logoContentDescription
            )
        },
        modifier = modifier,
        drawerState = drawerState,
        gesturesEnabled = gesturesEnabled,
        windowInsets = windowInsets,
        content = content
    )
}

/**
 * Official MEAYAR Permanent / Persistent Side Navigation Drawer.
 * Used for tablet, desktop, or landscape displays where the navigation panel
 * remains pinned to the start edge permanently.
 */
@Composable
fun MeayarPermanentNavigationDrawer(
    drawerContent: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    windowInsets: WindowInsets = DrawerDefaults.windowInsets,
    content: @Composable () -> Unit
) {
    val colors = MeayarTheme.colors

    PermanentNavigationDrawer(
        drawerContent = {
            PermanentDrawerSheet(
                drawerShape = RectangleShape,
                drawerContainerColor = colors.backgroundSurface,
                drawerContentColor = colors.textPrimary,
                drawerTonalElevation = 0.dp,
                windowInsets = windowInsets
            ) {
                drawerContent()
            }
        },
        modifier = modifier,
        content = content
    )
}

/**
 * Helper to remember a [DrawerState] with a default [DrawerValue.Closed].
 */
@Composable
fun rememberMeayarDrawerState(initialValue: DrawerValue = DrawerValue.Closed): DrawerState {
    return rememberDrawerState(initialValue)
}

/**
 * Official MEAYAR Navigation Drawer and Side Navigation component.
 *
 * Can be hosted inside Android Compose [androidx.compose.material3.ModalNavigationDrawer]
 * or rendered directly as a persistent sidebar on tablet / wide screens.
 */
@Composable
fun MeayarDrawerContent(
    items: List<MeayarDrawerItemData>,
    onItemSelected: (MeayarDrawerItemData) -> Unit,
    modifier: Modifier = Modifier,
    userName: String? = null,
    userNationalId: String? = null,
    onLogoutClick: (() -> Unit)? = null,
    headerTitle: String = "بوابة الخدمات الحكومية",
    headerSubtitle: String = "الجمهورية العربية السورية",
    footerText: String? = "معيار • الهوية الرقمية السورية",
    nationalIdLabel: String = "الرقم الوطني:",
    logoutText: String = "تسجيل الخروج",
    logoContentDescription: String = "شعار المنصة"
) {
    val colors = MeayarTheme.colors
    val typography = MeayarTheme.typography
    val scrollState = rememberScrollState()

    Box(
        modifier = modifier
            .width(320.dp)
            .fillMaxHeight()
            .background(colors.backgroundSurface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(vertical = 20.dp, horizontal = 16.dp)
        ) {
            // Syrian Official Header
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(colors.backgroundBrand),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = MeayarIcons.Building,
                        contentDescription = logoContentDescription,
                        tint = colors.textInverse,
                        modifier = Modifier.size(24.dp)
                    )
                }

                Column {
                    Text(
                        text = headerTitle,
                        style = typography.textMdBold,
                        color = colors.textPrimary
                    )
                    Text(
                        text = headerSubtitle,
                        style = typography.text2xs,
                        color = colors.textTertiary
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // User Identity Card (if signed in)
            if (userName != null) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(colors.backgroundSubtle)
                        .padding(12.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        MeayarAvatar(
                            name = userName,
                            size = MeayarAvatarSize.MD,
                            status = MeayarPresenceStatus.Online
                        )

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = userName,
                                style = typography.textSmMedium,
                                color = colors.textPrimary
                            )
                            if (userNationalId != null) {
                                Text(
                                    text = "$nationalIdLabel $userNationalId",
                                    style = typography.textXs,
                                    color = colors.textTertiary
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }

            MeayarDivider()

            Spacer(modifier = Modifier.height(12.dp))

            // Navigation Menu Items
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(scrollState),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items.forEach { item ->
                    MeayarDrawerItemRow(
                        item = item,
                        onClick = { onItemSelected(item) }
                    )
                }
            }

            MeayarDivider()

            Spacer(modifier = Modifier.height(12.dp))

            // Footer Logout Action
            if (onLogoutClick != null) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .clickable { onLogoutClick() }
                        .padding(horizontal = 12.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Icon(
                        imageVector = MeayarIcons.LogOut,
                        contentDescription = null,
                        tint = colors.textError,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = logoutText,
                        style = typography.textSmMedium,
                        color = colors.textError
                    )
                }
            }

            // Version stamp / footer text
            if (footerText != null) {
                Text(
                    text = footerText,
                    style = typography.text2xs,
                    color = colors.textDisabled,
                    modifier = Modifier.padding(start = 12.dp, top = 6.dp)
                )
            }
        }
    }
}

@Composable
private fun MeayarDrawerItemRow(
    item: MeayarDrawerItemData,
    onClick: () -> Unit
) {
    val colors = MeayarTheme.colors
    val typography = MeayarTheme.typography
    val shape = RoundedCornerShape(8.dp)

    val bg = if (item.isSelected) colors.backgroundBrandSubtle else colors.backgroundSurface
    val contentColor = if (item.isSelected) colors.textBrand else colors.textSecondary

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape)
            .background(bg)
            .clickable { onClick() }
            .padding(horizontal = 12.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = null,
                tint = contentColor,
                modifier = Modifier.size(20.dp)
            )

            Text(
                text = item.title,
                style = if (item.isSelected) typography.textSmBold else typography.textSmMedium,
                color = contentColor
            )
        }

        if (item.badge != null) {
            MeayarTag(
                text = item.badge,
                color = if (item.isSelected) MeayarTagColor.Primary else MeayarTagColor.Gray,
                hierarchy = MeayarTagHierarchy.Secondary,
                size = MeayarTagSize.Small
            )
        }
    }
}
