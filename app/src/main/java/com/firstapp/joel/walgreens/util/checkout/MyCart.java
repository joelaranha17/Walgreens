package com.firstapp.joel.walgreens.util.checkout;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.firstapp.joel.walgreens.R;
import com.firstapp.joel.walgreens.util.db.SQL;
import com.firstapp.joel.walgreens.util.model.CartList;
import com.firstapp.joel.walgreens.util.one.MainActivity;

import java.util.ArrayList;

import static com.firstapp.joel.walgreens.util.db.SQL.NAME;
import static com.firstapp.joel.walgreens.util.db.SQL.PRICE;
import static com.firstapp.joel.walgreens.util.db.SQL.QUANTITY;
import static com.firstapp.joel.walgreens.util.db.SQL.TABLE3;

//import static com.firstapp.joel.walgreens.util.db.SQL.KEY_ID;

public class MyCart extends AppCompatActivity {
    private RecyclerView mycartRecyclerView;
    private MyCartAdapter myCartAdapter;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<CartList> cartListArrayList;
    private SQL SqlHelper;
    private SQLiteDatabase db;
    private CartList cartList;
    ProgressDialog progressDialog;

    //--------------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);
        SqlHelper = new SQL(this);
        db = SqlHelper.getReadableDatabase();

//--------------------------------------------------------------------------------------------------
        SharedPreferences spref = this.getSharedPreferences("file5", Context.MODE_PRIVATE);
        String apiKey = spref.getString("AppApiKey", null);
        String userID = spref.getString("UserID", null);
//        Log.i("ShopItems", "Api " + apiKey + " User " + userID);

//--------------------------------------------------------------------------------------------------
        mycartRecyclerView = (RecyclerView) findViewById(R.id.mycartRecyclerView);
        mycartRecyclerView.setHasFixedSize(false);
        mycartRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        cartListArrayList = new ArrayList<>();
        mycRecyclerView();
    }

//--------------------------------------------------------------------------------------------------
    private void mycRecyclerView() {

        Cursor cursor = db.rawQuery
                ("select * from " + TABLE3, null);
        cursor.moveToFirst();


        do {
            CartList cl = new CartList(
  //          cursor.getString(cursor.getColumnIndex(KEY_ID)),
            cursor.getString(cursor.getColumnIndex(NAME)),
            cursor.getString(cursor.getColumnIndex(QUANTITY)),
            cursor.getString(cursor.getColumnIndex(PRICE)));
           // cursor.getString(cursor.getColumnIndex(IMAGE)));
            cartListArrayList.add(cl);
        }
        while (cursor.moveToNext());
        Log.i("CARTDETAILS","CHECK" +cartListArrayList);
        adapter = new MyCartAdapter(cartListArrayList, getApplicationContext());
        mycartRecyclerView.setAdapter(adapter);

    }
//--------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------
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
                    finish();
            }
            return super.onOptionsItemSelected(item);
        }

    private void LogoutUser() {
        SharedPreferences spref = getSharedPreferences("file5", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = spref.edit();
        editor.clear();
        editor.commit();
        Intent items1 = new Intent(this, MainActivity.class);
        //backbuttonpressed.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Will clear out your activity history stack till now
        this.startActivity(items1);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
       Intent backbuttonpressed = new Intent(this, MainActivity.class);
        startActivity(backbuttonpressed);
        finish();
    }
}
