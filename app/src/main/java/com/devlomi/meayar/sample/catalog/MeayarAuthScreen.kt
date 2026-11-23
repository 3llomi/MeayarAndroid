package com.devlomi.meayar.sample.catalog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.devlomi.meayar.components.MeayarAccessibilityToolbar
import com.devlomi.meayar.components.MeayarAlert
import com.devlomi.meayar.components.MeayarAlertStyle
import com.devlomi.meayar.components.MeayarButton
import com.devlomi.meayar.components.MeayarButtonSize
import com.devlomi.meayar.components.MeayarCard
import com.devlomi.meayar.components.MeayarCheckbox
import com.devlomi.meayar.components.MeayarDivider
import com.devlomi.meayar.components.MeayarOfficialBanner
import com.devlomi.meayar.components.MeayarOtpInput
import com.devlomi.meayar.components.MeayarTabs
import com.devlomi.meayar.components.MeayarTabStyle
import com.devlomi.meayar.components.MeayarTextField
import com.devlomi.meayar.components.MeayarToast
import com.devlomi.meayar.components.MeayarToastType
import com.devlomi.meayar.icons.MeayarIcons
import com.devlomi.meayar.theme.MeayarTheme

/**
 * Official Syrian National Digital Identity Authentication Screen.
 *
 * Provides a production-ready Citizen Login flow with:
 * - National ID (الرقم الوطني) & Phone verification
 * - OTP Code verification step
 * - High-accessibility mode toggle
 * - Syrian official government security disclaimers
 */
@Composable
fun MeayarAuthScreen(
    modifier: Modifier = Modifier,
    onLoginSuccess: () -> Unit = {}
) {
    val colors = MeayarTheme.colors
    val typography = MeayarTheme.typography
    val scrollState = rememberScrollState()

    var selectedTab by rememberSaveable { mutableIntStateOf(0) }
    val authTabs = listOf("تسجيل الدخول", "التحقق بالرمز (OTP)", "حساب جديد")

    var nationalId by rememberSaveable { mutableStateOf("01020034567") }
    var password by rememberSaveable { mutableStateOf("") }
    var phoneNumber by rememberSaveable { mutableStateOf("0933123456") }
    var otpCode by rememberSaveable { mutableStateOf("") }
    var rememberMe by rememberSaveable { mutableStateOf(true) }
    var isLoading by rememberSaveable { mutableStateOf(false) }
    var authSuccess by rememberSaveable { mutableStateOf(false) }
    var isHighContrast by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.backgroundPage)
    ) {
        // Official Syrian Top Banner
        MeayarOfficialBanner()

        // Accessibility Toolbar
        MeayarAccessibilityToolbar(
            onFontScaleDecrease = {},
            onFontScaleReset = {},
            onFontScaleIncrease = {},
            isHighContrast = isHighContrast,
            onToggleHighContrast = { isHighContrast = it }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 16.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Syrian Emblem Badge & Portal Title
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(colors.backgroundBrand),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = MeayarIcons.Building,
                    contentDescription = "Syrian Emblem",
                    tint = colors.textInverse,
                    modifier = Modifier.size(36.dp)
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = "بوابة الهوية الرقمية السورية الموحدة",
                style = typography.headingSm,
                color = colors.textPrimary,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "منظومة النفاذ الإلكتروني الموحد لكافة المعاملات والخدمات الحكومية",
                style = typography.textSm,
                color = colors.textSecondary,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Main Auth Card
            MeayarCard(modifier = Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    // Auth Mode Tabs
                    MeayarTabs(
                        tabs = authTabs,
                        selectedIndex = selectedTab,
                        onTabSelected = { selectedTab = it },
                        style = MeayarTabStyle.Pill
                    )

                    if (authSuccess) {
                        MeayarToast(
                            title = "تم التحقق بنجاح",
                            description = "أهلاً بك، تم تسجيل الدخول بنجاح إلى منظومة الهوية الرقمية.",
                            type = MeayarToastType.Success
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        MeayarButton(
                            onClick = onLoginSuccess,
                            modifier = Modifier.fillMaxWidth(),
                            size = MeayarButtonSize.LG
                        ) {
                            Text("الانتقال إلى لوحة الخدمات")
                        }
                    } else when (selectedTab) {
                        0 -> { // National ID + Password Login
                            MeayarTextField(
                                value = nationalId,
                                onValueChange = { nationalId = it },
                                label = "الرقم الوطني الموحد (11 رقماً)",
                                isMandatory = true,
                                forceLtrContent = true,
                                placeholder = "01xxxxxxxx",
                                helperText = "كما هو مدون في البطاقة الشخصية الصادرة عن السجل المدني",
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
                                value = password,
                                onValueChange = { password = it },
                                label = "كلمة المرور الخاصة بالهوية",
                                isMandatory = true,
                                placeholder = "••••••••••••",
                                forceLtrContent = true,
                                leadingIcon = {
                                    Icon(
                                        imageVector = MeayarIcons.Lock,
                                        contentDescription = null,
                                        tint = colors.iconSecondary,
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                            )

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                MeayarCheckbox(
                                    checked = rememberMe,
                                    onCheckedChange = { rememberMe = it },
                                    label = "تذكر هذا الجهاز"
                                )

                                Text(
                                    text = "نسيت كلمة المرور؟",
                                    style = typography.textXsMedium,
                                    color = colors.textBrand
                                )
                            }

                            MeayarButton(
                                onClick = {
                                    isLoading = true
                                    authSuccess = true
                                    isLoading = false
                                },
                                modifier = Modifier.fillMaxWidth(),
                                size = MeayarButtonSize.LG,
                                loading = isLoading
                            ) {
                                Text("تسجيل الدخول إلى البوابة")
                            }
                        }
                        1 -> { // OTP Phone Verification
                            Text(
                                text = "أدخل رمز التحقق المرسل عبر رسالة SMS إلى هاتفك المسجل لدى المنظومة:",
                                style = typography.textSm,
                                color = colors.textSecondary
                            )

                            MeayarTextField(
                                value = phoneNumber,
                                onValueChange = { phoneNumber = it },
                                label = "رقم الهاتف المحمول",
                                isMandatory = true,
                                forceLtrContent = true,
                                leadingIcon = {
                                    Icon(
                                        imageVector = MeayarIcons.Phone,
                                        contentDescription = null,
                                        tint = colors.iconSecondary,
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                            )

                            Text(
                                text = "رمز التحقق السريع (OTP):",
                                style = typography.textSmMedium,
                                color = colors.textPrimary
                            )

                            MeayarOtpInput(
                                otpValue = otpCode,
                                onOtpChange = { otpCode = it },
                                length = 4
                            )

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "لم يصلك الرمز؟ ",
                                    style = typography.textXs,
                                    color = colors.textTertiary
                                )
                                Text(
                                    text = "إعادة إرسال رمز جديد",
                                    style = typography.textXsMedium,
                                    color = colors.textBrand
                                )
                            }

                            MeayarButton(
                                onClick = {
                                    if (otpCode.length == 4) {
                                        authSuccess = true
                                    }
                                },
                                modifier = Modifier.fillMaxWidth(),
                                size = MeayarButtonSize.LG,
                                enabled = otpCode.length == 4
                            ) {
                                Text("تأكيد الدخول عبر OTP")
                            }
                        }
                        2 -> { // New Account
                            MeayarAlert(
                                title = "إصدار هوية رقمية جديدة",
                                description = "يتطلب تفعيل الهوية الرقمية لأول مرة الحضور شخصياً لأقرب مركز خدمة مواطن مع البطاقة الشخصية السارية.",
                                style = MeayarAlertStyle.Primary
                            )

                            MeayarTextField(
                                value = nationalId,
                                onValueChange = { nationalId = it },
                                label = "الرقم الوطني",
                                isMandatory = true,
                                forceLtrContent = true,
                                placeholder = "01010101010"
                            )

                            MeayarTextField(
                                value = phoneNumber,
                                onValueChange = { phoneNumber = it },
                                label = "رقم الهاتف المحمول المعتمد",
                                isMandatory = true,
                                forceLtrContent = true,
                                placeholder = "09xxxxxxxx"
                            )

                            MeayarButton(
                                onClick = {},
                                modifier = Modifier.fillMaxWidth(),
                                size = MeayarButtonSize.LG
                            ) {
                                Text("حجز موعد تفعيل في مركز الخدمة")
                            }
                        }
                    }

                    MeayarDivider()

                    // Official security note
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = MeayarIcons.CheckCircle,
                            contentDescription = null,
                            tint = colors.iconBrand,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = "نظام تشفير حكومي معتمد وفق معايير الهيئة الوطنية للشبكات",
                            style = typography.text2xs,
                            color = colors.textTertiary
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Footer Links
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "سياسة الخصوصية",
                    style = typography.textXsMedium,
                    color = colors.textTertiary
                )
                Text(text = "•", style = typography.textXs, color = colors.textDisabled)
                Text(
                    text = "شروط الاستخدام",
                    style = typography.textXsMedium,
                    color = colors.textTertiary
                )
                Text(text = "•", style = typography.textXs, color = colors.textDisabled)
                Text(
                    text = "مراكز الدعم الفني",
                    style = typography.textXsMedium,
                    color = colors.textTertiary
                )
            }
        }
    }
}
