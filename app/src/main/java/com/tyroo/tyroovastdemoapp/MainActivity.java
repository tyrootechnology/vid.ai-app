package com.tyroo.tyroovastdemoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.tyroo.tva.sdk.TyrooVidAiSdk;

public class MainActivity extends AppCompatActivity implements TyrooVidAiSdk.TyrooAdListener {

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

        btnInterstitial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayInterstitialAds();
            }
        });

        preLoadRequest();

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

    private void displayInterstitialAds() {
        try {
            tyrooVidAiSdk = TyrooVidAiSdk.initialize(getApplicationContext(),"1637","009", this);
            tyrooVidAiSdk.enableCaching(true);
            tyrooVidAiSdk.loadAds();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onDestroy() {
        //InitiateTyrooSdk.destroyInstance();
       // RefWatcher refWatcher = DemoApplication.getRefWatcher(this);
       // refWatcher.watch(this);
        tyrooVidAiSdk.flush();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        //adView.removeAllViews();
        super.onBackPressed();
    }

    @Override
    public void onAdLoaded(String placementId) {
        Log.d(TAG, "onAdLoaded: "+placementId);
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
    public void onFailure(String errorMsg) {
        Log.e(TAG, "onFailure: " + errorMsg);
    }

    private void preLoadRequest() {
        TyrooVidAiSdk.preLoadAds(getApplicationContext(), "1637", "009", true, new TyrooVidAiSdk.AdPreloadListener() {
            @Override
            public void onPreloadSuccess(String placementId) {
                Log.d(TAG, "onPreloadSuccess: "+placementId);
            }

            @Override
            public void onPreloadError(String errorMsg) {
                Log.e(TAG,"onPreloadError: "+errorMsg);
            }
        });
    }
}
