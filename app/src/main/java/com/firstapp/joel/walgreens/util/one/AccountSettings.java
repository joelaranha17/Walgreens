package com.firstapp.joel.walgreens.util.one;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
        startActivity(browserChooserIntent );
    }

    private void  loginMethod() {
        Intent gotologinpage = new Intent(this, LoginActivity.class);
        startActivity(gotologinpage);
    }

    private void registerMethod() {
        Intent gotoregisterpage = new Intent(this, Register.class);
        startActivity(gotoregisterpage);

    }
}
