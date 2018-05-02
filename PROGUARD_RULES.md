
```groovy

#vid.ai specific
-keep class com.tyroo.tva.** { *; }

-dontwarn javax.annotation.**
-dontwarn com.squareup.okhttp.**
-dontwarn com.squareup.okhttp3.**
-keep class com.squareup.okhttp.** { *;}
-keep class com.squareup.okhttp3.** {
    *;
}
-keep class okhttp3.* {
  *; }
-keep interface okhttp3.* {
  *; }

-dontwarn okhttp3

-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn okio.
-dontwarn com.fasterxml.**
-dontwarn okio.**
-dontwarn retrofit2.**

# Simple-Xml Proguard Config
-dontwarn com.bea.xml.**
-dontwarn javax.xml.**
-dontwarn org.simpleframework.xml.stream.**
-keep class org.simpleframework.xml.**{ *; }
-keepclasseswithmembers class org.simpleframeork.** { *; }
-keepclassmembers class * {
    @org.simpleframework.xml.* *;
}

-dontwarn javax.naming.**
-dontwarn javax.servlet.**
-dontwarn org.slf4j.**

-keepclassmembers class android.support.design.internal.BottomNavigationMenuView {
    boolean mShiftingMode;
}

#---Otto---
-keepattributes *Annotation*
-keepclassmembers class ** {
    @com.squareup.otto.Subscribe public *;
    @com.squareup.otto.Produce public *;
}

-keepattributes Signature
# For using GSON @Expose annotation
-keepattributes *Annotation*

# Gson specific classes
-dontwarn sun.misc.**
-dontwarn com.google.gson.**
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }
-keep class com.google.gson.** { *; }
-keepclassmembers class com.google.gson** {*;}
-keep class com.tyroo.vidai.entities.** { *; }
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

```
