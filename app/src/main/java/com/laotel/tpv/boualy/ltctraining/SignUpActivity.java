package com.laotel.tpv.boualy.ltctraining;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {


    // Explicit
    private EditText nameEditTex, userEditText, passEditText;
    private String nameString, userString, passString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        nameEditTex = (EditText) findViewById(R.id.editText);
        userEditText = (EditText) findViewById(R.id.editText2);
        passEditText = (EditText) findViewById(R.id.editText3);

    } // Main Method

    public void clickSignUpSign(View view) {

        nameString = nameEditTex.getText().toString().trim();
        userString = userEditText.getText().toString().trim();
        passString = passEditText.getText().toString().trim();

        // Check Space

        if (nameString.equals("") || userString.equals("") || passString.equals("")) {
            // have space
            Log.d("13decV1", "Have space");
        }

    } // ClickSign


} // Main Class
