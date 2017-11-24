package com.firstapp.joel.walgreens.util.Login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import com.firstapp.joel.walgreens.R;

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
                        register_user(url);
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

    private void register_user(String url) {
    }
}
