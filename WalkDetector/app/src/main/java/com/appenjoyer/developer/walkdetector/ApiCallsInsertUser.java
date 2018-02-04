package com.appenjoyer.developer.walkdetector;



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

public class ApiCallsInsertUser extends AsyncTask<String,Void,String> {

    private Context mContext;
    private UserPOJO user;
    public ApiCallsInsertUser(Context context, UserPOJO user)
    {
        this.user = user;
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
            HttpPost httpPost = new HttpPost(mContext.getResources().getString(R.string.apiUrlInsertUser));

            String json = "";
            /*
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);

            conn.setDoInput(true);
            */
            JSONObject jsonObject = new JSONObject();

            jsonObject.put("name", user.getName());
            jsonObject.put("surname", user.getSurname());
            jsonObject.put("address", user.getAddress());
            jsonObject.put("telf", user.getTelf());
            jsonObject.put("email", user.getEmail());
            jsonObject.put("password", user.getPassword());
            jsonObject.put("gender", user.getGender());
            jsonObject.put("birthday", user.getBirthday());

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
                result = inputStream.toString();}
            else
                result = "Did not work!";
            }

        catch (Exception e){

        }

            return result;
    }


    @Override
    protected void onPostExecute(String s) {


    }
}