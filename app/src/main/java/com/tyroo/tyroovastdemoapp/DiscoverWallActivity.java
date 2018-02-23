package com.tyroo.tyroovastdemoapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.tyroo.tva.sdk.AdView;
import com.tyroo.tva.sdk.TyrooVidAiSdk;

public class DiscoverWallActivity extends AppCompatActivity implements TyrooVidAiSdk.TyrooSdkListener {

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
            tyrooVidAiSdk = new TyrooVidAiSdk(getApplicationContext(), this);//TyrooVidAiSdk.initialize(getApplicationContext());
            tyrooVidAiSdk.setPlacementId("1559"); // 1559 or 1563
            tyrooVidAiSdk.setAdViewLayout(adView);
            tyrooVidAiSdk.setDynamicPlacement(true);
            tyrooVidAiSdk.enableCaching(true);
            tyrooVidAiSdk.setPackageName("009");
            tyrooVidAiSdk.validate();

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
    public void onSuccess(String message, String placementId) {
        Log.d("DiscoverWallActivity", "onSuccess: " + message);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onRenderingAds(Boolean status) {
        Log.d("DiscoverWallActivity", "onRenderingAds: " + Boolean.toString(status));
    }

    @Override
    public void onRenderedAds(Boolean status, String placementId) {
        Log.d("DiscoverWallActivity", "onRenderedAds: " + Boolean.toString(status));
        if (status){
            tyrooVidAiSdk.displayAds();
        }
    }

    @Override
    public void onDisplayAds(Boolean status) {
        Log.d("DiscoverWallActivity", "onDisplayAds: " + Boolean.toString(status));
    }

    @Override
    public void onFailure(String errorMsg) {
        Log.e("DiscoverWallActivity", "onFailure: " + errorMsg);
    }
}
