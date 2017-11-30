package com.firstapp.joel.walgreens.util.login;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firstapp.joel.walgreens.R;
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

public class ResetPassword extends AppCompatActivity {

    EditText unamer, pwordr, newpword;
    Button cont, canc;
    private String msg;
    private String url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        unamer = (EditText) findViewById(R.id.phNumb);
        pwordr = (EditText) findViewById(R.id.oldPass);
        newpword = (EditText) findViewById(R.id.newPass);
        cont = (Button) findViewById(R.id.buttonConfirm);
        canc = (Button) findViewById(R.id.buttonCancel);

        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 String input_username = unamer.getText().toString();
                 String input_password = pwordr.getText().toString();
                 String input_newpassword=newpword.getText().toString();
                if (input_username.equals("") || input_password.equals("")) {
                    Toast.makeText(ResetPassword.this, "Please Enter the Empty Field", Toast.LENGTH_SHORT).show();
                } else if (!input_username.isEmpty() && !input_password.isEmpty()) {
                    if (input_username.length() == 10) {
                        if (input_password.length() >= 7) {
                           /* if (remuser.isChecked()) {
                                SharedPreferences.Editor editPef = spref.edit();
                                editPef.putString("input_phone", input_username);
                                editPef.commit();*/

                            /*}
                            if (rempass.isChecked()) {
                                SharedPreferences.Editor editPef = spref.edit();
                                editPef.putString("input_phone", input_password);
                                editPef.commit();
                            }*/
                             url ="http://rjtmobile.com/ansari/shopingcart/androidapp/shop_reset_pass.php?" +
                                     "&mobile=" + input_username + "&password=" + input_password + "&newpassword=" + input_newpassword;
                            SignIn(url);
                        } else {
                            Toast.makeText(ResetPassword.this, "Password Should Be More Than 8 Characters", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(ResetPassword.this, "Username Should Be 10 Characters", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ResetPassword.this, "Input Fields cannot Be Empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
    canc.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent goback = new Intent(ResetPassword.this,LoginActivity.class);
            startActivity(goback);
            finish();
        }
    });
    }


    private void SignIn(String url) {
        String tag_string_req_home = "string_req";
        new ResetPassword.JSONPARSER().execute();
    }

    private class JSONPARSER extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
        }
        @Override
        protected void onPostExecute(String s) {

            Log.i("JOEL", "String S " + msg);
            try {
                JSONObject jsonObject = new JSONObject();

                JSONArray jArray = new JSONArray(msg);
                for (int i = 0; i < jArray.length(); i++) {
                    // HashMap<String,String> joel = new HashMap<String,String>();

                   /* jsonObject = jArray.getJSONObject(i);
                    *//*String appapikey = jsonObject.getString("AppApiKey ");*/
                    String msg = jsonObject.getString("msg");
                    /*String UserID = jsonObject.getString("UserID");
                    SharedPreferences.Editor editor = spref.edit();
                    editor.putString("AppApiKey",appapikey);
                    editor.putString("UserID",UserID);
                    editor.commit();*/
                    if (msg.contains("password reset successfully")) {

                        Toast.makeText(ResetPassword.this,"Password Changed", Toast.LENGTH_SHORT).show();
                        Intent goback = new Intent(ResetPassword.this,MainActivity.class);
                        startActivity(goback);
                        finish();

                    } else
                        Toast.makeText(ResetPassword.this, "incorrect password", Toast.LENGTH_LONG).show();
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
                    buffer.append(line + "\n");
                    Log.d("Response: ", "> " + line);
                }

                msg = buffer.toString();

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
