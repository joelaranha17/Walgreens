package com.firstapp.joel.walgreens.util.one;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.firstapp.joel.walgreens.R;
import com.firstapp.joel.walgreens.util.login.LoginActivity;
import com.firstapp.joel.walgreens.util.login.Register;

public class Mailbox extends AppCompatActivity {

    Button login,register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mailbox);

        SharedPreferences spref = this.getSharedPreferences("file5", Context.MODE_PRIVATE);
// then you use
        String apiKey = spref.getString("AppApiKey", null);
        String userID = spref.getString("UserID", null);
        Log.i("ShopItems", "Api " + apiKey + " User " + userID);


        login = (Button) findViewById(R.id.buttonLogin);
        register = (Button) findViewById(R.id.buttonRegister);

        if (apiKey != null && userID != null) {
            Intent alreadyloggedin = new Intent(this, WelcomeActivity.class);
            startActivity(alreadyloggedin);
        } else {
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    loginMethod();
                }
            });
            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    registerMethod();
                }
            });
        }
    }
    private void  loginMethod() {
        Intent gotologinpage = new Intent(this, LoginActivity.class);
        startActivity(gotologinpage);
        finish();
    }

    private void registerMethod() {
        Intent gotoregisterpage = new Intent(this, Register.class);
        startActivity(gotoregisterpage);
        finish();

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent backbuttonpressed = new Intent(this, MainActivity.class);
        startActivity(backbuttonpressed);
        finish();
    }
}