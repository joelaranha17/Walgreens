package com.firstapp.joel.walgreens.util.products;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.firstapp.joel.walgreens.R;
import com.firstapp.joel.walgreens.util.login.LoginActivity;
import com.firstapp.joel.walgreens.util.model.ProductsList;
import com.firstapp.joel.walgreens.util.one.MainActivity;
import com.firstapp.joel.walgreens.util.sub_category.SubCategoryItems;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProductItems extends AppCompatActivity {
    // private final String TAG = com.firstapp.joel.walgreens.util.sub_category.SubCategoryItems.class.getSimpleName();
    private String tmpurl = ""; //"http://rjtmobile.com/ansari/shopingcart/androidapp/cust_product.php?Id=";

    private RecyclerView productRecyclerView;
    private ProductAdapter productAdapter;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<ProductsList> productsListsArrayList;
    private ProductsList productsList;
    ProgressDialog progressDialog;

    private int currentId;
    private int categoryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        SharedPreferences prefs = this.getSharedPreferences("file5", Context.MODE_PRIVATE);
        String apiKey = prefs.getString("AppApiKey", null);
        String userID = prefs.getString("UserID", null);
        Log.i("ShopItems", "Api " + apiKey + " User " + userID);

       tmpurl = "http://rjtmobile.com/ansari/shopingcart/androidapp/cust_product.php?Id=205&" +
               "api_key=" + apiKey + "&user_id=" + userID;

        productRecyclerView = (RecyclerView) findViewById(R.id.myRView);
        productRecyclerView.setHasFixedSize(false);
        productRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        productsListsArrayList = new ArrayList<>();

        /*for (int i=0;i<=10;i++){
            listItem = new ListItem(  "HEADING" + i,"my description");
            mylisteItems.add(listItem);     }
        adapter= new MyAdapter(mylisteItems,this);
        recyclerView.setAdapter(adapter);*/
        mylRecyclerView();
    }

    private void mylRecyclerView() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("loading data");
        progressDialog.show();

      //  Log.i("MYTEST", "yes");

        StringRequest sr = new StringRequest(Request.Method.POST, tmpurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("products", response);

                progressDialog.cancel();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray product = jsonObject.getJSONArray("Product");
                    for (int i = 0; i < product.length(); i++) {
                        JSONObject item = product.getJSONObject(i);
                        ProductsList ls = new ProductsList(
                                item.getString("Id"),
                                item.getString("ProductName"),
                                item.getString("Quantity"),
                                item.getString("Prize"),
                                item.getString("Discription"),
                                item.getString("Image"));
                        productsListsArrayList.add(ls);

                    }
                    Log.i("PRODUCTS", "S" + productsListsArrayList);
                    adapter = new ProductAdapter(productsListsArrayList, getApplicationContext());
                    productRecyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(sr);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent backbuttonpressedonce = new Intent(this, SubCategoryItems.class);
        startActivity(backbuttonpressedonce);
    }
}