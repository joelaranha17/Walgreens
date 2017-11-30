package com.firstapp.joel.walgreens.util.products;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firstapp.joel.walgreens.R;
import com.firstapp.joel.walgreens.util.checkout.MyCart;
import com.firstapp.joel.walgreens.util.db.SQL;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.firstapp.joel.walgreens.util.db.SQL.NAME;
import static com.firstapp.joel.walgreens.util.db.SQL.PRICE;
import static com.firstapp.joel.walgreens.util.db.SQL.QUANTITY;
import static com.firstapp.joel.walgreens.util.db.SQL.TABLE3;

//import static com.firstapp.joel.walgreens.util.db.SQL.KEY_ID;

public class Product extends AppCompatActivity {
    Context context;
    int Quantity = 1;
    int PriceFinal;
    TextView cartquantity;
    private SharedPreferences sharedPreferences;
    String Product_name, Product_id, Product_desc, Product_quantity, Product_price, Product_image;
    private SQL SqlHelper;
    private SQLiteDatabase db;
    String url ="";
    String username,A, apiKey, userID;
    ArrayList<String> list = new ArrayList<String>();

//---------------------------------------------------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
//----------------------------------------------------------------------------------------------------------------------------
        SharedPreferences spref1 = this.getSharedPreferences("file1", Context.MODE_PRIVATE);
        username = spref1.getString("Username", null);
        String password = spref1.getString("Password", null);
        //----------------------Second SP-------------------------------------------------------
        SharedPreferences spref = this.getSharedPreferences("file5", Context.MODE_PRIVATE);
        apiKey = spref.getString("AppApiKey", null);
        userID = spref.getString("UserID", null);
        Log.i("ShopItems", "Api " + apiKey + " User " + userID);

//-----------------------------------------------------------------------------------------------------------------------------
        SqlHelper = new SQL(this);
        db = SqlHelper.getWritableDatabase();
//-----------------------------------------------------------------------------------------------------------------------------

        TextView name = (TextView) findViewById(R.id.product_name);
        TextView description = (TextView) findViewById(R.id.product_discription);
        TextView id = (TextView) findViewById(R.id.product_id);
        TextView quantity = (TextView) findViewById(R.id.product_quantity);
        TextView price = (TextView) findViewById(R.id.product_prize);
        ImageView image = (ImageView) findViewById(R.id.product_image);
        Button add = (Button) findViewById(R.id.addCart);
        Button plus = (Button) findViewById(R.id.Plus);
        Button minus = (Button) findViewById(R.id.Minus);
        Button buy = (Button) findViewById(R.id.Buy);
        cartquantity = (TextView) findViewById(R.id.tvQuantity);

//-------------------------------------------------------------------------------------------------------------------------------
        Product_name = super.getIntent().getExtras().getString("Name");
        name.setText("" + Product_name);
        Product_id = super.getIntent().getExtras().getString("ID");
        id.setText("Product ID: " + Product_id);
        Product_desc = super.getIntent().getExtras().getString("Description");
        description.setText("" + Product_desc);
        Product_quantity = super.getIntent().getExtras().getString("Quantity");
        quantity.setText("Available Quantity : " + Product_quantity);
        Product_price = super.getIntent().getExtras().getString("Prize");
        price.setText("$" + Product_price);
        Product_image = super.getIntent().getExtras().getString("Image");
        Picasso.with(context)
                .load(Product_image)
                .into(image);

//---------------------------------------------------------------------------------------------------------------------------
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Quantity += 1;
                cartquantity.setText(String.valueOf(Quantity));
            }
        });
//---------------------------------------------------------------------------------------------------------------------------
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Quantity >= 2) {
                    Quantity -= 1;
                    cartquantity.setText(String.valueOf(Quantity));
                } else {
                    Toast.makeText(Product.this, "Minimum Quantity should be 1", Toast.LENGTH_LONG).show();
                }
            }
        });
//----------------------------------------------------------------------------------------------------------------------------
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PriceFinal = Integer.valueOf(Product_price);
                PriceFinal = PriceFinal * Quantity;
                Log.i("Phatka", "Final" + PriceFinal);
                ContentValues cv = new ContentValues();
                cv.put(NAME, Product_name);
  //              cv.put(KEY_ID, Product_id);
                cv.put(PRICE, PriceFinal);
                cv.put(QUANTITY, Quantity);
                //cv.put(IMAGE, Product_image);
                db.insert(TABLE3, null, cv);
                Log.i("Phatka", "DB" + TABLE3);

            /*Intent c = new Intent(context,AbstractAccountAuthenticator.class);
            c.putExtra("ID",""+i);
            c.putExtra("Name",""+n);
            c.putExtra("Quantity",""+Quantity);
            c.putExtra("Prize",""+PriceFinal);
          //  i.putExtra("Description",""+d );
            c.putExtra("Image",""+i);
            view.getContext().startActivity(c);*/
            }
        });

//------------------------------------------------------------------------------------------------------------------------------
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotocart = new Intent(Product.this, MyCart.class);
                startActivity(gotocart);

/*

                Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE, null);
                cursor.moveToFirst();

                if (cursor.getCount() > 0) {

                    String Name = (cursor.getString(cursor.getColumnIndex(NAME)));

                        String Product_ID = cursor.getString(cursor.getColumnIndex(KEY_ID));
                        String Product_Name = cursor.getString(cursor.getColumnIndex(NAME));
                        String Product_QTY = cursor.getString(cursor.getColumnIndex(QUANTITY));
                        String Product_Price = cursor.getString(cursor.getColumnIndex(PRICE));
                        String Product_Image = cursor.getString(cursor.getColumnIndex(IMAGE));

                        CartList myCart = new CartList(Product_ID, Product_Name, Product_QTY, Product_Price, Product_Image);
*/

//                dothis();
            }
        });
    }
/*

    private void dothis(){
        url = "http://rjtmobile.com/ansari/shopingcart/androidapp/orders.php?" +
                "&item_id=" + i + "&item_names=" + n + "&item_quantity=" + QUANTITY
                + "&final_price=" + PriceFinal + "&mobile=" + username + "&api_key=" + apiKey +
                "&user_id=" + userID;
        Log.d("place_order", "Result" + url);
        StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response) {

                Log.d("place_order","Result:"+response);
                if(response.contains("Order Confirmed")){
                    Toast.makeText(Product.this,"Your order is confirmed",Toast.LENGTH_LONG).show();
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
*/

}