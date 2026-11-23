package com.devlomi.meayar.sample.catalog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.devlomi.meayar.components.MeayarAlert
import com.devlomi.meayar.components.MeayarAlertStyle
import com.devlomi.meayar.components.MeayarToast
import com.devlomi.meayar.components.MeayarToastType
import com.devlomi.meayar.components.MeayarDialog
import com.devlomi.meayar.components.MeayarOfficialBanner
import com.devlomi.meayar.components.MeayarServiceCard
import com.devlomi.meayar.components.MeayarServiceMeta
import com.devlomi.meayar.components.MeayarTabs
import com.devlomi.meayar.components.MeayarTabStyle
import com.devlomi.meayar.components.MeayarTagColor
import com.devlomi.meayar.components.MeayarTextField
import com.devlomi.meayar.components.MeayarTopAppBar
import com.devlomi.meayar.icons.MeayarIcons
import com.devlomi.meayar.theme.MeayarTheme

/**
 * Syrian Ministry of Health (وزارة الصحة) Service Portal Starter Template.
 * Official service portal screen demonstrating MEAYAR components.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HealthServicePortalScreen(
    modifier: Modifier = Modifier,
    onOpenDrawer: (() -> Unit)? = null
) {
    val colors = MeayarTheme.colors
    val typography = MeayarTheme.typography

    var searchQuery by rememberSaveable { mutableStateOf("") }
    var selectedFilterTab by rememberSaveable { mutableIntStateOf(0) }
    var selectedServiceForApplication by rememberSaveable { mutableStateOf<String?>(null) }

    val filterTabs = listOf("الكل", "المواعيد", "الشهادات", "اللقاحات", "الشكاوى")

    // Form inputs state for service application dialog
    var nationalId by rememberSaveable { mutableStateOf("") }
    var phoneNumber by rememberSaveable { mutableStateOf("") }
    var isSubmitted by rememberSaveable { mutableStateOf(false) }

    Box(modifier = modifier.fillMaxSize().background(colors.backgroundPage)) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 96.dp)
        ) {
            // Official Syrian Banner
            item {
                MeayarOfficialBanner()
            }

            // Top App Bar
            item {
                MeayarTopAppBar(
                    title = "وزارة الصحة",
                    subtitle = "الجمهورية العربية السورية — بوابة الرعاية الصحية",
                    onNavigationClick = onOpenDrawer,
                    navigationIcon = {
                        Icon(
                            imageVector = MeayarIcons.Menu,
                            contentDescription = "القائمة الرئيسية",
                            tint = colors.iconPrimary
                        )
                    }
                )
            }

            // Hero Section & Search
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                ) {
                    Text(
                        text = "دليل الخدمات الصحية الموحد",
                        style = typography.headingSm,
                        color = colors.textPrimary
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "احصل على خدمات المشافي، الرعاية الأولية، والشهادات الطبية الرسمية إلكترونياً.",
                        style = typography.textSm,
                        color = colors.textSecondary
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Mobile Search Bar
                    MeayarTextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        placeholder = "ابحث عن خدمة، لقاح، مركز صحي، أو مستشفى...",
                        leadingIcon = {
                            Icon(
                                imageVector = MeayarIcons.Search,
                                contentDescription = null,
                                tint = colors.iconSecondary,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    )
                }
            }

            // Quick Service Filter Tabs (Pill style)
            item {
                Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)) {
                    MeayarTabs(
                        tabs = filterTabs,
                        selectedIndex = selectedFilterTab,
                        onTabSelected = { selectedFilterTab = it },
                        style = MeayarTabStyle.Pill
                    )
                }
            }

            // Urgent Notice Banner
            item {
                Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                    MeayarAlert(
                        title = "تنويه رسمي",
                        description = "تم تفعيل حجز المواعيد المسبقة للعيادات الخارجية والمشافي العامة في دمشق وحلب.",
                        style = MeayarAlertStyle.Secondary
                    )
                }
            }

            // Section Header: Available Public Services
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "الخدمات الإلكترونية المتاحة",
                        style = typography.heading2xs,
                        color = colors.textPrimary
                    )
                    Text(
                        text = "3 خدمات متاحة",
                        style = typography.textXsMedium,
                        color = colors.textBrand
                    )
                }
            }

            // Service Card 1: Medical Appointment Booking
            item {
                Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)) {
                    MeayarServiceCard(
                        title = "حجز موعد طبي في المراكز الصحية",
                        description = "خدمة لحجز مواعيد الفحص السريري، والعيادات التخصصية، ومتابعة الأمراض المزمنة.",
                        tagText = "خدمة فورية",
                        tagColor = MeayarTagColor.Green,
                        icon = MeayarIcons.Calendar,
                        metaList = listOf(
                            MeayarServiceMeta(icon = MeayarIcons.Clock, label = "المدة", value = "فوري"),
                            MeayarServiceMeta(icon = MeayarIcons.Document, label = "الرسوم", value = "مجاني"),
                            MeayarServiceMeta(icon = MeayarIcons.Building, label = "القناة", value = "إلكتروني")
                        ),
                        onStartService = {
                            selectedServiceForApplication = "حجز موعد طبي في المراكز الصحية"
                            isSubmitted = false
                        }
                    )
                }
            }

            // Service Card 2: International Health Certificate
            item {
                Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)) {
                    MeayarServiceCard(
                        title = "إصدار الشهادة الصحية الدولية",
                        description = "إصدار وتوثيق شهادة الخلو من الأمراض السارية وبطاقات التطعيم المعتمدة دولياً.",
                        tagText = "تتطلب فحص",
                        tagColor = MeayarTagColor.Gold,
                        icon = MeayarIcons.Document,
                        metaList = listOf(
                            MeayarServiceMeta(icon = MeayarIcons.Clock, label = "المدة", value = "يومان عمل"),
                            MeayarServiceMeta(icon = MeayarIcons.Document, label = "الرسوم", value = "25,000 ل.س"),
                            MeayarServiceMeta(icon = MeayarIcons.Building, label = "القناة", value = "حضور شخصي")
                        ),
                        onStartService = {
                            selectedServiceForApplication = "إصدار الشهادة الصحية الدولية"
                            isSubmitted = false
                        }
                    )
                }
            }

            // Service Card 3: Complaints and Inquiries
            item {
                Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)) {
                    MeayarServiceCard(
                        title = "منظومة الشكاوى والمقترحات الصحية",
                        description = "تقديم ومتابعة الشكاوى المتعلقة بالخدمات الطبية، المرافق الصحية، وتوافر الأدوية.",
                        tagText = "متاح 24/7",
                        tagColor = MeayarTagColor.Blue,
                        icon = MeayarIcons.Info,
                        metaList = listOf(
                            MeayarServiceMeta(icon = MeayarIcons.Clock, label = "المدة", value = "48 ساعة"),
                            MeayarServiceMeta(icon = MeayarIcons.Document, label = "الرسوم", value = "مجاني"),
                            MeayarServiceMeta(icon = MeayarIcons.Building, label = "القناة", value = "إلكتروني")
                        ),
                        onStartService = {
                            selectedServiceForApplication = "منظومة الشكاوى والمقترحات الصحية"
                            isSubmitted = false
                        }
                    )
                }
            }
        }

        // Service Application Modal Dialog (Replaces Bottom Sheet)
        if (selectedServiceForApplication != null) {
            MeayarDialog(
                onDismissRequest = {
                    selectedServiceForApplication = null
                    isSubmitted = false
                },
                title = selectedServiceForApplication ?: "تقديم طلب الخدمة",
                description = if (!isSubmitted) "يرجى تعبئة البيانات المطلوبة لبدء معاملتك الرسمية." else null,
                icon = if (!isSubmitted) MeayarIcons.Document else MeayarIcons.CheckCircle,
                confirmText = if (!isSubmitted) "متابعة الطلب" else "إغلاق",
                cancelText = if (!isSubmitted) "إلغاء" else null,
                onConfirm = {
                    if (!isSubmitted) {
                        if (nationalId.isNotEmpty() && phoneNumber.isNotEmpty()) {
                            isSubmitted = true
                        }
                    } else {
                        selectedServiceForApplication = null
                        isSubmitted = false
                        nationalId = ""
                        phoneNumber = ""
                    }
                },
                onCancel = {
                    selectedServiceForApplication = null
                    isSubmitted = false
                }
            ) {
                if (!isSubmitted) {
                    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                        MeayarTextField(
                            value = nationalId,
                            onValueChange = { nationalId = it },
                            label = "الرقم الوطني",
                            isMandatory = true,
                            placeholder = "11 رقماً — مثال: 01010101010",
                            forceLtrContent = true,
                            leadingIcon = {
                                Icon(
                                    imageVector = MeayarIcons.User,
                                    contentDescription = null,
                                    tint = colors.iconSecondary,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        )

                        MeayarTextField(
                            value = phoneNumber,
                            onValueChange = { phoneNumber = it },
                            label = "رقم الهاتف المحمول",
                            isMandatory = true,
                            placeholder = "09xxxxxxxx",
                            forceLtrContent = true,
                            helperText = "سيصلك رمز تحقق عبر رسالة نصية قصيرة SMS",
                            leadingIcon = {
                                Icon(
                                    imageVector = MeayarIcons.Phone,
                                    contentDescription = null,
                                    tint = colors.iconSecondary,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        )
                    }
                } else {
                    MeayarToast(
                        title = "تم استلام الطلب بنجاح",
                        description = "تم قيد طلبك برقم مرجعي #SY-94821. سنرسل إشعاراً عند استكمال المعالجة.",
                        type = MeayarToastType.Success
                    )
                }
            }
        }
    }
}
