package com.tyroo.tyroovastdemoapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.tyroo.tva.interfaces.TyrooAdListener;
import com.tyroo.tva.sdk.AdView;
import com.tyroo.tva.sdk.ErrorCode;
import com.tyroo.tva.sdk.TyrooVidAiSdk;

public class DiscoverWallActivity extends AppCompatActivity implements TyrooAdListener {

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
            tyrooVidAiSdk = TyrooVidAiSdk.initialize(getApplicationContext(),"1559","009", this);
            tyrooVidAiSdk.setAdViewLayout(adView);
            tyrooVidAiSdk.enableCaching(true);
            tyrooVidAiSdk.loadAds();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // RefWatcher refWatcher = DemoApplication.getRefWatcher(this);
        // refWatcher.watch(this);
        adView.removeAllViews();
        tyrooVidAiSdk.flush();

    }

    @Override
    public void onAdLoaded(String placementId) {
        Log.d(TAG, "onAdLoaded: " + placementId);
        if (tyrooVidAiSdk.isAdLoaded()) {
            tyrooVidAiSdk.showAds();
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
