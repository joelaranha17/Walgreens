package com.firstapp.joel.walgreens.util.one;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firstapp.joel.walgreens.R;
import com.firstapp.joel.walgreens.util.login.LoginActivity;

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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.shopitems, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.logout:
                LogoutUser();
                return true;
            case R.id.home:
                Intent intenthome = new Intent(this, MainActivity.class);
                this.startActivity(intenthome);
        }
        return super.onOptionsItemSelected(item);
    }
    private void LogoutUser() {
        SharedPreferences sharedpreferences = getSharedPreferences("file5", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.commit();
        Intent intent1 = new Intent(this, LoginActivity.class);
        this.startActivity(intent1);
    }
}
