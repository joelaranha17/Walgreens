package com.firstapp.joel.walgreens.util.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.firstapp.joel.walgreens.R;
import com.firstapp.joel.walgreens.util.Shopping.ShopItems;
import com.firstapp.joel.walgreens.util.net.AppController;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG =LoginActivity.class.getSimpleName(); ;
    EditText uname;
    EditText pword;
    Button loginbtn;
    Switch remuser;
    Switch rempass;
    TextView forgot_user, forgot_pass, register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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
                            String url = "http://rjtmobile.com/ansari/shopingcart/androidapp/shop_login.php?" +
                                    "mobile=" + input_username + "&password=" + input_password;

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

        final StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG,"Result:"+response);
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
        AppController.getInstance().addToRequestQueue(stringRequest, tag_string_req_home);
    }
}
