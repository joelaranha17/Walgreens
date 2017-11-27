package com.firstapp.joel.walgreens.util.one;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firstapp.joel.walgreens.R;

public class WelcomeActivity extends AppCompatActivity {

    TextView welcome;
    Button btn1,btn2;
    String number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        welcome = (TextView)findViewById(R.id.welcomeUsername);
        btn1 = (Button)findViewById(R.id.buttonHome);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backbuttonpressed = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(backbuttonpressed);
            }
        });



    }
}
