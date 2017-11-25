package com.firstapp.joel.walgreens.util.one;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.firstapp.joel.walgreens.R;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    ListView listview;
    String[] items = {"Prescriptions & Health", "Shop Products", "Photo", "Weekly Ad & Coupons"};

    int[] imageIds = {R.drawable.plus, R.drawable.bag, R.drawable.camera, R.drawable.ads,};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = (ListView) this.findViewById(R.id.myList);


        /*mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
*/

       /* ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (MainActivity.this,R.layout.item_layout,R.id.textViewItem,countries);*/

        MyHomeScreenListAdapter myAdapter = new MyHomeScreenListAdapter(items, imageIds, this);
        listview.setAdapter(myAdapter);                                                //setting up listener
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mytopmenu, menu);
        return true;
    }
        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_settings:
                   /*
                   * Migrating to the account settings activity
                   * */

                    Intent account_settings = new Intent(this, AccountSettings.class);
                    startActivity(account_settings);
                    return true;

                case R.id.action_mailbox:
                    Intent mailbox = new Intent(this, Mailbox.class);
                    startActivity(mailbox);
                    return true;

                default:
                    // If we got here, the user's action was not recognized.
                    // Invoke the superclass to handle it.
                    return super.onOptionsItemSelected(item);

            }
        }

    }
