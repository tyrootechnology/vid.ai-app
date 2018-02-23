package com.tyroo.tyroovastdemoapp;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tyroo.tva.sdk.AdView;
import com.tyroo.tva.sdk.TyrooVidAiSdk;
import com.tyroo.tva.utils.TyrooLog;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    public static final String TAG = RecyclerViewAdapter.class.getSimpleName();

    // A normal item view type.
    private static final int NORMAL_ITEM_VIEW_TYPE = 0;

    // The video feed ad view type.
    private static final int VIDEO_FEED_AD_VIEW_TYPE = 1;

    private List<ImageDto> mDataList;
    private static RecyclerViewItemClick itemClickListener;
    private Context mContext;
    private String placementList[] = {"1707", "1707"};

    public class FeedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvTitle;
        ImageView imageView;

        public FeedViewHolder(View itemView) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public class AdViewHolder extends RecyclerView.ViewHolder implements TyrooVidAiSdk.TyrooSdkListener{

        AdView adView;
        TyrooVidAiSdk tyrooVidAiSdk;

        public AdViewHolder(View itemView) {
            super(itemView);

            adView = (AdView) itemView.findViewById(R.id.item_ad_view);
            try {
                tyrooVidAiSdk = new TyrooVidAiSdk(mContext.getApplicationContext(),this);//TyrooVidAiSdk.initialize(getApplicationContext());
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

        @Override
        public void onSuccess(String message, String placementId) {
            Log.d(TAG, "onSuccess: " + message);

        }

        @Override
        public void onRenderingAds(Boolean status) {
            Log.d(TAG, "onRenderingAds: " + Boolean.toString(status));
        }

        @Override
        public void onRenderedAds(Boolean status, String placementId) {
            Log.d(TAG, "onRenderedAds: " + Boolean.toString(status)+" with placement: "+placementId);

            if (status) {
                tyrooVidAiSdk.displayAds();
                //btnDiscover.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onDisplayAds(Boolean status) {
            Log.d(TAG, "onDisplayAds: " + Boolean.toString(status));
        }

        @Override
        public void onFailure(String errorMsg) {
            Log.e(TAG, "onFailure: " + errorMsg);
        }

        public void displayAds(){

        }

    }

    public void setOnItemClickListener(RecyclerViewItemClick myClickListener) {
        this.itemClickListener = myClickListener;
    }

    public RecyclerViewAdapter(List<ImageDto> data, Context context) {
        mDataList = data;
        mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (mDataList.get(position).getDescription() == "AdView") {
            return VIDEO_FEED_AD_VIEW_TYPE;
        } else {
            return NORMAL_ITEM_VIEW_TYPE;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == NORMAL_ITEM_VIEW_TYPE) {

            View normalView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_content, parent, false);
            return new FeedViewHolder(normalView);

        } else {
            View adView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_ad_content, parent, false);
            return new AdViewHolder(adView);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ImageDto data = mDataList.get(position);

        int viewType = getItemViewType(position);
        switch (viewType) {
            case NORMAL_ITEM_VIEW_TYPE:
                FeedViewHolder feedViewHolder = (FeedViewHolder) holder;
                feedViewHolder.tvTitle.setText(data.getDescription());
                feedViewHolder.imageView.setImageResource(data.getImageId());
                break;
            case VIDEO_FEED_AD_VIEW_TYPE:
                TyrooLog.d(TAG, "onBindViewHolder");
                AdViewHolder adViewHolder = (AdViewHolder) holder;

                //adViewHolder.adView.videoAutoPlay();
                break;

        }

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public interface RecyclerViewItemClick {
        void onItemClick(int position, View v);
    }

    public ImageDto getItem(int position) {
        return mDataList.get(position);
    }

    public void updateList(List<ImageDto> dataList) {
        mDataList = dataList;
        notifyDataSetChanged();
    }

    public void addList(List<ImageDto> dataList) {
        mDataList.addAll(dataList);
        notifyDataSetChanged();
    }
}

