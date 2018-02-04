package com.appenjoyer.developer.walkdetector;
import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.DetectedActivity;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    BroadcastReceiver broadcastReceiver;
    private CountDownTimer cdtTimer;
    private TextView txtActivity, txtConfidence;
    private ImageView imgActivity;
    private Button btnStartTrcking;

    //TextViews
    private TextView timerValue;
    private TextView tvPoints;

    private long startTime = 0L;

    private Handler customHandler = new Handler();

    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;


    //Points counter
    private int points = 0;
    private int frequencyPoints = 0;
    private final int FREQUENCY_ADD_POINTS = 5;

    private String carrerActual = "";



    /*
    private int timerTick = 0;
    private long secondsPassed = 0;
    private long minutesPassed = 0;*/
    LocationManager locationManager;
    Location location;
    TextView tvCarrer;
    int streetNumber = 0;
    int streetNumberBonus = 0;
    ImageView ivStar;


    Button btnCamina,btnTopSemanal,btnTenda;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("Disfruta caminant");
        txtActivity = findViewById(R.id.txt_activity);
        txtConfidence = findViewById(R.id.txt_confidence);
        imgActivity = findViewById(R.id.img_activity);
        btnStartTrcking = findViewById(R.id.btn_start_tracking);

        timerValue = findViewById(R.id.timerValue);
        tvPoints = findViewById(R.id.tvPoints);
        tvCarrer = findViewById(R.id.tvCarrer);
        ivStar = findViewById(R.id.ivStar);


        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        btnStartTrcking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTracking();
            }
        });



        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(Constants.BROADCAST_DETECTED_ACTIVITY)) {
                    int type = intent.getIntExtra("type", -1);
                    int confidence = intent.getIntExtra("confidence", 0);
                    handleUserActivity(type, confidence);
                }
            }
        };

        startTracking();
    }
    /*private void StartTimer(){
        final long EndTime   = 3600;
        cdtTimer = new CountDownTimer(EndTime*1000, 1000) {
            public void onTick(long millisUntilFinished) {
                long secondUntilFinished = (long) (millisUntilFinished/1000);
                secondsPassed = (EndTime);
                minutesPassed = (long) (secondsPassed/60);
                if(timerTick == 15){
                    points += timerTick * 10;
                    timerTick = 0;
                }
                else{timerTick++;}
                secondsPassed = secondsPassed%60;
                tvTimeGame.setText(String.format("%02d", minutesPassed) + ":" + String.format("%02d", secondsPassed));
                tvPoints.setText(points+"");
            }
            public void onFinish() {
                tvTimeGame.setText("done!");
            }
        }.start();
    }*/

    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;

            updatedTime = timeSwapBuff + timeInMilliseconds;
            int secs = (int) (updatedTime / 1000);
            int mins = secs / 60;
            secs = secs % 60;
            //Suma de puntuacion
            if (frequencyPoints == FREQUENCY_ADD_POINTS) {
                points += streetNumberBonus * frequencyPoints;
                Constants.GLOBALPOINTS = points;
                frequencyPoints = 0;
                getLocation();
            } else {
                streetNumber++;
                frequencyPoints++;
                switch(streetNumber){
                    case 5:
                        tvCarrer.setText("Ctra. de Barcelona, 11, 08302 Mataró, Barcelona, Spain");
                        tvCarrer.setTextColor(ResourcesCompat.getColor(getResources(), R.color.red, null));
                        ivStar.setImageResource(R.mipmap.nostars);
                        streetNumberBonus = 0;
                        break;
                    case 15:
                        tvCarrer.setText("Carrer d'Ernest Lluch, 14, 08302 Mataró, Barcelona, Spain");
                        tvCarrer.setTextColor(ResourcesCompat.getColor(getResources(), R.color.green, null));
                        ivStar.setImageResource(R.mipmap.onestar);
                        streetNumberBonus = 1;

                        break;
                    case 25:
                        tvCarrer.setText("Carrer Narcís Monturiol, 08302 Mataró, Barcelona, Spain");
                        tvCarrer.setTextColor(ResourcesCompat.getColor(getResources(), R.color.green, null));
                        ivStar.setImageResource(R.mipmap.twostars);
                        streetNumberBonus = 2;
                        streetNumber= 0;
                        break;
                }
            }

            timerValue.setText("" + mins + ":"
                    + String.format("%02d", secs));
            tvPoints.setText(points + "");
            customHandler.postDelayed(this, 1000);
        }

    };

    private void handleUserActivity(int type, int confidence) {
        String label = getString(R.string.activity_unknown);
        int icon = R.mipmap.ic_still;

        switch (type) {

            case DetectedActivity.ON_BICYCLE: {
                label = getString(R.string.activity_on_bicycle);
                icon = R.mipmap.ic_on_bicycle;
                break;
            }
            case DetectedActivity.ON_FOOT: {
                label = getString(R.string.activity_on_foot);
                icon = R.mipmap.ic_walking;
                break;
            }
            case DetectedActivity.RUNNING: {
                label = getString(R.string.activity_running);
                icon = R.mipmap.ic_running;
                break;
            }
            case DetectedActivity.STILL: {
                label = getString(R.string.activity_still);
                break;
            }
            case DetectedActivity.WALKING: {
                label = getString(R.string.activity_walking);
                icon = R.mipmap.ic_walking;
                break;
            }
            case DetectedActivity.UNKNOWN: {
                label = getString(R.string.activity_unknown);
                break;
            }
        }

        Log.e(TAG, "User activity: " + label + ", Confidence: " + confidence);

        if (confidence > Constants.CONFIDENCE) {
            //StartTimer();
            if(startTime == 0L) {
                startTime = SystemClock.uptimeMillis();
            }
            customHandler.postDelayed(updateTimerThread, 1000);
            txtActivity.setText(label);
            //txtConfidence.setText("Confidence: " + confidence);
            imgActivity.setImageResource(icon);


        } else {

            timeSwapBuff += timeInMilliseconds;
            customHandler.removeCallbacks(updateTimerThread);
            txtActivity.setText("Aturat");
            //txtConfidence.setText("Confidence: " + confidence);
            imgActivity.setImageResource(R.mipmap.ic_still);
        }
    }
    void getLocation() {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Constants.MY_PERMISSIONS_REQUEST_GET_LOCATION);

        } else {
            try{
                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                if (location != null){
                    new ApiCallsInsertStreet(this, location).execute("");
                    double lat = location.getLatitude();
                    double lng = location.getLongitude();




                }
            }catch (Exception e){
                Log.d("jjj","jjj");
            }


        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case Constants.MY_PERMISSIONS_REQUEST_GET_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    getLocation();

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver,
                new IntentFilter(Constants.BROADCAST_DETECTED_ACTIVITY));
    }

    @Override
    protected void onPause() {
        super.onPause();

        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
    }

    private void startTracking() {
        Intent intent1 = new Intent(MainActivity.this, BackgroundDetectedActivitiesService.class);
        startService(intent1);
    }

    private void stopTracking() {
        Intent intent = new Intent(MainActivity.this, BackgroundDetectedActivitiesService.class);
        stopService(intent);
    }


}