package com.appenjoyer.developer.walkdetector;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Developer on 03/02/2018.
 */

public class ShopListAdapter extends RecyclerView.Adapter<ShopListAdapter.ShopViewHolder> {
    private ArrayList<ShopOfferPOJO> mShops = new ArrayList<>();
    private Context mContext;

    public ShopListAdapter(Context context, ArrayList<ShopOfferPOJO> shops) {
        mContext = context;
        this.mShops = shops;
    }
    @Override
    public ShopListAdapter.ShopViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_list_items, parent, false);
        ShopViewHolder viewHolder = new ShopViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ShopListAdapter.ShopViewHolder holder, int position) {
        holder.bindRestaurant(mShops.get(position));
    }

    @Override
    public int getItemCount() {
        return mShops.size();
    }

    public class ShopViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.shopImageView) ImageView mShopImage;
        @BindView(R.id.shopNameTextView) TextView mShopName;
        @BindView(R.id.shopDiscount) TextView mShopDiscount;
        @BindView(R.id.shopPoints) TextView mShopPoints;
        private Context mContext;

        public ShopViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindRestaurant(ShopOfferPOJO shops) {
            mShopImage.setImageResource(shops.getShopImage());
            mShopName.setText(shops.getShopName());
            mShopDiscount.setText(shops.getShopDiscount());
            mShopPoints.setText(shops.getShopPoints());
        }
    }
}
