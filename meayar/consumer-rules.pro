# Proguard rules for MEAYAR Compose design system library

# Keep Kotlin reflection & Compose metadata
-keepattributes *Annotation*, Signature, InnerClasses, EnclosingMethod

# Keep public classes and composable functions in the MEAYAR library
-keep class com.devlomi.meayar.** { *; }

# Preserve Compose State and Immutable models across consumer apps
-keep @androidx.compose.runtime.Immutable class * { *; }
-keep @androidx.compose.runtime.Stable class * { *; }
