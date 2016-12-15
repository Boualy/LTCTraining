package com.laotel.tpv.boualy.ltctraining;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

/**
 * Created by Boualy on 14/12/2016.
 */

/// AsyncTask(Param, Progressbar, result)

public class InsertUser extends AsyncTask<Void, Void, String> {
    private static final String urlPHP = "http://swiftcodingthai.com/ltc/add_thely_master.php";
    private Context context;
    private String nameString, userString, passString;

    ///  Alt + Insert = Generate Constructor, Method, ...
    public InsertUser(Context context, String nameString, String userString, String passString) {
        this.context = context;
        this.nameString = nameString;
        this.userString = userString;
        this.passString = passString;
    }

    @Override
    protected String doInBackground(Void... params) {
        //// Set khar pai database via PHP
        try {

            OkHttpClient okHttpClient = new OkHttpClient();
            //// Create package string
            RequestBody requestBody = new FormEncodingBuilder()
                    .add("isAdd","true")
                    .add("Name",nameString)
                    .add("User",userString)
                    .add("Password",passString)
                    .build();

            Request.Builder builder = new Request.Builder();
            Request request = builder.url(urlPHP).post(requestBody).build();
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();

        } catch (Exception e) {
            Log.d("14DecV1", "e doing ==>" + e.toString().trim());
            return null;
        }


    }

    /// Trade AsyncTask
}
