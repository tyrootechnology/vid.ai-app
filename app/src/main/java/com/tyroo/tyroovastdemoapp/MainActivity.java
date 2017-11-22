package com.tyroo.tyroovastdemoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.tyroo.tva.sdk.TyrooVidAiSdk;

public class MainActivity extends AppCompatActivity implements TyrooVidAiSdk.TyrooSdkListener {

    Button goDiscover, goCarousal, goInterstitial;
    TyrooVidAiSdk tyrooVidAiSdk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goDiscover = (Button) findViewById(R.id.btn_go_discover);
        goCarousal = (Button) findViewById(R.id.btn_carousal);
        goInterstitial = (Button) findViewById(R.id.btn_interstitial);

        goDiscover.setOnClickListener(goDiscoverClick);
        goCarousal.setOnClickListener(goCarousalClick);
        goInterstitial.setVisibility(View.INVISIBLE);
        //adView = (AdView) findViewById(R.id.adView);

        initTyrooVidAiSdk();
    }
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


            goInterstitial.setVisibility(View.INVISIBLE);
            goInterstitial.setOnClickListener(new View.OnClickListener() {
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
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        //adView.removeAllViews();
        TyrooVidAiSdk.flush();
        super.onBackPressed();
    }

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
        //tvWait.setVisibility(View.GONE);
        goInterstitial.setVisibility(View.VISIBLE);
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
