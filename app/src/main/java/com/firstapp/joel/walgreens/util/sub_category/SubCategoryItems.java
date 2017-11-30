package com.firstapp.joel.walgreens.util.sub_category;

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
import com.firstapp.joel.walgreens.util.Shopping.ShopItems;
import com.firstapp.joel.walgreens.util.model.SubCategoryList;
import com.firstapp.joel.walgreens.util.one.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SubCategoryItems extends AppCompatActivity {
    private final String TAG = SubCategoryItems.class.getSimpleName();
    private String tmpurl =""; //"http://rjtmobile.com/ansari/shopingcart/androidapp/cust_product.php?Id=";

    private RecyclerView subCategoryRecyclerView;
    private SubCategoryAdapter subCategoryAdapter;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<SubCategoryList> subCategoryListArrayList;
    private ArrayList<String> idLists;
    private SubCategoryList subCategoryList;
    ProgressDialog progressDialog;

    private int currentId;
    private int categoryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcategoryitems);

        SharedPreferences spref = this.getSharedPreferences("file5", Context.MODE_PRIVATE);
        String apiKey = spref.getString("AppApiKey", null);
        String userID = spref.getString("UserID",null);
        Log.i("ShopItems","Api " +apiKey +" User "+userID);

        Intent gotosub = getIntent();//gotosub.getExtras();
        Bundle b = gotosub.getExtras();
        String s = b.getString("CategoryID");
        Log.i("CATEGORY ID","JOEL"+s);

        tmpurl = "http://rjtmobile.com/ansari/shopingcart/androidapp/cust_sub_category.php?Id=" +s +
                "&api_key=" + apiKey + "&user_id=" + userID;

        subCategoryRecyclerView = (RecyclerView) findViewById(R.id.subCategoryRecyclertListView);
        subCategoryRecyclerView.setHasFixedSize(true);
        subCategoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        subCategoryListArrayList = new ArrayList<>();

        /*for (int i=0;i<=10;i++){
            listItem = new ListItem(  "HEADING" + i,"my description");
            mylisteItems.add(listItem);     }
        adapter= new MyAdapter(mylisteItems,this);
        recyclerView.setAdapter(adapter);*/
        mysubRecyclerView();
    }

    private void mysubRecyclerView() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("loading data");
        progressDialog.show();

        Log.i("MYTEST", "yes");

        StringRequest sr = new StringRequest(Request.Method.POST, tmpurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("MYTEST", response);

                progressDialog.cancel();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray subCategory = jsonObject.getJSONArray("SubCategory");
                    for (int i = 0; i < subCategory.length(); i++) {
                        JSONObject item = subCategory.getJSONObject(i);
                        SubCategoryList ls = new SubCategoryList(
                                item.getString("Id"),
                                item.getString("SubCatagoryName"),
                                item.getString("SubCatagoryDiscription"),
                                item.getString("CatagoryImage"));
                        subCategoryListArrayList.add(ls);

                    }
                    Log.i("Sub","S" +subCategoryListArrayList);
                    adapter = new SubCategoryAdapter(subCategoryListArrayList, getApplicationContext());
                    subCategoryRecyclerView.setAdapter(adapter);
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
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void LogoutUser() {
        SharedPreferences spref = getSharedPreferences("file5", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = spref.edit();
        editor.clear();
        editor.commit();
        Intent items = new Intent(this, ShopItems.class);
        //backbuttonpressed.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Will clear out your activity history stack till now
        this.startActivity(items);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent backbuttonpressed = new Intent(this, ShopItems.class);
        startActivity(backbuttonpressed);
        finish();
    }
}