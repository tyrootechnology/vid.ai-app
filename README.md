VID.AI Demo application
=======================
AI DRIVEN  VIDEO CONTENT ENGAGEMENT PLATFORM

![](https://github.com/tyrootechnology/vid.ai-app/blob/master/screenshot.gif)

## Release Notes

### VERSION 1.0.4
* Removed AdView parameter from onDisplayAds().
* Added new method named setAdViewLayout(AdView) in TyrooVidAISdk which takes an AdView layout as parameter.
* Removed flick from video player and replaced BallPulseIndicator progress bar with android default circular progress bar.
* Added Alert Dialog for No Internet Connection in Discover Wall and Carousal View.

### VERSION 1.0.3
* Improved Video Player enter or exit animation.
* Reduce GPU overdraw.
* Handled Network connectivity.
* Close button added in Interstitial Screen

### VERSION 1.0.2
* Added support for VAST 2.0 and VAST 3.0
* Added support for android larger aspect ratio screen (display format with an aspect ratio of 18.5:9)
* Video player animation bug fixed.

### VERSION 1.0.1
* Null Pointer bug fixed.

### VERSION 1.0.0
* Added Discover, carousel and vertical full screen video ad formats.
* Added Support for VAST 4.0
* Gesture Supported Video Player



## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

For more information please see [the website](http://www.tyroo.com/)

### Adding the SDK to your Project

**Method 1:** Grab via jCenter

If you are using Gradle to build your Android applications, you can pull the latest version of the SDK from jCenter.


**Step 1:** Include this in your top-level ```build.gradle``` file:

```groovy
allprojects {
    repositories {
        jcenter()
    }
}
```
**Step 2:** Add the following line to the dependencies element in your application module’s build.gradle.

```groovy
compile 'com.tyroo:vidai:1.0.4'
```
**Step 3:** Sync your Gradle project to ensure that the dependency is downloaded by the build system.


**Method 2:** Manual Download

* Download the Vid.ai SDK AAR file from [here](http://rep.tyroo.com/SDK/vidai-release-1.0.4.aar)


To manually include external or downloaded AAR file into your project create a directory named 'libs' into your app module.

Lets say you have kept aar file in libs folder ( assume file name is vidai-release-1.0.3.aar ) then in app build.gradle specify following and click sync project with Gradle files. Open Project level build.gradle and add flatDir{dirs 'libs'} like did below


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
       compile(name:'vidai-release-1.0.4', ext:'aar')
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

If the developer is already using the above libraries, he should not include it once again and this will lead to decrease in app size accordingly. For example, if an app developer is already using library- "com.android.support:recyclerview-v7:25.3.1" then he should not include it once again.


If everything goes well you will see library entry is made in build -> exploded-aar


**Method 3:** via Maven

```groovy
<dependency>
  <groupId>com.tyroo</groupId>
  <artifactId>vidai</artifactId>
  <version>1.0.4</version>
  <type>pom</type>
</dependency>
```


## Prerequisite for rendering video ads

In order to display video ads at a certain placement, following methods need to be called. In case you have two placements inside an application where you want to display ads, then the entire set of methods need to be called again with the new placement id.



### 1. Display Video Ads by Passing XML Layout 
To show the ads inside your own layout, You need to add ```com.tyroo.tva.sdk.AdView``` element to your xml layout and later you have to pass the instance of this AdView layout class in ```setAdViewLayout(adViewLayout)``` method.


#### Step 1: Add AdView to the layout


```
<com.tyroo.tva.sdk.AdView
    android:id="@+id/adView"
    android:layout_height="match_parent"
    android:layout_width="match_parent"/>
```


#### Step 2: Create an instance of AdView


Open your Java Activity file and create an instance of ```AdView```


```Java
import com.tyroo.tva.sdk.AdView;

.....

public class MainActivity extends AppCompatActivity implements TyrooVidAiSdk.TyrooSdkListener {

private AdView adView;

.....

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    adView = (AdView) findViewById(R.id.adView);
    initTyrooVidAiSdk();
}
```


#### Step 3: Initialize Vid.AI SDK

After creating ```AdView``` instance, you need to initialize Vid.AI SDK before display ads. You need to create an instance of ```TyrooVidAiSdk``` class and implement ```TyrooVidAiSdk.TyrooSdkListener ``` in Activity or Fragment. A developer can decide whether he wants to use an ```Activity``` or ```Fragment```.


Here is the example how you can initialize the Vid.ai SDK.

```java
import com.tyroo.tva.sdk.AdView;
import com.tyroo.tva.sdk.TyrooVidAiSdk;


public class MainActivity extends AppCompatActivity implements TyrooVidAiSdk.TyrooSdkListener {

     private AdView adView;
     private TyrooVidAiSdk tyrooVidAiSdk;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adView = (AdView) findViewById(R.id.adView);
        initTyrooVidAiSdk();
    }

    private void initTyrooVidAiSdk() {
        try {
            tyrooVidAiSdk = new TyrooVidAiSdk(getApplicationContext(), this);
            tyrooVidAiSdk.setPlacementId(placementId); //Your Placement ID as a String
	    tyrooVidAiSdk.setAdViewLayout(adView); //AdView Layout parameter as object
            tyrooVidAiSdk.setDynamicPlacement(true);// Always set as true
            tyrooVidAiSdk.setPackageName(packageName); // Your application package name
            tyrooVidAiSdk.enableCaching(true); //set it true for preload videos file
            tyrooVidAiSdk.validate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 ```  


```Note: It’s important to use an Application Context instead of Activity context when calling TyrooVidAiSdk Constructor.```

#### Step 4: Implement SDK Listener

To be notified of SDK ads lifecycle events, you must implement ```TyrooVidAiSdk.TyrooSdkListener ```. ```TyrooSdkListener ``` is an interface in the TyrooVidAiSdk class that contains five callback methods. These methods will be called by the SDK when a state has changed: after successful SDK integration, after successful or unsuccessful ad render, after successful or unsuccessful ad display and on failure because of any other reason.


You can use these callbacks to know about the success or failure status and  take appropriate actions at your end.For example, in case of failure ( because of no ad response ), you may want to show appropriate messages to the end user.


TyrooSdkListener interface has following callback methods:

```onSuccess(String status)```
> Based on the validation of placement id, dynamic placement, package name etc. this method will return true if SDK is successfully initialized. In case of failure it will return false.

```onRenderingAds(Boolean status)```
> This method is called when request for ads is under process.

```onRenderedAds(Boolean status)```
> This method is called when request for ads is successfully completed and ads are available to be shown.

```onDisplayAds(Boolean status)```
> This method is called when ads are successfully displayed on user screen.

```onFailure(String errorMsg)```
> This method is called when a request to fetch ads fails. The status message indicating the reason for failure is available as parameters.


#### Step 5: Display Ads

To display ads app developer needs to call ```displayAds()``` method of the ```TyrooVidAiSdk``` instance. Make sure deveoper has passed adView layout object as a parameter in ```setAdViewLayout(adView)``` method of the ```TyrooVidAISdk``` class. The method ```displayAds()``` should be called after the ```onRenderedAds``` event has been generated by the SDK with true status.


For example :

```java
@Override
public void onRenderedAds(Boolean status) {
    if (status){
       	tyrooVidAiSdk.displayAds();
    }
}
```

``` Note: It is highly recommended to call tyrooVidAiSdk.displayAds() method after onRenderedAds() method of the SDK listener with true status. onRenderedAds() method signifies that the video ad request has successfully received a response.```


Here is a simple implementation of the listener methods:

```java
@Override
public void onSuccess(String message) {
    Log.d("MainActivity", "onSuccess: " + message);
}
@Override
public void onRenderingAds(Boolean status) {
    Log.d("MainActivity", "onRenderingAds: " + Boolean.toString(status));
}
@Override
public void onRenderedAds(Boolean status) {
    Log.d("MainActivity", "onRenderedAds: " + Boolean.toString(status));
     //Always call the displayAds method after onRenderedAds success true

     if (status){
       tyrooVidAiSdk.displayAds();
   }
}
@Override
public void onDisplayAds(Boolean status) {
    Log.d("MainActivity", "onDisplayAds: " + Boolean.toString(status))   
}
@Override
public void onFailure(String errorMsg) {
    Log.e("MainActivity", "onFailure: " + errorMsg);
}
```



#### Step 6: Destroy SDK instance when user exits screen

You need to destroy SDK instance to prevent memory leaks whenever the user exits the ad screen.

```java
@Override
protected void onDestroy() {
	adView.removeAllViews();
	TyrooVidAiSdk.flush();
	super.onDestroy();
}
```



### 2. Display Ads without XML Layout 

To show a video ad without the layout, call ```onDisplayAds()``` method on the ```TyrooVidAiSdk```. This call should be made after the ```onRenderedAds``` event has been generated by the SDK. In this case SDK will automatically display ads on its own generated screen. In this case you don't need to call the method ```setAdViewLayout()``` of the SDK.


#### Step 1: Initialize Vid.AI SDK

You need to create an instance of ```TyrooVidAiSdk``` class and implement ```TyrooVidAiSdk.TyrooSdkListener``` in Activity or Fragment. A deveoper can decide whether he wants to use ```Activity``` or ```Fragment```.


Here is the example how you can initialize the Vid.ai SDK.

```java
import com.tyroo.tva.sdk.TyrooVidAiSdk;

public class MainActivity extends AppCompatActivity implements TyrooVidAiSdk.TyrooSdkListener {

     private TyrooVidAiSdk tyrooVidAiSdk;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTyrooVidAiSdk();
    }

    private void initTyrooVidAiSdk() {
        try {

            tyrooVidAiSdk = new TyrooVidAiSdk(getApplicationContext(), this);
            tyrooVidAiSdk.setPlacementId(placementId); //Your Placement ID as a String
            tyrooVidAiSdk.setDynamicPlacement(true);// Always set as true
            tyrooVidAiSdk.setPackageName(packageName); // Your application package name
            tyrooVidAiSdk.enableCaching(true); //set it true for preload videos file
            tyrooVidAiSdk.validate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 ```    


```Note: It’s important to use an Application Context instead of Activity context when calling TyrooVidAiSdk Constructor.```

#### Step 2: Implement SDK Listener

To be notified of SDK ads lifecycle events, you must implement ```TyrooVidAiSdk.TyrooSdkListener```. ```TyrooSdkListener``` is an interface in the ```TyrooVidAiSdk``` class that contains five callback methods. These methods will be called by the SDK when a state has changed: after successful SDK integration, after successful or unsuccessful ad render, after successful or unsuccessful ad display and on failure because of any other reason.


You can use these callbacks to know about the success or failure status and  take appropriate actions at your end.For example, in case of failure ( because of no ad response ), you may want to show appropriate messages to the end user.


```TyrooSdkListener``` interface has following callback methods:


```onSuccess(String status)```
> Based on the validation of placement id, dynamic placement, package name etc. this method will return true if SDK is successfully initialized. In case of failure it will return false.

```onRenderingAds(Boolean status)```
>This method is called when request for ads is under process.

```onRenderedAds(Boolean status)```
> This method is called when request for ads is successfully completed and ads are available to be shown.

```onDisplayAds(Boolean status)```
> This method is called when ads are successfully displayed on user screen.

```onFailure(String errorMsg)```
> This method is called when a request to fetch ads fails. The status message indicating the reason for failure is available as parameters.



#### Step 3: Display Ads
To display ads app developer needs to call ```displayAds()``` method of the ```TyrooVidAiSdk``` instance. At this point deveoper can call ```displayAds()``` method with empty parameter. In this case SDK will show the ads on its own layout screen.


For example:

```java
@Override
public void onRenderedAds(Boolean status) {     
        if (status) {
            tyrooVidAiSdk.displayAds();
	 }
 }
```

``` Note: It is highly recommended to call tyrooVidAiSdk.displayAds() method after onRenderedAds() method of the SDK listener. onRenderedAds() method signifies that the video ad request has successfully received a response.```


Here is a simple implementation of the listener methods:

```java
    @Override
    public void onSuccess(String message) {
        Log.d("MainActivity", "onSuccess: " + message);
    }
    @Override
    public void onRenderingAds(Boolean status) {
        Log.d("MainActivity", "onRenderingAds: " + Boolean.toString(status));
    }
    @Override
    public void onRenderedAds(Boolean status) {
        Log.d("MainActivity", "onRenderedAds: " + Boolean.toString(status));
          if (status) {
            tyrooVidAiSdk.displayAds();
	 }
    }
    @Override
    public void onDisplayAds(Boolean status) {
        Log.d("MainActivity", "onDisplayAds: " + Boolean.toString(status));
    }
    @Override
    public void onFailure(String errorMsg) {
        Log.e("MainActivity", "onFailure: " + errorMsg);
    }
}
```

### Step 4: Destroy SDK instance when user exits screen

You need to destroy SDK instance to prevent memory leaks whenever the user exits the ad screen.

```java
@Override

protected void onDestroy() {
	   adView.removeAllViews();
	   TyrooVidAiSdk.flush();
	   super.onDestroy();
 }
```

| Method Arguments   | Definitions | 
| -------------      |:--------------------------------------------------------------:|
| context            | context of the Application.                                    |
| Placement Id       | Kindly obtain your Placement Id from Tyroo Account Manager     |
| Package Name       | Package name of the Application.                               |
| Dynamic Placement  | This needs to be set true(in string like "true") for all cases.|


### 3. ProGuard Rules

If you are using ProGuard you might need to add the following option:

```groovy

-keep class * {
    public private *;
}
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
```
