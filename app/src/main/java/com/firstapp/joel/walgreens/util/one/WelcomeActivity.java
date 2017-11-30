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
import android.widget.Toast;

import com.firstapp.joel.walgreens.R;

public class WelcomeActivity extends AppCompatActivity {

    TextView welcome;
    Button btn1,btn2;
    String number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        SharedPreferences prefs = this.getSharedPreferences("file5", Context.MODE_PRIVATE);
        String apiKey = prefs.getString("AppApiKey", null);
        String userID = prefs.getString("UserID",null);
        String phone = prefs.getString("input_phone",null);
        welcome = (TextView)findViewById(R.id.welcomeUsername);
//        welcome.append(apiKey);
        btn1 = (Button)findViewById(R.id.buttonHome);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backbuttonpressed = new Intent(WelcomeActivity.this, MainActivity.class);
                backbuttonpressed.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Will clear out your activity history stack till now
                startActivity(backbuttonpressed);
                finish();
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
                Intent backbuttonpressed = new Intent(this, MainActivity.class);
                this.startActivity(backbuttonpressed);
        }
        return super.onOptionsItemSelected(item);
    }
    private void LogoutUser() {
        SharedPreferences spref = getSharedPreferences("file5", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = spref.edit();
        editor.clear();
        editor.commit();
        Toast.makeText(WelcomeActivity.this,"Logged Out Successfully",Toast.LENGTH_SHORT).show();
        Intent backbuttonpressed = new Intent(this, MainActivity.class);
        this.startActivity(backbuttonpressed);
    }
}
