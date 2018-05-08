Changelog
=====================

## 1.1.6 (May 08, 2018)
- Added New ErrorCode params in onFailure method.
- Removed preload ads method.
- Removed PreloadAdsListener Interface.
- Added new loadAds(), showAds() and isAdLoaded() method.
- Replace android native video player with Google ExoPlayer.
- Modified TyrooVidAiSdk.initialize(Context, PLACEMENT_ID, PACKAGE_NAME, listner).

## 1.1.3 (April 04, 2018)
- Remove response cache time.
- Resolve OOM bug from video caching task.
- Resolve Interstitial close button bug.

## 1.1.2 (March 28, 2018)
- Modified TyrooAdListener callbacks and added onAdClicked, onAdOpened, onAdLeftApplication etc methods.
- Change integration process.
- Added new preload ads method.
- Added PreloadAdsListener Interface.

## 1.1.1 (March 20, 2018)
- Handled println exception.

## 1.0.7 (March 05, 2018)
- Support for 18:9 resolution.
- Update proguard-rules.
- Improvements in InVideoFeed ad format.

## 1.0.6 (Feb 23, 2018)
- Added new InVideoFeed Ad format.

## 1.0.5
- Removed AdView parameter from ```onDisplayAds()```.
- Added new method named ```setAdViewLayout(AdView)``` in ```TyrooVidAISdk``` which takes an ```AdView``` layout as parameter.
- Removed flick from video player and replaced BallPulseIndicator progress bar with android default circular progress bar.
- Added Alert Dialog for No Internet Connection in Discover Wall and Carousal View.

## 1.0.3
- Improved Video Player enter or exit animation.
- Reduce GPU overdraw.
- Handled Network connectivity.
- Close button added in Interstitial Screen

## 1.0.2
- Added support for VAST 2.0 and VAST 3.0
- Added support for android larger aspect ratio screen (display format with an aspect ratio of 18.5:9)
- Video player animation bug fixed.

## 1.0.1
- Bug fixed.

## 1.0.0
- Added Discover, carousel and vertical full screen video ad formats.
- Added Support for VAST 4.0
- Gesture Supported Video Player
