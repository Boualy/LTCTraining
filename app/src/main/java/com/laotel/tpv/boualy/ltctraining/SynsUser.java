package com.laotel.tpv.boualy.ltctraining;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

/**
 * Created by Boualy on 14/12/2016.
 */

public class SynsUser extends AsyncTask<Void, Void, String> {

    private Context context;
    private static final String urlJSON = "http://swiftcodingthai.com/ltc/get_user_thely.php";

    public SynsUser(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(Void... params) {
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            Request.Builder builder = new Request.Builder();
            Request request = builder.url(urlJSON).build();
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();

        } catch (Exception e) {
            Log.d("14DecV2", "e do in ==>" + e.toString().trim());
            //return null;
            return null;
        }


    }
}
