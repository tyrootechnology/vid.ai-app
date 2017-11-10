package com.tyroo.tyroovastdemoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tyroo.tva.sdk.AdView;
import com.tyroo.tva.sdk.TyrooVidAiSdk;

public class MainActivity extends AppCompatActivity implements TyrooVidAiSdk.TyrooSdkListener {

    AdView adView;
    Button goDiscover;
    TyrooVidAiSdk tyrooVidAiSdk;
    TextView tvWait;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goDiscover = (Button) findViewById(R.id.btn_go_discover);
        adView = (AdView) findViewById(R.id.adView);
        tvWait = (TextView) findViewById(R.id.tv_wait);

        initTyrooVidAiSdk();
    }

    private void initTyrooVidAiSdk() {
        try {
            tyrooVidAiSdk = new TyrooVidAiSdk(getApplicationContext(), this);//TyrooVidAiSdk.initialize(getApplicationContext());
            tyrooVidAiSdk.setPlacementId("1559"); // 1559 or 1563
            tyrooVidAiSdk.setDynamicPlacement(true);
            tyrooVidAiSdk.enableCaching(true);
            tyrooVidAiSdk.setPackageName("009");
            tyrooVidAiSdk.validate();


            goDiscover.setVisibility(View.INVISIBLE);
            goDiscover.setOnClickListener(new View.OnClickListener() {
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
        adView.removeAllViews();
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
        tvWait.setVisibility(View.GONE);
        goDiscover.setVisibility(View.VISIBLE);
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
