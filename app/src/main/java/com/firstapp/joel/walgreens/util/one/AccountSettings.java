package com.firstapp.joel.walgreens.util.one;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firstapp.joel.walgreens.R;
import com.firstapp.joel.walgreens.util.login.LoginActivity;
import com.firstapp.joel.walgreens.util.login.Register;

/**
 * Created by joel on 11/24/2017.
 */

public class AccountSettings extends AppCompatActivity {

    Button login,register;
    TextView about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_settings);

        login = (Button)findViewById(R.id.buttonLogin);
        register = (Button)findViewById(R.id.buttonRegister);
        about = (TextView)findViewById(R.id.tvAbout);

        SharedPreferences spref = this.getSharedPreferences("file5", Context.MODE_PRIVATE);
// then you use
        String apiKey = spref.getString("AppApiKey", null);
        String userID = spref.getString("UserID", null);
        Log.i("ShopItems", "Api " + apiKey + " User " + userID);

        if (apiKey != null && userID != null) {
            Intent alreadyloggedin = new Intent(this, WelcomeActivity.class);
            startActivity(alreadyloggedin);
        }
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

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAbout();
            }
        });

    }

    private void showAbout() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.walgreens.com"));
        Intent browserChooserIntent = Intent.createChooser(browserIntent , "Choose browser of your choice");
        startActivity(browserChooserIntent);
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
        backbuttonpressed.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Will clear out your activity history stack till now
        startActivity(backbuttonpressed);
    }

}
