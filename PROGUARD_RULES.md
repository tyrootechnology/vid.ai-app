
```groovy

-keep class * {
    public private *;
}
-dontwarn okio.**
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

-dontwarn com.bea.xml.stream.**
-dontwarn javax.xml.**

-dontwarn javax.naming.**
-dontwarn javax.servlet.**
-dontwarn org.slf4j.**

-keepclassmembers class android.support.design.internal.BottomNavigationMenuView {
    boolean mShiftingMode;
}
```
