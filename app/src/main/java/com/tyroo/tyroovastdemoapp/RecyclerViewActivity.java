package com.tyroo.tyroovastdemoapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.tyroo.tva.sdk.TyrooVidAiSdk;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity implements TyrooVidAiSdk.TyrooSdkListener{

    public static final String TAG = RecyclerViewActivity.class.getSimpleName();

    private Context mContext;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    TyrooVidAiSdk tyrooVidAiSdk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        try {
            initTyrooVidAiSdk();
            init();
            getViews();
            bindData();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void init() {
        mContext = this;
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void getViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_mix_ad_content);
        mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void initTyrooVidAiSdk() {
        try {
            tyrooVidAiSdk = new TyrooVidAiSdk(getApplicationContext(), this);//TyrooVidAiSdk.initialize(getApplicationContext());
            tyrooVidAiSdk.setPlacementId("1707"); //1637, 1673 or 1563
            tyrooVidAiSdk.setDynamicPlacement(true);
            tyrooVidAiSdk.enableCaching(true);
            tyrooVidAiSdk.setPackageName("009");
            tyrooVidAiSdk.validate();


            /*btnDiscover.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //startActivity(new Intent(MainActivity.this, DiscoverActivity.class));
                    tyrooVidAiSdk.displayAds();

                }
            });*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        tyrooVidAiSdk.flush();
    }

    public void bindData() {
        List<ImageDto> dataList = new ArrayList<>();
        dataList.add(new ImageDto(R.drawable.bprm, "Black Panther"));
        dataList.add(new ImageDto(R.drawable.creative, "Enlighten Up"));
        dataList.add(new ImageDto(R.drawable.vbcvb, "Feel Nature"));
        dataList.add(new ImageDto(R.drawable.vbcvb, "AdView"));
        dataList.add(new ImageDto(R.drawable.bprm, "Black Panther"));

        recyclerViewAdapter = new RecyclerViewAdapter(dataList, mContext);
        mRecyclerView.setAdapter(recyclerViewAdapter);
        setSharedContentItemClickListener(recyclerViewAdapter);
    }

    private void setSharedContentItemClickListener(final RecyclerViewAdapter mAdapter) {
        mAdapter.setOnItemClickListener(new RecyclerViewAdapter.RecyclerViewItemClick() {
            @Override
            public void onItemClick(int position, View v) {
                /*try {

                } catch (Exception e) {
                    e.printStackTrace();
                }*/
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();

    }


    @Override
    public void onSuccess(String message, String placementId) {
        Log.d("RecyclerViewActivity", "onSuccess: " + message);

    }

    @Override
    public void onRenderingAds(Boolean status) {
        Log.d("RecyclerViewActivity", "onRenderingAds: " + Boolean.toString(status));
    }

    @Override
    public void onRenderedAds(Boolean status, String placementId) {
        Log.d("RecyclerViewActivity", "onRenderedAds: " + Boolean.toString(status));

    }

    @Override
    public void onDisplayAds(Boolean status) {
        Log.d("RecyclerViewActivity", "onDisplayAds: " + Boolean.toString(status));
    }

    @Override
    public void onFailure(String errorMsg) {
        Log.e("RecyclerViewActivity", "onFailure: " + errorMsg);
    }
}
