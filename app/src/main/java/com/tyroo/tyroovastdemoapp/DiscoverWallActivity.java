package com.tyroo.tyroovastdemoapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.tyroo.tva.sdk.AdView;
import com.tyroo.tva.sdk.TyrooVidAiSdk;

public class DiscoverWallActivity extends AppCompatActivity implements TyrooVidAiSdk.TyrooAdListener {

    private static final String TAG = "DiscoverWallActivity";
    AdView adView;
    TyrooVidAiSdk tyrooVidAiSdk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover_wall);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        adView = (AdView) findViewById(R.id.adView);
        initTyrooVidAiSdk();
    }

    private void initTyrooVidAiSdk() {
        try {
            tyrooVidAiSdk = TyrooVidAiSdk.initialize(getApplicationContext(),"1559","009", this);//TyrooVidAiSdk.initialize(getApplicationContext());
            tyrooVidAiSdk.setAdViewLayout(adView);
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
        adView.removeAllViews();
        tyrooVidAiSdk.flush();
        super.onDestroy();
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
}
