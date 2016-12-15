package com.laotel.tpv.boualy.ltctraining;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private EditText userEditText, passwordEditText;
    private String userString, passwordString;
    private String[] loginStrings;
    private Boolean aBoolean = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initInstances();
    }

    private void initInstances() {
        userEditText = (EditText) findViewById(R.id.ET_User);
        passwordEditText = (EditText) findViewById(R.id.ET_Pass);
    }

    //// Button Sign Up
    public void SignUpMain(View view) {
        //// Start new Activity with Intent
        startActivity(new Intent(MainActivity.this, SignUpActivity.class));
    }
    //// Button Sign In
    public void SignInMain(View view) {
        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();
        if (userString.equals("")) {
            // Requite Username
            MyAlert myAlert = new MyAlert(MainActivity.this, getResources().getString(R.string.title_err_user),
                    getResources().getString(R.string.message_err_user),
                    R.drawable.nobita48);
            myAlert.myDialog();

        } else if (passwordString.equals("")) {
            // Requite Password
            MyAlert myAlert = new MyAlert(MainActivity.this, getResources().getString(R.string.title_err_pass),
                    getResources().getString(R.string.message_err_pass),
                    R.drawable.nobita48);
            myAlert.myDialog();
        } else {
            /// Check username and Password
            try {
                SynsUser synsUser = new SynsUser(MainActivity.this);
                synsUser.execute();
                String s = synsUser.get();
                Log.d("14DecV2", "JSON Log ==>" + s);

                /// Split array from JSON
                JSONArray jsonArray = new JSONArray(s);
                loginStrings = new String[4];
                for (int i = 0; i < jsonArray.length(); i += 1) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    if (userString.equals(jsonObject.getString("User"))) {
                        loginStrings[0] = jsonObject.getString("id");
                        loginStrings[1] = jsonObject.getString("Name");
                        loginStrings[2] = jsonObject.getString("User");
                        loginStrings[3] = jsonObject.getString("Password");
                        aBoolean = false;
                    } /// IF

                } /// For

                if (aBoolean) {
                    //Toast.makeText(MainActivity.this, "ERR", Toast.LENGTH_SHORT).show();
                    MyAlert myAlert = new MyAlert(MainActivity.this,
                            getResources().getString(R.string.title_user_false),
                            getResources().getString(R.string.message_user_false),
                            R.drawable.rat48);

                } else if (passwordString.equals(loginStrings[3])) {
                    //Password True
                    Toast.makeText(MainActivity.this, "Welcome " + loginStrings[1], Toast.LENGTH_SHORT).show();

                    /// Open map on Service Activity class
                    Intent intent = new Intent(MainActivity.this, ServiceActivity.class);
                    intent.putExtra("Login", loginStrings);
                    startActivity(intent);
                    finish();

                } else {
                    //Password False
                    MyAlert myAlert = new MyAlert(MainActivity.this,
                            getResources().getString(R.string.title_pass_false),
                            getResources().getString(R.string.message_pass_false),
                            R.drawable.kon48);
                    myAlert.myDialog();
                }

            } catch (Exception e) {
                Log.d("14DecV2", "e Main ==>" + e.toString().trim());
            }


        }

    }

}
