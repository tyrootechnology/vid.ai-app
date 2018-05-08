package com.tyroo.tyroovastdemoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.tyroo.tva.interfaces.TyrooAdListener;
import com.tyroo.tva.sdk.ErrorCode;
import com.tyroo.tva.sdk.TyrooVidAiSdk;

public class MainActivity extends AppCompatActivity implements TyrooAdListener {

    private static final String TAG = "MainActivity";
    Button btnVideoInFeed, btnDiscover, btnCarousal, btnInterstitial, btnWithRecyclerView;
    TyrooVidAiSdk tyrooVidAiSdk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnVideoInFeed = (Button) findViewById(R.id.btn_video_in_feed);
        btnDiscover = (Button) findViewById(R.id.btn_go_discover);
        btnCarousal = (Button) findViewById(R.id.btn_carousal);
        btnInterstitial = (Button) findViewById(R.id.btn_interstitial);
        btnWithRecyclerView = (Button) findViewById(R.id.btn_video_feed_with_rv);

        btnVideoInFeed.setOnClickListener(goVideoInFeedClick);
        btnWithRecyclerView.setOnClickListener(goVideoInFeedWithRecyclerView);
        btnDiscover.setOnClickListener(goDiscoverClick);
        btnCarousal.setOnClickListener(goCarousalClick);
        btnInterstitial.setVisibility(View.INVISIBLE);
        btnInterstitial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tyrooVidAiSdk == null || btnInterstitial.getText().equals("Refresh")){
                    loadInterstitialAds();
                }else {
                    if (tyrooVidAiSdk.isAdLoaded()) {
                        tyrooVidAiSdk.showAds();
                    }
                }
            }
        });

        loadInterstitialAds();
        //loadAllAds();
    }

    private View.OnClickListener goVideoInFeedClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(MainActivity.this, VideoInFeedActivity.class));
        }
    };

    private View.OnClickListener goVideoInFeedWithRecyclerView = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(MainActivity.this, RecyclerViewActivity.class));
        }
    };

    private View.OnClickListener goDiscoverClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(MainActivity.this, DiscoverWallActivity.class));
        }
    };

    private View.OnClickListener goCarousalClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(MainActivity.this, CarousalActivity.class));
        }
    };

    private void loadInterstitialAds() {
        try {
            tyrooVidAiSdk = TyrooVidAiSdk.initialize(getApplicationContext(),"1637","009", this);
            tyrooVidAiSdk.enableCaching(true);
            tyrooVidAiSdk.loadAds();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadAllAds() {
        try {
            TyrooVidAiSdk requestVideoFeed = TyrooVidAiSdk.initialize(getApplicationContext(),"1707","009", this);
            requestVideoFeed.enableCaching(true);
            requestVideoFeed.loadAds();

            TyrooVidAiSdk requestDiscoverWall = TyrooVidAiSdk.initialize(getApplicationContext(),"1559","009", this);
            requestDiscoverWall.enableCaching(true);
            requestDiscoverWall.loadAds();

            TyrooVidAiSdk requestCarousal = TyrooVidAiSdk.initialize(getApplicationContext(),"1635","009", this);
            requestCarousal.enableCaching(true);
            requestCarousal.loadAds();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
       // RefWatcher refWatcher = DemoApplication.getRefWatcher(this);
       // refWatcher.watch(this);
        if (tyrooVidAiSdk != null) {
            tyrooVidAiSdk.flush();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onAdLoaded(String placementId) {
        Log.d(TAG, "onAdLoaded: " + placementId);
        if (placementId.equals("1637")) {
            btnInterstitial.setText("Interstitial Ads");
            btnInterstitial.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onAdDisplayed() {
        Log.d(TAG, "onAdDisplayed");
    }

    @Override
    public void onAdOpened() {
        Log.d(TAG, "onAdOpened");
    }

    @Override
    public void onAdClosed() {
        Log.d(TAG, "onAdClosed");
        btnInterstitial.setText("Refresh");
    }

    @Override
    public void onAdCompleted() {
        Log.d(TAG, "onAdCompleted");
    }

    @Override
    public void onAdClicked() {
        Log.d(TAG, "onAdClicked");
    }

    @Override
    public void onAdLeftApplication() {
        Log.d(TAG, "onAdLeftApplication");
    }

    @Override
    public void onFailure(int errorCode, String errorMsg) {
        Log.e(TAG, "onFailure: " + errorMsg);
        btnInterstitial.setText("Refresh");
        switch (errorCode){
            case ErrorCode.BAD_REQUEST:
                //It may be due to some invalid/Blank value in the api request.
                break;
            case ErrorCode.NETWORK_ERROR:
                //It may be due to slow/no internet connectivity.
                break;
            case ErrorCode.NO_INVENTORY:
                //There is no fill.
                break;
            case ErrorCode.UNKNOWN:
                //In case of any unexpected error or exception.
                break;
            default:

        }
    }

}
