package com.appenjoyer.developer.walkdetector;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;




public class ShopList extends AppCompatActivity {
    public static final String TAG = ShopList.class.getSimpleName();
    RecyclerView mRecyclerView;
    private ShopListAdapter mAdapter;
    private TextView tvGlobalPoints;

    public ArrayList<ShopOfferPOJO> shops = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("Ofertes");
        mRecyclerView = (RecyclerView)findViewById(R.id.recycleView);
        tvGlobalPoints = (TextView)findViewById(R.id.tvGains);
        tvGlobalPoints.setText(Constants.GLOBALPOINTS+"");
        shops.add(new ShopOfferPOJO("PcBox",R.mipmap.pcbox,"20%","3000"));
        shops.add(new ShopOfferPOJO("Prims",R.mipmap.prims,"15%","2300"));
        shops.add(new ShopOfferPOJO("Dismatac",R.mipmap.dismatac,"10%","2000"));
        shops.add(new ShopOfferPOJO("Ortopedia",R.mipmap.ortopedia,"20%","3000"));
        shops.add(new ShopOfferPOJO("Carniceria",R.mipmap.carniceria,"15%","2300"));
        mAdapter = new ShopListAdapter(getApplicationContext(), shops);
        mRecyclerView.setAdapter(mAdapter);
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(ShopList.this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);



    }

}
