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

public class RecyclerViewActivity extends AppCompatActivity {

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
            init();
            getViews();
            bindData();
        } catch (Exception e) {
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


    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (tyrooVidAiSdk != null) {
            tyrooVidAiSdk.flush();
        }
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
}
