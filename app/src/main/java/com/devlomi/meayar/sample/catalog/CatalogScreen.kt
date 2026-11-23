package com.devlomi.meayar.sample.catalog

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import kotlinx.coroutines.launch
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.devlomi.meayar.components.MeayarAccordion
import com.devlomi.meayar.components.MeayarAccessibilityToolbar
import com.devlomi.meayar.components.MeayarAlert
import com.devlomi.meayar.components.MeayarAlertStyle
import com.devlomi.meayar.components.MeayarAttachedFileRow
import com.devlomi.meayar.components.MeayarAvatar
import com.devlomi.meayar.components.MeayarAvatarSize
import com.devlomi.meayar.components.MeayarBadge
import com.devlomi.meayar.components.MeayarBreadcrumbs
import com.devlomi.meayar.components.MeayarButton
import com.devlomi.meayar.components.MeayarButtonGroup
import com.devlomi.meayar.components.MeayarButtonGroupItem
import com.devlomi.meayar.components.MeayarSegmentedButton
import com.devlomi.meayar.components.MeayarButtonSize
import com.devlomi.meayar.components.MeayarButtonVariant
import com.devlomi.meayar.components.MeayarCalendar
import com.devlomi.meayar.components.MeayarCalendarType
import com.devlomi.meayar.components.MeayarCard
import com.devlomi.meayar.components.MeayarCheckbox
import com.devlomi.meayar.components.MeayarColumn
import com.devlomi.meayar.components.MeayarCookieConsent
import com.devlomi.meayar.components.MeayarDatePicker
import com.devlomi.meayar.components.MeayarDialog
import com.devlomi.meayar.components.MeayarDivider
import com.devlomi.meayar.components.MeayarVerticalDivider
import com.devlomi.meayar.components.MeayarDocumentCard
import com.devlomi.meayar.components.MeayarDrawerContent
import com.devlomi.meayar.components.MeayarDrawerItemData
import com.devlomi.meayar.components.MeayarEmptyState
import com.devlomi.meayar.components.MeayarEmptyStateType
import com.devlomi.meayar.components.MeayarFileItem
import com.devlomi.meayar.components.MeayarFileUpload
import com.devlomi.meayar.components.MeayarIconButton
import com.devlomi.meayar.components.MeayarOfficialBanner
import com.devlomi.meayar.components.MeayarOtpInput
import com.devlomi.meayar.components.MeayarPageFeedback
import com.devlomi.meayar.components.MeayarPagination
import com.devlomi.meayar.components.MeayarProgressBar
import com.devlomi.meayar.components.MeayarPresenceStatus
import com.devlomi.meayar.components.MeayarRadioButton
import com.devlomi.meayar.components.MeayarRating
import com.devlomi.meayar.components.MeayarSelect
import com.devlomi.meayar.components.MeayarServiceCard
import com.devlomi.meayar.components.MeayarServiceMeta
import com.devlomi.meayar.components.MeayarSkeleton
import com.devlomi.meayar.components.MeayarSlider
import com.devlomi.meayar.components.MeayarSnackbar
import com.devlomi.meayar.components.MeayarSnackbarStyle
import com.devlomi.meayar.components.showMeayarSnackbar
import com.devlomi.meayar.components.MeayarSpinner
import com.devlomi.meayar.components.MeayarSpinnerSize
import com.devlomi.meayar.components.MeayarStatCard
import com.devlomi.meayar.components.MeayarStep
import com.devlomi.meayar.components.MeayarStepper
import com.devlomi.meayar.components.MeayarSwitch
import com.devlomi.meayar.components.MeayarTable
import com.devlomi.meayar.components.MeayarTabs
import com.devlomi.meayar.components.MeayarTabStyle
import com.devlomi.meayar.components.MeayarTag
import com.devlomi.meayar.components.MeayarTagColor
import com.devlomi.meayar.components.MeayarTagHierarchy
import com.devlomi.meayar.components.MeayarTagSize
import com.devlomi.meayar.components.MeayarTextField
import com.devlomi.meayar.components.MeayarToast
import com.devlomi.meayar.components.MeayarToastType
import com.devlomi.meayar.components.MeayarTooltip
import com.devlomi.meayar.components.MeayarTooltipBox
import com.devlomi.meayar.components.MeayarTopAppBar
import com.devlomi.meayar.icons.MeayarIcons
import com.devlomi.meayar.theme.MeayarTheme

/**
 * Interactive Component Catalog for MEAYAR Design System.
 * Complete implementation of all 36 components.
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CatalogScreen(
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState? = null,
    onOpenDrawer: (() -> Unit)? = null
) {
    val colors = MeayarTheme.colors
    val typography = MeayarTheme.typography
    val coroutineScope = rememberCoroutineScope()

    // State for interactive demos
    var inputDefault by rememberSaveable { mutableStateOf("") }
    var inputPhone by rememberSaveable { mutableStateOf("0933123456") }
    var inputTextarea by rememberSaveable { mutableStateOf("") }
    var selectedDateMillis by rememberSaveable { mutableStateOf<Long?>(null) }
    var selectedHijriDateMillis by rememberSaveable { mutableStateOf<Long?>(null) }
    var inputError by rememberSaveable { mutableStateOf("wrong-input") }
    var tabIndexLine by rememberSaveable { mutableIntStateOf(0) }
    var tabIndexPill by rememberSaveable { mutableIntStateOf(1) }
    // Button Loading & Group State
    var buttonLoading by rememberSaveable { mutableStateOf(false) }
    var selectedButtonGroupIndex by rememberSaveable { mutableIntStateOf(0) }

    // Cookie Consent State
    var showCookieConsentDemo by rememberSaveable { mutableStateOf(true) }

    // Controls State
    var checkboxState1 by rememberSaveable { mutableStateOf(true) }
    var checkboxState2 by rememberSaveable { mutableStateOf(false) }
    var selectedRadio by rememberSaveable { mutableIntStateOf(0) }
    var switchState1 by rememberSaveable { mutableStateOf(true) }
    var switchState2 by rememberSaveable { mutableStateOf(false) }

    // Select Dropdown State
    val governorates = listOf("دمشق", "ريف دمشق", "حلب", "حمص", "حماة", "اللاذقية", "طرطوس")
    var selectedGovernorate by rememberSaveable { mutableStateOf<String?>("دمشق") }

    // OTP State
    var otpCode by rememberSaveable { mutableStateOf("4821") }

    // Rating & Slider State
    var userRating by rememberSaveable { mutableIntStateOf(4) }
    var sliderValue by rememberSaveable { mutableFloatStateOf(0.7f) }

    // Stepper & Pagination State
    var currentStepperIndex by rememberSaveable { mutableIntStateOf(1) }
    var currentPageNumber by rememberSaveable { mutableIntStateOf(2) }

    // Modal Dialog State
    var showDialog by rememberSaveable { mutableStateOf(false) }

    // Progress State
    var progressVal by rememberSaveable { mutableFloatStateOf(0.65f) }

    // File Upload State
    var uploadedFiles by remember {
        mutableStateOf(
            listOf(
                MeayarFileItem(id = "1", name = "البطاقة_الشخصية_الوجه_الأول.pdf", sizeString = "2.4 MB", progress = 1f),
                MeayarFileItem(id = "2", name = "سند_إقامة_معتمد.jpg", sizeString = "1.1 MB", progress = 0.65f)
            )
        )
    }

    // Calendar State
    var calYear by rememberSaveable { mutableIntStateOf(2026) }
    var calMonth by rememberSaveable { mutableIntStateOf(10) }
    var calDay by rememberSaveable { mutableIntStateOf(15) }
    var selectedCalendarTypeIndex by rememberSaveable { mutableIntStateOf(0) }

    // Accessibility Toolbar State
    var isHighContrastDemo by rememberSaveable { mutableStateOf(false) }

    // Drawer Selected Item State
    var selectedDrawerItem by rememberSaveable { mutableStateOf("services") }

    val drawerNavItems = listOf(
        MeayarDrawerItemData(id = "home", title = "الرئيسية", icon = MeayarIcons.Home, isSelected = selectedDrawerItem == "home"),
        MeayarDrawerItemData(id = "services", title = "دليل المعاملات", icon = MeayarIcons.Document, badge = "12 خدمة", isSelected = selectedDrawerItem == "services"),
        MeayarDrawerItemData(id = "calendar", title = "مواعيدي", icon = MeayarIcons.Calendar, badge = "موعد جديد", isSelected = selectedDrawerItem == "calendar"),
        MeayarDrawerItemData(id = "settings", title = "إعدادات الحساب", icon = MeayarIcons.User, isSelected = selectedDrawerItem == "settings")
    )

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(colors.backgroundPage),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 96.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // Title
        item {
            Column {
                Text(
                    text = "معرض مكوّنات معيار الشامل",
                    style = typography.headingMd,
                    color = colors.textPrimary
                )
                Text(
                    text = "المكتبة الكاملة لجميع مكوّنات الهوية الرقمية السورية (MEAYAR Compose) مع التوافق التام للغة العربية والوضع الداكن.",
                    style = typography.textSm,
                    color = colors.textSecondary
                )
            }
        }

        // 0. Official Headers & App Bars
        item {
            MeayarCard {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(
                        text = "الترويسات والأشرطة العُلوية الرسمية (Official Headers & App Bars)",
                        style = typography.heading2xs,
                        color = colors.textPrimary
                    )

                    Text(
                        text = "الشريط الحكومي الموحد (Official Banner):",
                        style = typography.textSmMedium,
                        color = colors.textSecondary
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .border(1.dp, colors.borderSubtle, RoundedCornerShape(8.dp))
                    ) {
                        MeayarOfficialBanner()
                    }

                    Text(
                        text = "شريط التطبيق العُلوي (Top App Bar):",
                        style = typography.textSmMedium,
                        color = colors.textSecondary
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .border(1.dp, colors.borderSubtle, RoundedCornerShape(8.dp))
                    ) {
                        MeayarTopAppBar(
                            title = "بوابة المعاملات الحكومية",
                            onNavigationClick = onOpenDrawer,
                            navigationIcon = {
                                Icon(
                                    imageVector = MeayarIcons.Menu,
                                    contentDescription = "القائمة الجانبية",
                                    tint = colors.iconPrimary,
                                    modifier = Modifier.size(22.dp)
                                )
                            },
                            actions = {
                                MeayarIconButton(onClick = {}) {
                                    Icon(
                                        imageVector = MeayarIcons.Search,
                                        contentDescription = "بحث",
                                        tint = colors.iconPrimary,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                                MeayarIconButton(onClick = {}) {
                                    Icon(
                                        imageVector = MeayarIcons.HelpOutline,
                                        contentDescription = "المساعدة",
                                        tint = colors.iconPrimary,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            }
                        )
                    }
                }
            }
        }

        // 1. Breadcrumbs
        item {
            MeayarCard {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(text = "مسار التصفح (Breadcrumbs):", style = typography.textSmMedium, color = colors.textSecondary)
                    MeayarBreadcrumbs(
                        items = listOf("الرئيسية", "الخدمات الإلكترونية", "وزارة الصحة", "حجز موعد"),
                        onItemClick = {}
                    )
                }
            }
        }

        // 2. Tabs Section (Line & Pill)
        item {
            MeayarCard {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(
                        text = "التبويبات (Tabs)",
                        style = typography.heading2xs,
                        color = colors.textPrimary
                    )

                    Text(text = "نمط الخط (Line):", style = typography.textSmMedium, color = colors.textSecondary)
                    MeayarTabs(
                        tabs = listOf("نظرة عامة", "الطلبات الحالية", "الأرشيف"),
                        selectedIndex = tabIndexLine,
                        onTabSelected = { tabIndexLine = it },
                        style = MeayarTabStyle.Line
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(text = "نمط الكبسولة (Pill):", style = typography.textSmMedium, color = colors.textSecondary)
                    MeayarTabs(
                        tabs = listOf("يومي", "أسبوعي", "شهري", "سنوي"),
                        selectedIndex = tabIndexPill,
                        onTabSelected = { tabIndexPill = it },
                        style = MeayarTabStyle.Pill
                    )
                }
            }
        }

        // 3. Tags & Badges Section (All 7 colors and 3 hierarchies)
        item {
            MeayarCard {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(
                        text = "الشارات والتصنيفات (Tags & Badges)",
                        style = typography.heading2xs,
                        color = colors.textPrimary
                    )

                    Text(text = "الألوان الرسمية السبعة (Secondary):", style = typography.textSmMedium, color = colors.textSecondary)
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        MeayarTag(text = "أخضر سوري (Primary)", color = MeayarTagColor.Primary)
                        MeayarTag(text = "مكتمل (Green)", color = MeayarTagColor.Green)
                        MeayarTag(text = "عاجل (Red)", color = MeayarTagColor.Red)
                        MeayarTag(text = "قيد المعالجة (Yellow)", color = MeayarTagColor.Yellow)
                        MeayarTag(text = "مميّز (Gold)", color = MeayarTagColor.Gold)
                        MeayarTag(text = "معلومات (Blue)", color = MeayarTagColor.Blue)
                        MeayarTag(text = "مسودة (Gray)", color = MeayarTagColor.Gray)
                    }

                    Text(text = "مستويات الظهور (Filled, Secondary, Outlined):", style = typography.textSmMedium, color = colors.textSecondary)
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        MeayarTag(text = "ممتلئ Filled", color = MeayarTagColor.Primary, hierarchy = MeayarTagHierarchy.Filled)
                        MeayarTag(text = "ثانوي Secondary", color = MeayarTagColor.Primary, hierarchy = MeayarTagHierarchy.Secondary)
                        MeayarTag(text = "محدد Outlined", color = MeayarTagColor.Primary, hierarchy = MeayarTagHierarchy.Outlined)
                    }
                }
            }
        }

        // 4. Buttons & Icon-Only Buttons
        item {
            MeayarCard {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(
                        text = "الأزرار وأزرار الأيقونات (Buttons & Icon Buttons)",
                        style = typography.heading2xs,
                        color = colors.textPrimary
                    )

                    Text(text = "الأنماط (Hierarchy):", style = typography.textSmMedium, color = colors.textSecondary)
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        MeayarButton(onClick = {}, variant = MeayarButtonVariant.Primary) { Text("رئيسي Primary") }
                        MeayarButton(onClick = {}, variant = MeayarButtonVariant.Secondary) { Text("ثانوي Secondary") }
                        MeayarButton(onClick = {}, variant = MeayarButtonVariant.Outlined) { Text("محدد Outlined") }
                        MeayarButton(onClick = {}, variant = MeayarButtonVariant.Ghost) { Text("شفاف Ghost") }
                        MeayarButton(onClick = {}, variant = MeayarButtonVariant.Destructive) { Text("تحذيري Destructive") }
                    }

                    Text(text = "الأحجام (XL..XS):", style = typography.textSmMedium, color = colors.textSecondary)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        MeayarButton(onClick = {}, size = MeayarButtonSize.XL) { Text("XL 60") }
                        MeayarButton(onClick = {}, size = MeayarButtonSize.LG) { Text("LG 48") }
                        MeayarButton(onClick = {}, size = MeayarButtonSize.MD) { Text("MD 44") }
                        MeayarButton(onClick = {}, size = MeayarButtonSize.SM) { Text("SM 32") }
                        MeayarButton(onClick = {}, size = MeayarButtonSize.XS) { Text("XS") }
                    }

                    Text(text = "أزرار الأيقونات فقط (Icon Only):", style = typography.textSmMedium, color = colors.textSecondary)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        MeayarIconButton(onClick = {}, size = MeayarButtonSize.XL, variant = MeayarButtonVariant.Primary) {
                            Icon(MeayarIcons.Search, contentDescription = null)
                        }
                        MeayarIconButton(onClick = {}, size = MeayarButtonSize.LG, variant = MeayarButtonVariant.Secondary) {
                            Icon(MeayarIcons.Add, contentDescription = null)
                        }
                        MeayarIconButton(onClick = {}, size = MeayarButtonSize.MD, variant = MeayarButtonVariant.Outlined) {
                            Icon(MeayarIcons.Check, contentDescription = null)
                        }
                        MeayarIconButton(onClick = {}, size = MeayarButtonSize.SM, variant = MeayarButtonVariant.Ghost) {
                            Icon(MeayarIcons.Close, contentDescription = null)
                        }
                    }

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        MeayarButton(
                            onClick = { buttonLoading = !buttonLoading },
                            loading = buttonLoading,
                            variant = MeayarButtonVariant.Primary,
                            leadingIcon = { Icon(MeayarIcons.Add, contentDescription = null, tint = colors.textInverse) }
                        ) {
                            Text("انقر لتفعيل التحميل")
                        }

                        MeayarButton(onClick = {}, enabled = false) {
                            Text("معطل (Disabled)")
                        }
                    }
                }
            }
        }

        // 4b. Button Groups
        item {
            MeayarCard {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(
                        text = "مجموعات الأزرار المتصلة (Button Groups)",
                        style = typography.heading2xs,
                        color = colors.textPrimary
                    )
                    Text(
                        text = "أزرار مجمعة متصلة للحالات المتعددة والتنقل السريع مع حواف خارجية منحنية تلقائياً حسب اتجاه النص العربي/الإنجليزي:",
                        style = typography.textSm,
                        color = colors.textSecondary
                    )

                    Text(text = "مجموعة خيارات أحادية (Single Selection - SegmentedButton):", style = typography.textSmMedium, color = colors.textSecondary)
                    MeayarSegmentedButton(
                        items = listOf(
                            MeayarButtonGroupItem("يومي", leadingIcon = { Icon(MeayarIcons.Calendar, contentDescription = null, tint = if (selectedButtonGroupIndex == 0) colors.textInverse else colors.iconBrand, modifier = Modifier.size(16.dp)) }),
                            MeayarButtonGroupItem("أسبوعي", leadingIcon = { Icon(MeayarIcons.Clock, contentDescription = null, tint = if (selectedButtonGroupIndex == 1) colors.textInverse else colors.iconBrand, modifier = Modifier.size(16.dp)) }),
                            MeayarButtonGroupItem("شهري", leadingIcon = { Icon(MeayarIcons.Document, contentDescription = null, tint = if (selectedButtonGroupIndex == 2) colors.textInverse else colors.iconBrand, modifier = Modifier.size(16.dp)) })
                        ),
                        selectedIndex = selectedButtonGroupIndex,
                        onItemSelected = { selectedButtonGroupIndex = it },
                        size = MeayarButtonSize.MD
                    )

                    Text(text = "مجموعة إجراءات مستقلة (Actions):", style = typography.textSmMedium, color = colors.textSecondary)
                    MeayarButtonGroup(
                        items = listOf(
                            MeayarButtonGroupItem("معاينة", leadingIcon = { Icon(MeayarIcons.Search, contentDescription = null, tint = colors.iconBrand, modifier = Modifier.size(16.dp)) }),
                            MeayarButtonGroupItem("تحميل", leadingIcon = { Icon(MeayarIcons.Upload, contentDescription = null, tint = colors.iconBrand, modifier = Modifier.size(16.dp)) }),
                            MeayarButtonGroupItem("مشاركة", leadingIcon = { Icon(MeayarIcons.ArrowForward, contentDescription = null, tint = colors.iconBrand, modifier = Modifier.size(16.dp)) })
                        ),
                        onItemSelected = {},
                        size = MeayarButtonSize.SM,
                        variant = MeayarButtonVariant.Secondary
                    )
                }
            }
        }

        // 5. Inputs & Textarea Section
        item {
            MeayarCard {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(
                        text = "حقول الإدخال ومربعات النصوص (Inputs & Textarea)",
                        style = typography.heading2xs,
                        color = colors.textPrimary
                    )

                    MeayarTextField(
                        value = inputDefault,
                        onValueChange = { inputDefault = it },
                        label = "الاسم الكامل",
                        isMandatory = true,
                        placeholder = "أدخل اسمك الثلاثي كما في الهوية...",
                        helperText = "يجب أن يتطابق الاسم مع قيود السجل المدني",
                        leadingIcon = { Icon(MeayarIcons.User, contentDescription = null, tint = colors.iconSecondary) }
                    )

                    MeayarTextField(
                        value = inputPhone,
                        onValueChange = { inputPhone = it },
                        label = "رقم الهاتف المحمول (LTR في نموذج RTL)",
                        isMandatory = true,
                        forceLtrContent = true,
                        placeholder = "+963 9xxxxxxxx",
                        helperText = "يتم الحفاظ على اتجاه الأرقام من اليسار لليمين",
                        leadingIcon = { Icon(MeayarIcons.Phone, contentDescription = null, tint = colors.iconSecondary) }
                    )

                    MeayarTextField(
                        value = inputTextarea,
                        onValueChange = { inputTextarea = it },
                        label = "الملاحظات الإضافية (Textarea / Multiline)",
                        placeholder = "اكتب تفاصيل المعاملة هنا...",
                        singleLine = false,
                        minLines = 3
                    )

                    MeayarDatePicker(
                        selectedDateMillis = selectedDateMillis,
                        onDateSelected = { selectedDateMillis = it },
                        label = "تاريخ المعاملة (ميلادي - Gregorian Date Picker)",
                        isMandatory = true
                    )

                    MeayarDatePicker(
                        selectedDateMillis = selectedHijriDateMillis,
                        onDateSelected = { selectedHijriDateMillis = it },
                        label = "تاريخ المعاملة (هجري - Hijri Date Picker)",
                        calendarType = MeayarCalendarType.Hijri,
                        placeholder = "اختر التاريخ الهجري..."
                    )

                    MeayarTextField(
                        value = inputError,
                        onValueChange = { inputError = it },
                        label = "البريد الإلكتروني",
                        isError = true,
                        errorMessage = "صيغة البريد الإلكتروني غير صالحة",
                        leadingIcon = { Icon(MeayarIcons.Warning, contentDescription = null, tint = colors.textError) }
                    )
                }
            }
        }

        // 6. OTP Input Section
        item {
            MeayarCard {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(
                        text = "رمز التحقق (OTP Input)",
                        style = typography.heading2xs,
                        color = colors.textPrimary
                    )
                    Text(
                        text = "حقل إدخال رمز التحقق الرقمي مع محاذاة LTR واستجابة فورية للوحة المفاتيح:",
                        style = typography.textSm,
                        color = colors.textSecondary
                    )

                    Box(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), contentAlignment = Alignment.Center) {
                        MeayarOtpInput(
                            otpValue = otpCode,
                            onOtpChange = { otpCode = it },
                            length = 4
                        )
                    }

                    Text(
                        text = "الرمز المدخل حالياً: $otpCode",
                        style = typography.textXsMedium,
                        color = colors.textBrand
                    )
                }
            }
        }

        // 7. Stepper Section
        item {
            MeayarCard {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(
                        text = "مؤشر الخطوات (Stepper)",
                        style = typography.heading2xs,
                        color = colors.textPrimary
                    )

                    MeayarStepper(
                        steps = listOf(
                            MeayarStep("بيانات المتقدم"),
                            MeayarStep("حجز الموعد"),
                            MeayarStep("دفع الرسوم"),
                            MeayarStep("تأكيد الطلب")
                        ),
                        currentStepIndex = currentStepperIndex
                    )

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        MeayarButton(
                            onClick = { currentStepperIndex = (currentStepperIndex - 1).coerceAtLeast(0) },
                            size = MeayarButtonSize.SM,
                            variant = MeayarButtonVariant.Outlined
                        ) {
                            Text("الخطوة السابقة")
                        }
                        MeayarButton(
                            onClick = { currentStepperIndex = (currentStepperIndex + 1).coerceAtMost(3) },
                            size = MeayarButtonSize.SM,
                            variant = MeayarButtonVariant.Primary
                        ) {
                            Text("الخطوة التالية")
                        }
                    }
                }
            }
        }

        // 8. Service Card Demonstration
        item {
            MeayarCard {
                Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                    Text(
                        text = "بطاقة الخدمة الحكومية (Service Card)",
                        style = typography.heading2xs,
                        color = colors.textPrimary
                    )

                    MeayarServiceCard(
                        title = "تجديد البطاقة الشخصية إلكترونياً",
                        description = "خدمة للمواطنين داخل القطر لتجديد الهوية المدنية مع التوصيل إلى العنوان المعتمد.",
                        tagText = "خدمة شائعة",
                        tagColor = MeayarTagColor.Primary,
                        icon = MeayarIcons.User,
                        metaList = listOf(
                            MeayarServiceMeta(icon = MeayarIcons.Clock, label = "المدة", value = "5 أيام"),
                            MeayarServiceMeta(icon = MeayarIcons.Document, label = "الرسوم", value = "10,000 ل.س"),
                            MeayarServiceMeta(icon = MeayarIcons.Building, label = "القناة", value = "إلكتروني")
                        ),
                        onStartService = {}
                    )
                }
            }
        }

        // 9. Stat Cards Section
        item {
            MeayarCard {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(
                        text = "بطاقات الإحصائيات (Stat Cards)",
                        style = typography.heading2xs,
                        color = colors.textPrimary
                    )

                    // Full-width KPI Card
                    MeayarStatCard(
                        title = "إجمالي المعاملات الحكومية المنجزة",
                        value = "142,850",
                        description = "محدثة حتى اليوم، مقارنة بإحصائيات الشهر الماضي",
                        delta = "+14.8%",
                        isPositiveDelta = true,
                        icon = MeayarIcons.Document
                    )

                    // Side-by-side Compact KPI Grid
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        MeayarStatCard(
                            title = "المعاملات",
                            value = "142K",
                            delta = "+14%",
                            isPositiveDelta = true,
                            icon = MeayarIcons.Document,
                            modifier = Modifier.weight(1f)
                        )

                        MeayarStatCard(
                            title = "قيد المعالجة",
                            value = "1,420",
                            delta = "-3.2%",
                            isPositiveDelta = false,
                            icon = MeayarIcons.Clock,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }

        // 10. Select Dropdown Menu
        item {
            MeayarCard {
                Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                    Text(
                        text = "القوائم المنسدلة (Select / Dropdown)",
                        style = typography.heading2xs,
                        color = colors.textPrimary
                    )

                    MeayarSelect(
                        items = governorates,
                        selectedItem = selectedGovernorate,
                        onItemSelected = { selectedGovernorate = it },
                        itemLabel = { it },
                        label = "المحافظة",
                        isMandatory = true,
                        placeholder = "اختر المحافظة..."
                    )
                }
            }
        }

        // 11. Selection Controls (Checkbox, Radio, Switch)
        item {
            MeayarCard {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(
                        text = "عناصر الاختيار والتحكم (Form Controls)",
                        style = typography.heading2xs,
                        color = colors.textPrimary
                    )

                    MeayarCheckbox(
                        checked = checkboxState1,
                        onCheckedChange = { checkboxState1 = it },
                        label = "أوافق على الشروط والأحكام الرسمية",
                        description = "يتطلب تفعيل هذا الخيار موافقة المستخدم على سياسة الخصوصية"
                    )
                    MeayarCheckbox(
                        checked = checkboxState2,
                        onCheckedChange = { checkboxState2 = it },
                        label = "استلام الإشعارات عبر الرسائل النصية القصيرة"
                    )

                    MeayarDivider()

                    MeayarRadioButton(
                        selected = selectedRadio == 0,
                        onClick = { selectedRadio = 0 },
                        label = "الدفع الإلكتروني عبر الحساب البنكي الموحد",
                        description = "خصم مباشر من الحساب المصرفي دون رسوم إضافية"
                    )
                    MeayarRadioButton(
                        selected = selectedRadio == 1,
                        onClick = { selectedRadio = 1 },
                        label = "الدفع نقداً عند استلام المعاملة",
                        description = "يتطلب الحضور إلى مركز الخدمة خلال 3 أيام عمل"
                    )

                    MeayarDivider()

                    MeayarSwitch(
                        checked = switchState1,
                        onCheckedChange = { switchState1 = it },
                        label = "التحقق بخطوتين (2FA)",
                        description = "طلب رمز OTP إضافي عند تسجيل الدخول"
                    )
                }
            }
        }

        // 12. Rating & Slider Section
        item {
            MeayarCard {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(
                        text = "التقييم وأشرطة التمرير (Rating & Slider)",
                        style = typography.heading2xs,
                        color = colors.textPrimary
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "تقييم جودة الخدمة:", style = typography.textSmMedium, color = colors.textSecondary)
                        MeayarRating(
                            rating = userRating,
                            onRatingChange = { userRating = it }
                        )
                    }

                    MeayarDivider()

                    Column {
                        Text(
                            text = "نسبة الإنجاز المطلوبة: ${(sliderValue * 100).toInt()}%",
                            style = typography.textSmMedium,
                            color = colors.textSecondary
                        )
                        MeayarSlider(
                            value = sliderValue,
                            onValueChange = { sliderValue = it }
                        )
                    }
                }
            }
        }

        // 13. Dividers (Horizontal & Vertical)
        item {
            MeayarCard {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(
                        text = "الفواصل (Dividers)",
                        style = typography.heading2xs,
                        color = colors.textPrimary
                    )

                    Text(text = "فاصل أفقي (Horizontal Divider):", style = typography.textSmMedium, color = colors.textSecondary)
                    MeayarDivider()

                    Text(text = "فاصل عمودي داخل شريط إجراءات (Vertical Divider):", style = typography.textSmMedium, color = colors.textSecondary)
                    Row(
                        modifier = Modifier.fillMaxWidth().height(36.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        MeayarButton(onClick = {}, size = MeayarButtonSize.SM, variant = MeayarButtonVariant.Ghost) {
                            Text("تعديل")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        MeayarVerticalDivider(modifier = Modifier.height(20.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        MeayarButton(onClick = {}, size = MeayarButtonSize.SM, variant = MeayarButtonVariant.Ghost) {
                            Text("مشاركة")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        MeayarVerticalDivider(modifier = Modifier.height(20.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        MeayarButton(onClick = {}, size = MeayarButtonSize.SM, variant = MeayarButtonVariant.Ghost) {
                            Text("طباعة")
                        }
                    }
                }
            }
        }

        // 14. Pagination Section
        item {
            MeayarCard {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(
                        text = "ترقيم الصفحات (Pagination)",
                        style = typography.heading2xs,
                        color = colors.textPrimary
                    )

                    Text(text = "النمط الافتراضي مع تسميات التنقل (Default with Labels):", style = typography.textSmMedium, color = colors.textSecondary)
                    MeayarPagination(
                        currentPage = currentPageNumber,
                        totalPages = 5,
                        onPageSelected = { currentPageNumber = it },
                        showLabels = true
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(text = "النمط المبسط بالأرقام فقط (Minimal Centered):", style = typography.textSmMedium, color = colors.textSecondary)
                    MeayarPagination(
                        currentPage = currentPageNumber,
                        totalPages = 5,
                        onPageSelected = { currentPageNumber = it },
                        showLabels = false
                    )
                }
            }
        }

        // 14. Document Card & Page Feedback
        item {
            MeayarCard {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(
                        text = "المستندات الرسمية وتقييم الصفحة (Document & Feedback)",
                        style = typography.heading2xs,
                        color = colors.textPrimary
                    )

                    MeayarDocumentCard(
                        title = "المرسوم التشريعي رقم 14 الخاص بالتحول الرقمي",
                        fileType = "PDF",
                        fileSize = "2.4 MB",
                        onDownloadClick = {}
                    )

                    MeayarPageFeedback()
                }
            }
        }

        // 15. Accordion Section
        item {
            MeayarCard {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(
                        text = "القوائم القابلة للطي (Accordion)",
                        style = typography.heading2xs,
                        color = colors.textPrimary
                    )

                    MeayarAccordion(
                        title = "ما هي الأوراق المطلوبة لتجديد جواز السفر؟",
                        initiallyExpanded = true
                    ) {
                        Text(
                            text = "يتطلب تقديم البطاقة الشخصية الأصلية، صورتين شخصيتين ملونتين بخلفية بيضاء، ودفع الرسوم المقررة عبر المنظومة الإلكترونية.",
                            style = typography.textSm,
                            color = colors.textSecondary
                        )
                    }
                }
            }
        }

        // 16. Indicators (Progress, Spinner & Skeleton)
        item {
            MeayarCard {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(
                        text = "مؤشرات التحميل والتقدم (Progress, Spinner & Skeleton)",
                        style = typography.heading2xs,
                        color = colors.textPrimary
                    )

                    Text(text = "شريط التقدم الخطي (Progress Bar):", style = typography.textSmMedium, color = colors.textSecondary)
                    MeayarProgressBar(progress = progressVal)

                    Text(text = "مؤشر التحميل الدائري الرسمي (Meayar Spinner):", style = typography.textSmMedium, color = colors.textSecondary)
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        MeayarSpinner(size = MeayarSpinnerSize.XS)
                        MeayarSpinner(size = MeayarSpinnerSize.SM)
                        MeayarSpinner(size = MeayarSpinnerSize.MD)
                        MeayarSpinner(size = MeayarSpinnerSize.LG)
                        MeayarSpinner(size = MeayarSpinnerSize.XL)
                    }

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        MeayarSpinner(size = MeayarSpinnerSize.SM, label = "جاري التحقق من الهوية الرقمية...")
                    }

                    // Inverse Spinner preview on brand background
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(colors.backgroundBrand)
                            .padding(12.dp)
                    ) {
                        MeayarSpinner(
                            size = MeayarSpinnerSize.SM,
                            label = "نمط التحميل المعكوس (Inverse Spinner on Brand Surface)",
                            isInverse = true
                        )
                    }

                    Text(text = "الهيكل العظمي النابض (Skeleton Loader):", style = typography.textSmMedium, color = colors.textSecondary)
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        MeayarSkeleton(modifier = Modifier.size(44.dp))
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.weight(1f)) {
                            MeayarSkeleton(modifier = Modifier.fillMaxWidth(0.7f).height(14.dp))
                            MeayarSkeleton(modifier = Modifier.fillMaxWidth(0.9f).height(10.dp))
                        }
                    }
                }
            }
        }

        // 17. Avatars & Badges
        item {
            MeayarCard {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(
                        text = "الصور الرمزية والشارات (Avatars & Badges)",
                        style = typography.heading2xs,
                        color = colors.textPrimary
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        MeayarAvatar(name = "عمر الخيام", size = MeayarAvatarSize.XL, status = MeayarPresenceStatus.Online)
                        MeayarAvatar(name = "سارة أحمد", size = MeayarAvatarSize.LG, status = MeayarPresenceStatus.Busy)
                        MeayarAvatar(name = "محمد دمشقي", size = MeayarAvatarSize.MD, status = MeayarPresenceStatus.Offline)
                        MeayarAvatar(name = "نور الهدى", size = MeayarAvatarSize.SM)
                        MeayarAvatar(name = "حلب", size = MeayarAvatarSize.XS)
                    }

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("شارات التنبيهات:", style = typography.textSmMedium, color = colors.textSecondary)
                        MeayarBadge(count = 3)
                        MeayarBadge(count = 42, containerColor = colors.backgroundBrand)
                        MeayarBadge(count = 150)
                    }
                }
            }
        }

        // 18. Empty State Section
        item {
            MeayarCard {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(
                        text = "الحالات الفارغة (Empty State)",
                        style = typography.heading2xs,
                        color = colors.textPrimary
                    )

                    MeayarEmptyState(
                        title = "لا توجد معاملات سابقة",
                        description = "لم تقم بتقديم أي طلبات عبر المنظومة بعد. يمكنك بدء معاملة جديدة بالضغط على الزر أدناه.",
                        type = MeayarEmptyStateType.NoData,
                        actionText = "تقديم طلب جديد",
                        onActionClick = {}
                    )

                    MeayarDivider()

                    MeayarEmptyState(
                        title = "لا توجد نتائج مطابقة",
                        description = "لم نتمكن من العثور على أي نتائج لكلمة البحث المدخلة. يرجى تجربة عبارات بحث بديلة.",
                        type = MeayarEmptyStateType.NoResults,
                        actionText = "مسح البحث",
                        onActionClick = {}
                    )

                    MeayarDivider()

                    MeayarEmptyState(
                        title = "فشل في تحميل البيانات",
                        description = "حدث خطأ أثناء الاتصال بالخادم الحكومي. يرجى التحقق من اتصال الإنترنت والمحاولة مجدداً.",
                        type = MeayarEmptyStateType.Error,
                        actionText = "إعادة المحاولة",
                        onActionClick = {}
                    )
                }
            }
        }

        // 19. Brand Alerts
        item {
            MeayarCard {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(
                        text = "تنبيهات معيار الرسمية",
                        style = typography.heading2xs,
                        color = colors.textPrimary
                    )

                    MeayarAlert(
                        title = "تنبيه رئيسي (Style=Primary)",
                        description = "تنبيه رسمي بارز بخلفية الأخضر السوري الأساسي #094239.",
                        style = MeayarAlertStyle.Primary,
                        onDismiss = {}
                    )

                    MeayarAlert(
                        title = "تنبيه ثانوي (Style=Secondary)",
                        description = "تنبيه هادئ بخلفية الأخضر المخفف #D8DFDE.",
                        style = MeayarAlertStyle.Secondary,
                        onDismiss = {}
                    )

                    MeayarAlert(
                        title = "تنبيه محايد (Style=Neutral)",
                        description = "تنبيه رمادي للملاحظات التنظيمية العامة.",
                        style = MeayarAlertStyle.Neutral,
                        onDismiss = {}
                    )

                    MeayarAlert(
                        title = "تنبيه بسيط (Style=Simple)",
                        description = "تنبيه بإطار خفيف وبدون خلفية ملونة للملاحظات الخفيفة.",
                        style = MeayarAlertStyle.Simple,
                        onDismiss = {}
                    )
                }
            }
        }

        // 20. Semantic Toasts (Success, Error, Warning, Info)
        item {
            MeayarCard {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(
                        text = "الإشعارات العائمة",
                        style = typography.heading2xs,
                        color = colors.textPrimary
                    )

                    MeayarToast(
                        title = "نجاح العملية",
                        description = "تم حفظ التغييرات وتحديث السجل الرقمي.",
                        type = MeayarToastType.Success,
                        onClose = {}
                    )

                    MeayarToast(
                        title = "تنبيه تنفيذي",
                        description = "يرجى تسديد الرسوم خلال المهلة القانونية المحددة.",
                        type = MeayarToastType.Warning,
                        onClose = {}
                    )
                }
            }
        }

        // 20b. Tooltips
        item {
            MeayarCard {
                Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                    Text(
                        text = "التلميحات العائمة (Tooltips)",
                        style = typography.heading2xs,
                        color = colors.textPrimary
                    )
                    Text(
                        text = "عناصر إرشادية سريعة توفر شروحات سياقية للحقول والرموز بتصميم معيار الداكن المتباين:",
                        style = typography.textSm,
                        color = colors.textSecondary
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "فقاعة التلميح القياسية:", style = typography.textSmMedium, color = colors.textSecondary)
                        MeayarTooltip(
                            text = "الرقم الوطني يتألف من 11 خانة رقمية",
                            icon = MeayarIcons.Info
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "عنصر تفاعلي (انقر للإظهار):", style = typography.textSmMedium, color = colors.textSecondary)
                        MeayarTooltipBox(
                            tooltipText = "رمز التحقق يرسل عبر الرسائل القصيرة SMS",
                            icon = MeayarIcons.HelpOutline
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(colors.backgroundSubtle)
                                    .padding(horizontal = 10.dp, vertical = 6.dp)
                            ) {
                                Icon(
                                    imageVector = MeayarIcons.HelpOutline,
                                    contentDescription = "مساعدة",
                                    tint = colors.iconBrand,
                                    modifier = Modifier.size(16.dp)
                                )
                                Text(
                                    text = "شرح رمز التحقق",
                                    style = typography.textXsMedium,
                                    color = colors.textBrand
                                )
                            }
                        }
                    }
                }
            }
        }

        // 21. Android Native Snackbars (MeayarSnackbar & MeayarSnackbarHost)
        item {
            MeayarCard {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(
                        text = "أشرطة التنبيه المحمولة (Android Snackbars)",
                        style = typography.heading2xs,
                        color = colors.textPrimary
                    )

                    Text(
                        text = "النمط المعكوس القياسي مع زر الإجراء (Inverse with Action):",
                        style = typography.textSmMedium,
                        color = colors.textSecondary
                    )
                    MeayarSnackbar(
                        message = "تم أرشفة المعاملة بنجاح",
                        actionLabel = "تراجع",
                        onActionClick = {},
                        style = MeayarSnackbarStyle.Inverse
                    )

                    Text(
                        text = "نمط الهوية السورية مع زر الإغلاق (Brand Style):",
                        style = typography.textSmMedium,
                        color = colors.textSecondary
                    )
                    MeayarSnackbar(
                        message = "تم تقديم الطلب إلى وزارة الصحة برقم مرجعي #94821",
                        actionLabel = "عرض التفاصيل",
                        onActionClick = {},
                        onDismissClick = {},
                        style = MeayarSnackbarStyle.Brand
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "تجربة إظهار شريط التنبيه الفعلي في التطبيق (Interactive Triggers):",
                        style = typography.textSmMedium,
                        color = colors.textSecondary
                    )

                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        // Green / Brand Snackbar trigger
                        MeayarButton(
                            onClick = {
                                coroutineScope.launch {
                                    snackbarHostState?.showMeayarSnackbar(
                                        message = "تم حفظ التعديلات في السجل الرقمي السوري بنجاح",
                                        actionLabel = "عرض",
                                        style = MeayarSnackbarStyle.Brand,
                                        icon = MeayarIcons.CheckCircle
                                    )
                                }
                            },
                            size = MeayarButtonSize.SM,
                            variant = MeayarButtonVariant.Primary
                        ) {
                            Text("شريط أخضر رسمي (Brand)")
                        }

                        // Dark / Inverse Snackbar trigger
                        MeayarButton(
                            onClick = {
                                coroutineScope.launch {
                                    snackbarHostState?.showMeayarSnackbar(
                                        message = "إشعار نظام: تم تحديث البيانات بنجاح",
                                        actionLabel = "حسناً",
                                        style = MeayarSnackbarStyle.Inverse,
                                        icon = MeayarIcons.Info
                                    )
                                }
                            },
                            size = MeayarButtonSize.SM,
                            variant = MeayarButtonVariant.Secondary
                        ) {
                            Text("شريط داكن (Inverse)")
                        }

                        // Action with Undo trigger
                        MeayarButton(
                            onClick = {
                                coroutineScope.launch {
                                    val result = snackbarHostState?.showMeayarSnackbar(
                                        message = "تم نقل المعاملة إلى سلة المحذوفات",
                                        actionLabel = "تراجع",
                                        style = MeayarSnackbarStyle.Brand
                                    )
                                    if (result == SnackbarResult.ActionPerformed) {
                                        snackbarHostState?.showMeayarSnackbar(
                                            message = "تم التراجع عن الحذف بنجاح",
                                            style = MeayarSnackbarStyle.Inverse
                                        )
                                    }
                                }
                            },
                            size = MeayarButtonSize.SM,
                            variant = MeayarButtonVariant.Outlined
                        ) {
                            Text("شريط مع تراجع (Undo)")
                        }
                    }
                }
            }
        }

        // 22. Modal Dialog Prompt
        item {
            MeayarCard {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(
                        text = "النوافذ المنبثقة (Modals & Dialogs)",
                        style = typography.heading2xs,
                        color = colors.textPrimary
                    )

                    MeayarButton(
                        onClick = { showDialog = true },
                        size = MeayarButtonSize.MD,
                        variant = MeayarButtonVariant.Primary
                    ) {
                        Text("فتح نافذة التأكيد (Show Dialog)")
                    }
                }
            }
        }

        // 22b. Cookie Consent & Privacy Notice
        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "شريط ملفات تعريف الارتباط والخصوصية (Cookie Consent Banner)",
                    style = typography.heading2xs,
                    color = colors.textPrimary,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )

                if (showCookieConsentDemo) {
                    MeayarCookieConsent(
                        onAcceptAll = { showCookieConsentDemo = false },
                        onCustomize = {},
                        onDecline = { showCookieConsentDemo = false }
                    )
                } else {
                    MeayarCard {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "تم قبول إعدادات الخصوصية بنجاح", style = typography.textSmMedium, color = colors.textSuccess)
                            MeayarButton(
                                onClick = { showCookieConsentDemo = true },
                                size = MeayarButtonSize.SM,
                                variant = MeayarButtonVariant.Outlined
                            ) {
                                Text("إعادة عرض الشريط")
                            }
                        }
                    }
                }
            }
        }

        // 23. File Upload Dropzone
        item {
            MeayarCard {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(
                        text = "رفع المرفقات والمستندات (File Upload)",
                        style = typography.heading2xs,
                        color = colors.textPrimary
                    )

                    MeayarFileUpload(
                        label = "المستندات الثبوتية المطلوبة",
                        isMandatory = true,
                        hintText = "ملفات PDF أو صور JPG/PNG بحد أقصى 10 ميغابايت",
                        files = uploadedFiles,
                        onSelectFiles = {
                            uploadedFiles = uploadedFiles + MeayarFileItem(
                                id = System.currentTimeMillis().toString(),
                                name = "تقرير_طبي_معتمد_${uploadedFiles.size + 1}.pdf",
                                sizeString = "1.8 MB",
                                progress = 1f
                            )
                        },
                        onRemoveFile = { fileToRemove ->
                            uploadedFiles = uploadedFiles.filter { it.id != fileToRemove.id }
                        }
                    )

                    MeayarDivider()

                    Text(
                        text = "صفوف الملفات المرفقة بحالاتها المختلفة (Attached File Row States):",
                        style = typography.textSmMedium,
                        color = colors.textSecondary
                    )

                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        // Success state
                        MeayarAttachedFileRow(
                            file = MeayarFileItem(
                                id = "demo-success",
                                name = "البطاقة_الشخصية_الوجه_الأمامي.pdf",
                                sizeString = "2.4 MB",
                                progress = 1f
                            ),
                            onRemove = {}
                        )

                        // Uploading in-progress state
                        MeayarAttachedFileRow(
                            file = MeayarFileItem(
                                id = "demo-progress",
                                name = "صورة_سند_الإقامة.jpg",
                                sizeString = "4.1 MB",
                                progress = 0.65f
                            ),
                            onRemove = {}
                        )

                        // Error state
                        MeayarAttachedFileRow(
                            file = MeayarFileItem(
                                id = "demo-error",
                                name = "مستند_غير_صالح.exe",
                                sizeString = "14.2 MB",
                                isError = true,
                                errorMessage = "نوع الملف غير مدعوم أو تجاوز الحد المسموح (10 MB)"
                            ),
                            onRemove = {}
                        )
                    }
                }
            }
        }

        // 24. Data Table
        item {
            MeayarCard {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(
                        text = "جداول البيانات الحكومية (Data Tables)",
                        style = typography.heading2xs,
                        color = colors.textPrimary
                    )

                    val tableItems = listOf(
                        Triple("SY-94821", "حجز موعد عيادة", Pair("مكتمل", MeayarTagColor.Green)),
                        Triple("SY-94819", "شهادة صحية دولية", Pair("قيد التدقيق", MeayarTagColor.Yellow)),
                        Triple("SY-93402", "تجديد بطاقة صحية", Pair("بانتظار الرسوم", MeayarTagColor.Gold)),
                        Triple("SY-91024", "شكوى إدارية", Pair("مرفوض", MeayarTagColor.Red))
                    )

                    val columns = listOf(
                        MeayarColumn<Triple<String, String, Pair<String, MeayarTagColor>>>(
                            title = "رقم المعاملة",
                            width = 110.dp
                        ) { item ->
                            Text(text = item.first, style = typography.textXsMedium, color = colors.textBrand)
                        },
                        MeayarColumn<Triple<String, String, Pair<String, MeayarTagColor>>>(
                            title = "نوع الخدمة",
                            width = 140.dp
                        ) { item ->
                            Text(text = item.second, style = typography.textXsMedium, color = colors.textPrimary)
                        },
                        MeayarColumn<Triple<String, String, Pair<String, MeayarTagColor>>>(
                            title = "حالة الطلب",
                            width = 110.dp
                        ) { item ->
                            MeayarTag(
                                text = item.third.first,
                                color = item.third.second,
                                hierarchy = MeayarTagHierarchy.Secondary,
                                size = MeayarTagSize.Small
                            )
                        }
                    )

                    MeayarTable(
                        columns = columns,
                        items = tableItems
                    )
                }
            }
        }

        // 25. Inline Calendar Grid
        item {
            MeayarCard {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(
                        text = "التقويم الشبكي المباشر (Inline Calendar)",
                        style = typography.heading2xs,
                        color = colors.textPrimary
                    )

                    val calendarType = if (selectedCalendarTypeIndex == 0) MeayarCalendarType.Gregorian else MeayarCalendarType.Hijri

                    MeayarTabs(
                        tabs = listOf("ميلادي (Gregorian)", "هجري (Hijri)"),
                        selectedIndex = selectedCalendarTypeIndex,
                        onTabSelected = { idx ->
                            selectedCalendarTypeIndex = idx
                            if (idx == 1 && calYear > 1500) {
                                calYear = 1448
                                calMonth = 5
                                calDay = 8
                            } else if (idx == 0 && calYear < 1500) {
                                calYear = 2026
                                calMonth = 10
                                calDay = 15
                            }
                        },
                        style = MeayarTabStyle.Pill
                    )

                    Text(
                        text = if (selectedCalendarTypeIndex == 0) {
                            "تاريخ الموعد المحدد (ميلادي): $calDay/${calMonth + 1}/$calYear"
                        } else {
                            "تاريخ الموعد المحدد (هجري): $calDay/${calMonth + 1}/$calYear هـ"
                        },
                        style = typography.textSmMedium,
                        color = colors.textBrand
                    )

                    MeayarCalendar(
                        selectedYear = calYear,
                        selectedMonth = calMonth,
                        selectedDay = calDay,
                        calendarType = calendarType,
                        onDateSelected = { year, month, day ->
                            calYear = year
                            calMonth = month
                            calDay = day
                        }
                    )
                }
            }
        }

        // 26. Accessibility Toolbar
        item {
            MeayarCard {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(
                        text = "شريط سهولة الاستخدام (Accessibility Toolbar)",
                        style = typography.heading2xs,
                        color = colors.textPrimary
                    )

                    MeayarAccessibilityToolbar(
                        onFontScaleDecrease = {},
                        onFontScaleReset = {},
                        onFontScaleIncrease = {},
                        isHighContrast = isHighContrastDemo,
                        onToggleHighContrast = { isHighContrastDemo = it }
                    )
                }
            }
        }

        // 27. Navigation Drawer & Side Nav
        item {
            MeayarCard {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(
                        text = "القائمة الجانبية والشريط الملاحي (Drawer & Side Nav)",
                        style = typography.heading2xs,
                        color = colors.textPrimary
                    )

                    Text(
                        text = "القائمة التفاعلية المنزلقة (Interactive Modal Navigation Drawer):",
                        style = typography.textSmMedium,
                        color = colors.textSecondary
                    )
                    MeayarButton(
                        onClick = { onOpenDrawer?.invoke() },
                        variant = MeayarButtonVariant.Primary
                    ) {
                        Icon(imageVector = MeayarIcons.Menu, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("فتح القائمة الجانبية (Open Navigation Drawer)")
                    }

                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "المعاينة المباشرة لمحتوى القائمة (In-Card Content Preview):",
                        style = typography.textSmMedium,
                        color = colors.textSecondary
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .border(1.dp, colors.borderSubtle, RoundedCornerShape(12.dp))
                    ) {
                        MeayarDrawerContent(
                            items = drawerNavItems,
                            onItemSelected = { selectedDrawerItem = it.id },
                            userName = "حمزة الخطيب",
                            userNationalId = "01030094821",
                            onLogoutClick = {}
                        )
                    }
                }
            }
        }
    }

    // Modal Dialog instance
    if (showDialog) {
        MeayarDialog(
            onDismissRequest = { showDialog = false },
            title = "تأكيد إرسال المعاملة",
            description = "هل أنت متأكد من رغبتك في إرسال طلب تجديد البطاقة الشخصية؟ لن تتمكن من تعديل البيانات بعد الإرسال.",
            confirmText = "نعم، إرسال الطلب",
            cancelText = "مراجعة البيانات",
            icon = MeayarIcons.Document,
            onConfirm = { showDialog = false }
        )
    }
}
