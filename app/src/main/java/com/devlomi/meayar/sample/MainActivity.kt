package com.devlomi.meayar.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.devlomi.meayar.sample.catalog.CatalogScreen
import com.devlomi.meayar.sample.catalog.FoundationsScreen
import com.devlomi.meayar.sample.catalog.HealthServicePortalScreen
import com.devlomi.meayar.sample.catalog.MeayarAuthScreen
import com.devlomi.meayar.components.MeayarButton
import com.devlomi.meayar.components.MeayarButtonSize
import com.devlomi.meayar.components.MeayarButtonVariant
import com.devlomi.meayar.components.MeayarDrawerItemData
import com.devlomi.meayar.components.MeayarNavigationBar
import com.devlomi.meayar.components.MeayarNavigationDrawer
import com.devlomi.meayar.components.MeayarNavigationItem
import com.devlomi.meayar.components.MeayarSnackbarHost
import com.devlomi.meayar.components.rememberMeayarDrawerState
import com.devlomi.meayar.icons.MeayarIcons
import com.devlomi.meayar.theme.MeayarTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var isDarkMode by rememberSaveable { mutableStateOf(false) }
            var isRtl by rememberSaveable { mutableStateOf(true) }
            var selectedBottomNav by rememberSaveable { mutableIntStateOf(0) }
            val snackbarHostState = remember { SnackbarHostState() }
            val drawerState = rememberMeayarDrawerState(DrawerValue.Closed)
            val coroutineScope = rememberCoroutineScope()

            CompositionLocalProvider(
                LocalLayoutDirection provides if (isRtl) LayoutDirection.Rtl else LayoutDirection.Ltr
            ) {
                MeayarTheme(darkTheme = isDarkMode) {
                    val colors = MeayarTheme.colors
                    val typography = MeayarTheme.typography

                    val drawerNavItems = listOf(
                        MeayarDrawerItemData(id = "portal", title = "بوابة وزارة الصحة", icon = MeayarIcons.Home, isSelected = selectedBottomNav == 0),
                        MeayarDrawerItemData(id = "catalog", title = "معرض المكوّنات (36+ مكوّن)", icon = MeayarIcons.Document, badge = "49 عنصر", isSelected = selectedBottomNav == 1),
                        MeayarDrawerItemData(id = "foundations", title = "الأساسيات والرموز (Tokens)", icon = MeayarIcons.Building, isSelected = selectedBottomNav == 2),
                        MeayarDrawerItemData(id = "auth", title = "الهوية الرقمية الموحدة", icon = MeayarIcons.User, isSelected = selectedBottomNav == 3)
                    )

                    // Root Navigation Drawer: draws OVER both Top App Bar and Bottom Navigation Bar
                    MeayarNavigationDrawer(
                        items = drawerNavItems,
                        onItemSelected = { item ->
                            when (item.id) {
                                "portal" -> selectedBottomNav = 0
                                "catalog" -> selectedBottomNav = 1
                                "foundations" -> selectedBottomNav = 2
                                "auth" -> selectedBottomNav = 3
                            }
                            coroutineScope.launch { drawerState.close() }
                        },
                        drawerState = drawerState,
                        userName = "حمزة الخطيب",
                        userNationalId = "01030094821",
                        onLogoutClick = {
                            selectedBottomNav = 3
                            coroutineScope.launch { drawerState.close() }
                        }
                    ) {
                        Scaffold(
                            modifier = Modifier.fillMaxSize(),
                            containerColor = colors.backgroundPage,
                            snackbarHost = { MeayarSnackbarHost(hostState = snackbarHostState) },
                            bottomBar = {
                                MeayarNavigationBar(
                                    items = listOf(
                                        MeayarNavigationItem(title = "البوابة", icon = MeayarIcons.Home),
                                        MeayarNavigationItem(title = "المكوّنات", icon = MeayarIcons.Document),
                                        MeayarNavigationItem(title = "الأساسيات", icon = MeayarIcons.Building),
                                        MeayarNavigationItem(title = "الهوية الرقمية", icon = MeayarIcons.User)
                                    ),
                                    selectedIndex = selectedBottomNav,
                                    onItemSelected = { selectedBottomNav = it }
                                )
                            }
                        ) { innerPadding ->
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(innerPadding)
                            ) {
                                // Quick Interactive Switcher Header (Light/Dark + RTL/LTR)
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(colors.backgroundSubtle)
                                        .padding(horizontal = 16.dp, vertical = 6.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(8.dp)
                                                .clip(CircleShape)
                                                .background(colors.backgroundBrand)
                                        )
                                        Text(
                                            text = "معيار MEAYAR Android",
                                            style = typography.textXsMedium,
                                            color = colors.textPrimary
                                        )
                                    }

                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        MeayarButton(
                                            onClick = { isDarkMode = !isDarkMode },
                                            size = MeayarButtonSize.XS,
                                            variant = MeayarButtonVariant.Secondary
                                        ) {
                                            Text(if (isDarkMode) "فاتح ☀️" else "داكن 🌙")
                                        }

                                        MeayarButton(
                                            onClick = { isRtl = !isRtl },
                                            size = MeayarButtonSize.XS,
                                            variant = MeayarButtonVariant.Outlined
                                        ) {
                                            Text(if (isRtl) "English LTR" else "عربي RTL")
                                        }
                                    }
                                }

                                // Active Screen
                                Box(modifier = Modifier.fillMaxSize()) {
                                    when (selectedBottomNav) {
                                        0 -> HealthServicePortalScreen(
                                            onOpenDrawer = { coroutineScope.launch { drawerState.open() } }
                                        )
                                        1 -> CatalogScreen(
                                            snackbarHostState = snackbarHostState,
                                            onOpenDrawer = { coroutineScope.launch { drawerState.open() } }
                                        )
                                        2 -> FoundationsScreen()
                                        3 -> MeayarAuthScreen(onLoginSuccess = { selectedBottomNav = 0 })
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
