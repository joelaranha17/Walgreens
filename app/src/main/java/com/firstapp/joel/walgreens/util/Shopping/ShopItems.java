package com.firstapp.joel.walgreens.util.Shopping;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.firstapp.joel.walgreens.R;
import com.firstapp.joel.walgreens.util.adapters.ProductsAdapter;
import com.firstapp.joel.walgreens.util.model.ProductsList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ShopItems extends AppCompatActivity {
    private final String TAG = ShopItems.class.getSimpleName();
    private static final String tmpurl = "http://rjtmobile.com/ansari/shopingcart/androidapp/cust_product.php?Id=";
    private String url;

    private RecyclerView productsRecyclerView;
    private ProductsAdapter productsAdapter;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<ProductsList> productsArrayList;
    private ProductsList products;
    ProgressDialog progressDialog;

    private int currentId;
    private int categoryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopitems);

        productsRecyclerView = (RecyclerView) findViewById(R.id.productRecyclertListView);
        productsRecyclerView.setHasFixedSize(true);
        productsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        productsArrayList = new ArrayList<>();

        /*for (int i=0;i<=10;i++){
            listItem = new ListItem(  "HEADING" + i,"my description");
            mylisteItems.add(listItem);     }
        adapter= new MyAdapter(mylisteItems,this);
        recyclerView.setAdapter(adapter);*/
            mylarRecyclerView();
        }
    private void mylarRecyclerView() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("loading data");
        progressDialog.show();

        Log.i("MYTEST", "yes");

        StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {

                Log.i("MYTEST", response);

                progressDialog.cancel();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray categories = jsonObject.getJSONArray("actors");
                    for (int i = 0; i < categories.length(); i++) {
                        JSONObject item = categories.getJSONObject(i);
                        ProductsList ls = new ProductsList(item.getString("ID"),
                                item.getString("ProductName"),
                                item.getString("Quantity"),
                                item.getString("Price"),
                                item.getString("Description"),
                                item.getString("Image"));
                        productsArrayList.add(ls);
                    }

                    adapter = new ProductsAdapter(productsArrayList, getApplicationContext());
                    productsRecyclerView.setAdapter(adapter);
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
}