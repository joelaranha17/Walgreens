package com.firstapp.joel.walgreens;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    ListView listview;
    String[] items = {"Prescriptions & Health", "Shop Products", "Photo", "Weekly Ad & Coupons"};

    int[] imageIds = {R.drawable.plus,R.drawable.bag,R.drawable.camera,R.drawable.ads,};



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        listview = (ListView)this.findViewById(R.id.myList);


        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


       /* ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (MainActivity.this,R.layout.item_layout,R.id.textViewItem,countries);*/

        MyAdapter myAdapter = new MyAdapter(items, imageIds,this);
        listview.setAdapter(myAdapter);                                                //setting up listener

    }
}
