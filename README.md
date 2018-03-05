VID.AI Android SDK
=======================
This guide and sample code is intended for publishers who want to monetize their Android app with VID.AI SDK.

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

For more information please see [the website](http://www.tyroo.com/)

## Changelog
To see what has changed in recent versions of VID.AI SDK for Android, see the [CHANGELOG](CHANGELOG.md).

## How Does Video Monetisation Work
Video Content consumption is growing on mobile and advertisers are spending huge dollar on video advertising. 50% of these videos are consumed on mobile. Focus on short format video has also increased as the average human span attention is less than 8 sec. Also there is in an interesting insight about video consumption, 98% of time Phones are used in portrait mode while consuming videos. Our video content cloud SDK gives access to Short format vertical and horizontal videos.Our Android native video player embedded in SDK supports VAST 4.0, VAST 3.0 and VAST 2.0 Inline Linear Ads.

## Get Started
Integrating the VID.AI Video Ads SDK into an app is the first step toward displaying ads and earning revenue. Once that's done, you can choose an ad format (such as Video in feed or Interstisial video) and get a detailed set of steps for implementing it.

### Requirements 
- Use Android Studio 2.0 or higher
- Target Android API level 14 or higher (added in sdk version 1.0.7)

### Register App
- Please contact support@tyroo.com for register the application.

### Adding the SDK to your Project

**Method 1:** Grab via jCenter

If you are using Gradle to build your Android applications, you can pull the latest version of the SDK from jCenter.


Step 1: Include this in your top-level ```build.gradle``` file:

```groovy
allprojects {
    repositories {
        jcenter()
    }
}
```
Step 2: Add the following line to the dependencies element in your application module’s build.gradle.

```groovy
compile 'com.tyroo:vidai:1.0.7'
```
Step 3: Sync your Gradle project to ensure that the dependency is downloaded by the build system.


**Method 2:** Manual Download

* Download the Vid.ai SDK AAR file from [here](http://rep.tyroo.com/SDK/vidai-release-1.0.5.aar)


To manually include external or downloaded AAR file into your project create a directory named 'libs' into your app module.

Lets say you have kept aar file in libs folder ( assume file name is vidai-release-1.0.5.aar ) then in app build.gradle specify following and click sync project with Gradle files. Open Project level build.gradle and add flatDir{dirs 'libs'} like did below


```groovy
allprojects {
    repositories {
        jcenter()
        flatDir {
            dirs 'libs'
        }
    }
}
```
and now open app level build.grdle file and add .aar file

```groovy
dependencies {
       compile(name:'vidai-release-1.0.7', ext:'aar')
}
```

#### Additional Dependencies Required

To monetize with Tyroo Vid.AI SDK using AAR file, you must add the following dependencies in your application module’s build.gradle:

* Picasso
```groovy
 compile 'com.squareup.picasso:picasso:2.5.2'
```
* Gson
```groovy
 compile 'com.google.code.gson:gson:2.8.2'
```
* Okhttp
```groovy
 compile 'com.squareup.okhttp3:okhttp:3.9.0'
```
* RecyclerView
```groovy
 compile 'com.android.support:recyclerview-v7:25.3.1'
```
* Otto
```groovy
 compile 'com.squareup:otto:1.3.8'
```

If the developer is already using the above libraries, he should not include it once again and this will lead to decrease in app size accordingly. For example, if an app developer is already using library- "com.android.support:recyclerview-v7:25.3.1" then he should not include it once again.


If everything goes well you will see library entry is made in build -> exploded-aar


**Method 3:** via Maven

```groovy
<dependency>
  <groupId>com.tyroo</groupId>
  <artifactId>vidai</artifactId>
  <version>1.0.7</version>
  <type>pom</type>
</dependency>
```

## Available Ad Formats 

![InVideoFeed](https://github.com/tyrootechnology/vid.ai-app/blob/master/screenshots/screen.jpg)

### In Feed/In Article Video
In-Feed or In-Article Video ads-namely, the ads that show up in the middle of the stream as you scroll through your favorite news feed, social feed, at some position of recyclerview, application start screen or some other portion of the app.

In feed/In Article videos can be customised to suit the applications look and feel. Multiple customisation options are also available in it's integratin guidelines.

[See Integration Guidelines](https://github.com/tyrootechnology/vid.ai-app/wiki/In-Feed-Video)

### Offerwall Videos
The Offerwall format lets you show multiple video ads in a Grid form.

[See Integration Guidelines](https://github.com/tyrootechnology/vid.ai-app/wiki/Offerwall-Videos)

### In Feed/In Article carousel Video
The carousel format lets you show multiple videos ads, headlines and links, or calls to action in a single ad unit. Anyone who sees ad can then scroll through the carousel cards by swiping left-right on mobile phones.

[See Integration Guidelines](https://github.com/tyrootechnology/vid.ai-app/wiki/In-Feed-carousel-Video)

### Interstitial Video
Interstitial video ads are full-screen ad formats covering the interface of an app and appearing at natural app action points.

[See Integration Guidelines](https://github.com/tyrootechnology/vid.ai-app/wiki/Interstitial-Video)


## Your feedback and questions
At Tyroo we're massive believers in feedback! Here are some common ways to share your thoughts with us:
  - Posting issue/question/enhancement on our [issues page](https://github.com/tyrootechnology/vid.ai-app/issues).
  - Email us: support@tyroo.com
  

## ProGuard Rules
If you are using ProGuard you might need to add these [PROGUARD RULES](PROGUARD_RULES.md):
