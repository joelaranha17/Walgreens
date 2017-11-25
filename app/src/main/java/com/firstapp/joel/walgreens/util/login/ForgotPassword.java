package com.firstapp.joel.walgreens.util.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.firstapp.joel.walgreens.R;
import com.firstapp.joel.walgreens.util.net.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ForgotPassword extends AppCompatActivity {

    EditText username;
    Button btnContinue;
    TextView forgotUser;
    private String global_phone_value;
    private String jsonResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        username    = (EditText)findViewById(R.id.resetPassUserText);
        btnContinue = (Button)findViewById(R.id.buttonResetPasswordContinue);
        forgotUser  = (TextView)findViewById(R.id.forgotUsernameTV);

/*
            forgot_pass_phone = (EditText)findViewById(R.id.editText_userphone);
            submit_button_forgot = (Button)findViewById(R.id.button_submit_phone);*/

            btnContinue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String registered_phone = username.getText().toString().trim();
                    global_phone_value = registered_phone;

                    if((!registered_phone.isEmpty())&&(registered_phone.length()==10)){
                        String forgot_pass_url = "http://rjtmobile.com/ansari/shopingcart/androidapp/shop_fogot_pass.php?"+
                                "&mobile="+registered_phone;
                        submitPhoneNumber(forgot_pass_url);
                    }
                    else
                    {
                        Toast.makeText(ForgotPassword.this, "Please Enter Valid Mobile of 10 digit", Toast.LENGTH_LONG).show();
                    }


                }
            });
        }

    private void submitPhoneNumber(String url){
        String  tag_string_req = "string_req_forgot";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url,new Response.Listener<JSONArray>(){
            @Override
            public void onResponse(JSONArray response) {
                try{
                    jsonResponse="";
                    for (int i = 0; i < response.length(); i++) {

                        JSONObject result = response.getJSONObject(i);
                        String UserPassword = result.getString("UserPassword");
                        jsonResponse+= UserPassword;

                    }
                    Toast.makeText(ForgotPassword.this,"Your Password is: "+jsonResponse,Toast.LENGTH_LONG).show();
                }catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonArrayRequest, tag_string_req);
    }
}
