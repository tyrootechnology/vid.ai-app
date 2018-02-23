package com.tyroo.tyroovastdemoapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.tyroo.tva.sdk.AdView;
import com.tyroo.tva.sdk.TyrooVidAiSdk;


public class VideoInFeedActivity extends AppCompatActivity implements TyrooVidAiSdk.TyrooSdkListener {

    AdView adView;
    TyrooVidAiSdk tyrooVidAiSdk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_in_feed);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        adView = (AdView) findViewById(R.id.adView);
        initTyrooVidAiSdk();
    }

    private void initTyrooVidAiSdk() {
        try {
            tyrooVidAiSdk = new TyrooVidAiSdk(getApplicationContext(), this);//TyrooVidAiSdk.initialize(getApplicationContext());
            tyrooVidAiSdk.setPlacementId("1707");
            tyrooVidAiSdk.setAdViewLayout(adView);
            tyrooVidAiSdk.setDynamicPlacement(true);
            tyrooVidAiSdk.enableCaching(true);
            tyrooVidAiSdk.setPackageName("009");
            tyrooVidAiSdk.validate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        tyrooVidAiSdk.displayAds(adView);
//    }

    @Override
    protected void onDestroy() {
        //InitiateTyrooSdk.destroyInstance();
        adView.removeAllViews();
        tyrooVidAiSdk.flush();
        super.onDestroy();
    }

    @Override
    public void onSuccess(String message, String placementId) {
        Log.d("VideoInFeedActivity", "onSuccess: " + message);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onRenderingAds(Boolean status) {
        Log.d("VideoInFeedActivity", "onRenderingAds: " + Boolean.toString(status));
    }

    @Override
    public void onRenderedAds(Boolean status, String placementId) {
        Log.d("VideoInFeedActivity", "onRenderedAds: " + Boolean.toString(status));
        if (status) {
            tyrooVidAiSdk.displayAds();
        }
    }

    @Override
    public void onDisplayAds(Boolean status) {
        Log.d("VideoInFeedActivity", "onDisplayAds: " + Boolean.toString(status));
    }

    @Override
    public void onFailure(String errorMsg) {
        Log.e("VideoInFeedActivity", "onFailure: " + errorMsg);
    }
}
