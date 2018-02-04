package com.appenjoyer.developer.walkdetector;

import android.location.Location;

/**
 * Created by Developer on 04/02/2018.
 */

/**
 * Created by Design on 03/02/2018.
 */

import android.content.Context;
import android.location.Location;
import android.os.AsyncTask;
import android.provider.Settings;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.InputStream;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;



import android.content.Context;
import android.os.AsyncTask;

import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;

/**
 * Created by Developer on 30/11/2017.
 */

public class ApiCallsInsertStreet extends AsyncTask<String,Void,String> {

    private Context mContext;
    private Location location;
    public ApiCallsInsertStreet(Context context, Location location)
    {
        this.location = location;
        this.mContext = context;
    }

    @Override
    protected String doInBackground(String... params) {

        InputStream inputStream = null;
        String result = "";

        try {

            // 1. create HttpClient

            HttpClient httpclient = new DefaultHttpClient();

            // 2. make POST request to the given URL
            HttpPost httpPost = new HttpPost(mContext.getResources().getString(R.string.apiUrlCoordSaveLocation));

            String json = "";
 /*
 conn.setReadTimeout(10000);
 conn.setConnectTimeout(15000);

 conn.setDoInput(true);
 */
            JSONObject jsonObject = new JSONObject();

            jsonObject.put("lat", String.valueOf(location.getLatitude()));
            jsonObject.put("lng", String.valueOf(location.getLongitude()));


            // 4. convert JSONObject to JSON to String
            json = jsonObject.toString();


            // 5. set json to StringEntity
            StringEntity se = new StringEntity(json);

            // 6. set httpPost Entity
            httpPost.setEntity(se);

            // 7. Set some headers to inform server about the type of the content
            httpPost.setHeader("Content-type", "application/json");

            // 8. Execute POST request to the given URL
            HttpResponse httpResponse = httpclient.execute(httpPost);

            // 9. receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();


            // 10. convert inputstream to string

            if (inputStream != null){
                BufferedReader bR = new BufferedReader(  new InputStreamReader(inputStream));
                String line = "";
                while((line =  bR.readLine()) != null){

                    if(line.equals("Unnamed Road")){
                        result = "Did not work!";
                        break;
                    }
                    else{
                        result = line;
                    }
                }
                inputStream.close();

            }

            else
                result = "Did not work!";
        }

        catch (Exception e){

        }

        return result;
    }


    @Override
    protected void onPostExecute(String s) {
        if(s.equals("Did not work!" )) return;

        Toast.makeText(mContext, s, Toast.LENGTH_SHORT).show();
    }
}
