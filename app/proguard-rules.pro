# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Keep Compose rules
-keep class androidx.compose.** { *; }
-keep class kotlin.** { *; }
-dontwarn androidx.compose.**

# Keep ViewModel
-keep class * extends androidx.lifecycle.ViewModel {
    <init>();
}
-keepclassmembers class * extends androidx.lifecycle.ViewModel {
    <init>(...);
}

# Keep all Piano app classes (don't obfuscate)
-keep class com.example.piano.** { *; }
-keepclassmembers class com.example.piano.** { *; }

# Keep audio engine and MediaPlayer
-keep class android.media.** { *; }
-keep class android.media.AudioTrack { *; }
-keep class android.media.MediaPlayer { *; }
-dontwarn android.media.**

# Keep Kotlin metadata
-keep class kotlin.Metadata { *; }
-keepattributes *Annotation*

# Keep Coroutines
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}
-keepclassmembers class kotlinx.** {
    volatile <fields>;
}

# Uncomment this to preserve the line number information for
# debugging stack traces.
-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
-renamesourcefileattribute SourceFile