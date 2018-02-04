package com.appenjoyer.developer.walkdetector;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    Button btnTopUsers,btnCamina,btnBotiga;
    TextView tvAbdulaPoints;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("El meu perfil");
        setSupportActionBar(toolbar);

        btnTopUsers = (Button)findViewById(R.id.btnTop);
        btnCamina = (Button)findViewById(R.id.btnCamina);
        btnBotiga = (Button)findViewById(R.id.btnShop);
        tvAbdulaPoints = (TextView)findViewById(R.id.tvAbdulaPunts);
        tvAbdulaPoints.setText(Constants.GLOBALPOINTS+"");
        btnTopUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(),TopUsersActivity.class));
            }
        });
        btnCamina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(),MainActivity.class));
            }
        });
        btnBotiga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(),ShopList.class));
            }
        });



    }

}
