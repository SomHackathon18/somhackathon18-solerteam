package com.appenjoyer.developer.walkdetector;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;

/**
 * Created by Developer on 03/02/2018.
 */

public class ApiCallsLoginUser  extends AsyncTask<String,Void,String> {

    private ProgressDialog dialog;
    private Context mContext;
    private UserPOJO user;
    public ApiCallsLoginUser(Context context, UserPOJO user)
    {
        dialog = new ProgressDialog(context);
        this.user = user;
        this.mContext = context;
    }
    @Override
    protected void onPreExecute() {
        dialog.setMessage("Loggin in, please wait.");
        dialog.show();
    }

    @Override
    protected String doInBackground(String... params) {

        InputStream inputStream = null;
        String result = "";

        try {

            // 1. create HttpClient

            HttpClient httpclient = new DefaultHttpClient();

            // 2. make POST request to the given URL
            HttpPost httpPost = new HttpPost(mContext.getResources().getString(R.string.apiUrlLoginUser));

            String json = "";
            /*
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);

            conn.setDoInput(true);
            */
            JSONObject jsonObject = new JSONObject();

            jsonObject.put("email", user.getEmail());
            jsonObject.put("password", user.getPassword());

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
                StringBuilder responseStrBuilder = new StringBuilder();
                while((line =  bR.readLine()) != null){

                    responseStrBuilder.append(line);
                }
                inputStream.close();
                Log.d("Response",responseStrBuilder.toString());
                if(responseStrBuilder.toString().contains("null")) result = "Did not work!";
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
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
        if(s.equals("Did not work!")) return;
        mContext.startActivity(new Intent(mContext, ProfileActivity.class));
    }

}
