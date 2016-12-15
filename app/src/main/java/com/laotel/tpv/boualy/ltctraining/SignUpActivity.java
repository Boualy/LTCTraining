package com.laotel.tpv.boualy.ltctraining;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    // Explicit
    private EditText nameEditTex, userEditText, passEditText;
    private String nameString, userString, passString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        InitInstances();

    } // Main Method

    private void InitInstances() {
        nameEditTex = (EditText) findViewById(R.id.editText);
        userEditText = (EditText) findViewById(R.id.editText2);
        passEditText = (EditText) findViewById(R.id.editText3);
    }

    public void clickSignUpSign(View view) {

        nameString = nameEditTex.getText().toString().trim();
        userString = userEditText.getText().toString().trim();
        passString = passEditText.getText().toString().trim();

        // Check Space
        if (nameString.equals("") || userString.equals("") || passString.equals("")) {
            // have space
            Log.d("13decV1", "Have space");
            MyAlert myAlert = new MyAlert(SignUpActivity.this, "Err Space", "Please fill all blank", R.drawable.doremon48);
            myAlert.myDialog();
        } else {

            try {
                InsertUser insertUser = new InsertUser(SignUpActivity.this, nameString, userString, passString);
                insertUser.execute();

                String s = insertUser.get();
                Log.d("14DecV1", "Result ==>" + s);

                if (Boolean.parseBoolean(s)) {
                    Toast.makeText(SignUpActivity.this, "Finished", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(SignUpActivity.this, "Can't Sign Up", Toast.LENGTH_SHORT).show();
                }

             //   finish();


            } catch (Exception e) {
                Log.d("14DecV1", "e signUp" + e.toString());
            }

        }

    } // ClickSign


} // Main Class
