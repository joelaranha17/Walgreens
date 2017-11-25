package com.firstapp.joel.walgreens.util.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.firstapp.joel.walgreens.R;
import com.firstapp.joel.walgreens.util.net.AppController;

public class Register extends AppCompatActivity {

    EditText fname;
    EditText remail;
    EditText rphone;
    EditText pword;
    Button loginbtn;
    Switch remuser;
    Switch rempass;
//-------------------------------------------------------------------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        fname = (EditText) findViewById(R.id.registerPageNameEditText);
        remail = (EditText) findViewById(R.id.registerPageLNameEditText);
        pword = (EditText) findViewById(R.id.registerPagePasswordEditText);
        rphone = (EditText) findViewById(R.id.registerPagePhEditText);
        remuser = (Switch) findViewById(R.id.registerPageSaveUser);
        rempass = (Switch) findViewById(R.id.registerPageSavePass);
        loginbtn = (Button) findViewById(R.id.buttonRegisterPageContinue);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerContinue();
            }
        });
    }
//--------------------------------------------------------------------------------------------------------------------------------------------------------
    private void registerContinue() {
        String input_name=fname.getText().toString();
        String input_email=remail.getText().toString();
        String input_password=pword.getText().toString();
        String input_phone=rphone.getText().toString();

//---------------------------------------------------------------------------------------------------------------------------------------------------------
        /*
         * Validating the registration credentials
         */

        if(!(input_name.equals(""))&& !(input_email.equals(""))&&!(input_name.equals(""))&&!(input_email.equals("")))
        {
            if(input_email.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+"))
            {
                if(input_phone.length() == 10)
                {
                    if(input_password.length()>=6)
                    {
                        String url = "http://rjtmobile.com/ansari/shopingcart/androidapp/shop_reg.php?" +
                                "name=" + input_name + "&email=" + input_email + "&mobile=" + input_phone + "&password=" + input_password;
                        registerUser(url);
                    }
                    else
                    {
                        Toast.makeText(Register.this, "Password Should be 6 or more Characters", Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(Register.this,"Mobile Should be 10 digit Number" , Toast.LENGTH_LONG).show();
                }
            }
            else
            {
                Toast.makeText(Register.this,"Please Enter Valid Email" , Toast.LENGTH_LONG).show();
            }

        }

        else if(input_name.equals("")|| input_email.equals("")||input_phone.equals("")||input_password.equals(""))
        {
            Toast.makeText(Register.this,"Please Enter Empty Fields",Toast.LENGTH_LONG).show();

        }

                /*
                 * After inserting data in database clear all textboxes
                 */

        fname.setText("");
        remail.setText("");
        rphone.setText("");
        pword.setText("");
//--------------------------------------------------------------------------------------------------------------------------------------------------------
    }
    private void registerUser(String url) {
        String  tag_string_req = "string_req";
        StringRequest stringRequest = new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("Result Response:",response);

                if(response.contains("successfully registered")){

                    Toast.makeText(Register.this,response+"You can Login Now",Toast.LENGTH_LONG).show();
                    Intent back_to_login = new Intent(Register.this,LoginActivity.class);
                    startActivity(back_to_login);

                }
                else{
                    Toast.makeText(Register.this,response,Toast.LENGTH_LONG).show();
                }

            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(stringRequest, tag_string_req);

    }
}
