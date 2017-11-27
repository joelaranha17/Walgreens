package com.firstapp.joel.walgreens.util.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.firstapp.joel.walgreens.R;
import com.firstapp.joel.walgreens.util.Shopping.ShopItems;
import com.firstapp.joel.walgreens.util.model.LoginDetails;
import com.firstapp.joel.walgreens.util.one.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();
    EditText uname;
    EditText pword;
    Button loginbtn;
    Switch remuser;
    Switch rempass;
    TextView forgot_user, forgot_pass, register;
    private SharedPreferences spref;
    private ArrayList<LoginDetails> loginDetailsArrayList;
    private LoginDetails login;
    private String url="";
    private String mydata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences prefs = this.getSharedPreferences("file5", Context.MODE_PRIVATE);
// then you use
        String apiKey = prefs.getString("AppApiKey", null);
        String userID = prefs.getString("UserID",null);
        Log.i("ShopItems","Api " +apiKey +" User "+userID);

        if(apiKey!=null && userID != null){
            Intent category_intent = new Intent(LoginActivity.this, ShopItems.class);
            startActivity(category_intent);
        }
//---------------------------------------------------------------------------------------------------------------------------------------------------------
        /*
         * initializing ids for all view elements
         * EditText,TextView and Buttons
         */
        uname = (EditText) findViewById(R.id.loginUsernameText);
        pword = (EditText) findViewById(R.id.loginPasswordText);
        remuser = (Switch) findViewById(R.id.switchSaveUname);
        rempass = (Switch) findViewById(R.id.switchSavePword);
        loginbtn = (Button) findViewById(R.id.logginButton);
        forgot_user = (TextView) findViewById(R.id.forgotUnameTextView);
        forgot_pass = (TextView) findViewById(R.id.forgotPWordTextView);
        forgot_pass = (TextView) findViewById(R.id.forgotPWordTextView);
        register = (TextView) findViewById(R.id.registerTextView);
        spref = getSharedPreferences("file5", Context.MODE_PRIVATE);

//--------------------------------------------------------------------------------------------------------------------------------------------------------
        /*
         * Setting up listeners for the activity
         */
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String input_username = uname.getText().toString();
                String input_password = pword.getText().toString();
                if (input_username.equals("") || input_password.equals("")) {
                    Toast.makeText(LoginActivity.this, "Please Enter the Empty Field", Toast.LENGTH_SHORT).show();
                } else if (!input_username.isEmpty() && !input_password.isEmpty()) {
                    if (input_username.length() == 10) {
                        if (input_password.length() >= 3) {
                            if(remuser.isChecked())
                            {
                                SharedPreferences.Editor editPef = spref.edit();
                                editPef.putString("input_phone", input_username);
                                editPef.apply();

                            }
                            if(rempass.isChecked()){
                                SharedPreferences.Editor editPef = spref.edit();
                                editPef.putString("input_phone", input_password);
                                editPef.apply();
                            }
                            url = "http://rjtmobile.com/ansari/shopingcart/androidapp/shop_login.php?" +
                                    "mobile=" + input_username + "&password=" + input_password;

                            // JsonParser();
                            SignIn(url);
                        } else {
                            Toast.makeText(LoginActivity.this, "Password Should Be More Than 8 Characters", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "Username Should Be 10 Characters", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Input Fields cannot Be Empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
//--------------------------------------------------------------------------------------------------------------------------------------------------------
        forgot_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*ForgotUsernameFragment mFragment = null;
                mFragment = new ForgotUsernameFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, mFragment).commit();*/
                Intent forgot_user = new Intent(LoginActivity.this, ForgotUsername.class);
                startActivity(forgot_user);
            }
        });
//---------------------------------------------------------------------------------------------------------------------------------------------------------
        forgot_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent forgot_password = new Intent(LoginActivity.this, ForgotPassword.class);
                startActivity(forgot_password);
            }
        });
//---------------------------------------------------------------------------------------------------------------------------------------------------------
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register = new Intent(LoginActivity.this, Register.class);
                startActivity(register);
            }
        });
        //--------------------------------------------------------------------------------------------------------------------------------------------------


    }

    private void SignIn(String url)
    {
        String tag_string_req_home = "string_req";

     /*   final StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG,"Result:"+response);

                StringBuffer buffer = new StringBuffer();
                String line = "";

                for (int i=0;i<=response.length();i++){
                    buffer.append(line+"\n");
                    Log.d("Response: ", "> " + buffer.toString());
                }

                mydata =  buffer.toString();

                Log.i("JOEL","myData "+mydata);

                if(response.contains("success")){
                    Toast.makeText(LoginActivity.this,"Successfully Logged in",Toast.LENGTH_LONG).show();
                    Intent category_intent = new Intent(LoginActivity.this, ShopItems.class);

                    startActivity(category_intent);
                } else if(response.contains("Mobile Number not register"))
                {
                    Toast.makeText(LoginActivity.this,"Mobile Number not register",Toast.LENGTH_LONG).show();
                }
                else if(response.contains("incorrect password"))
                {
                    Toast.makeText(LoginActivity.this,"incorrect password",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(stringRequest, tag_string_req_home);*/

        new JSONPARSER().execute();
    }

    private class JSONPARSER extends AsyncTask<String,String,String> {
        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onPostExecute(String s) {

            Log.i("JOEL","String S "+mydata);
            try {
                JSONArray jArray = new JSONArray(mydata);
                for(int i=0; i < jArray.length(); i++) {
                    // HashMap<String,String> joel = new HashMap<String,String>();

                    JSONObject jsonObject = jArray.getJSONObject(i);
                    String appapikey = jsonObject.getString("AppApiKey ");
                    String msg = jsonObject.getString("msg");
                    String UserID = jsonObject.getString("UserID");
                    SharedPreferences.Editor editor = spref.edit();
                    editor.putString("AppApiKey",appapikey);
                    editor.putString("UserID",UserID);
                    editor.commit();

                    if(mydata.contains("success")){
                        Toast.makeText(LoginActivity.this,"Successfully Logged in",Toast.LENGTH_LONG).show();
                        Intent category_intent = new Intent(LoginActivity.this, ShopItems.class);
                        startActivity(category_intent);
                    }
                    else if(mydata.contains("Mobile Number not register"))
                    {
                        Toast.makeText(LoginActivity.this,"Mobile Number not register",Toast.LENGTH_LONG).show();
                    }
                    else if(mydata.contains("incorrect password"))
                    {
                        Toast.makeText(LoginActivity.this,"incorrect password",Toast.LENGTH_LONG).show();
                    }



                }

            } catch (JSONException e) {
                Log.e("JSONException", "Error: " + e.toString());
            }

            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(String... strings) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL urlnew = new URL(url);
                connection = (HttpURLConnection) urlnew.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line+"\n");
                    Log.d("Response: ", "> " + line);
                }

                mydata =  buffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent backbuttonpressed = new Intent(this, MainActivity.class);
        startActivity(backbuttonpressed);
    }
}
