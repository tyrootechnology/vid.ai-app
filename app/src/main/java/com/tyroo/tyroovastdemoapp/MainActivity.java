package com.tyroo.tyroovastdemoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.tyroo.tva.sdk.TyrooVidAiSdk;

public class MainActivity extends AppCompatActivity implements TyrooVidAiSdk.TyrooSdkListener {

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
        //adView = (AdView) findViewById(R.id.adView);

        initTyrooVidAiSdk();
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

    private void initTyrooVidAiSdk() {
        try {
            tyrooVidAiSdk = new TyrooVidAiSdk(getApplicationContext(), this);//TyrooVidAiSdk.initialize(getApplicationContext());
            tyrooVidAiSdk.setPlacementId("1637"); // 1559 or 1563
            tyrooVidAiSdk.setDynamicPlacement(true);
            tyrooVidAiSdk.enableCaching(true);
            tyrooVidAiSdk.setPackageName("009");
            tyrooVidAiSdk.validate();


            btnInterstitial.setVisibility(View.INVISIBLE);
            btnInterstitial.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //startActivity(new Intent(MainActivity.this, DiscoverActivity.class));
                    tyrooVidAiSdk.displayAds();
                }
            });
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
    public void onSuccess(String message, String placementId) {
        Log.d("MainActivity", "onSuccess: " + message);

    }

    @Override
    public void onRenderingAds(Boolean status) {
        Log.d("MainActivity", "onRenderingAds: " + Boolean.toString(status));
    }

    @Override
    public void onRenderedAds(Boolean status, String placementId) {
        Log.d("MainActivity", "onRenderedAds: " + Boolean.toString(status));
        //tvWait.setVisibility(View.GONE);
        btnInterstitial.setVisibility(View.VISIBLE);
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
