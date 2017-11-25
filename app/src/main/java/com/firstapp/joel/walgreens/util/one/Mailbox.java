package com.firstapp.joel.walgreens.util.one;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

        login = (Button) findViewById(R.id.buttonLogin);
        register = (Button) findViewById(R.id.buttonRegister);

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
    private void  loginMethod() {
        Intent gotologinpage = new Intent(this, LoginActivity.class);
        startActivity(gotologinpage);
    }

    private void registerMethod() {
        Intent gotoregisterpage = new Intent(this, Register.class);
        startActivity(gotoregisterpage);

    }
}