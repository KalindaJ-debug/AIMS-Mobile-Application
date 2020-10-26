package com.example.javamobileapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

import Classes.JSONParser;
import Classes.Users;
import Database.UserHandler;

public class officer_login extends AppCompatActivity {

    private Button login;
    private ProgressDialog pDialog;
    UserHandler userHandler;
    EditText username;
    EditText password;

    String u_name;
    String p_word;

    //json names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    //json object
    JSONObject json = new JSONObject();

    //make connection with remote database
    JSONParser jParser = new JSONParser();
    private static String url_check_login = "http://192.168.1.8:8000/getRegisteredUsers";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_officer_login);

        userHandler = new UserHandler(this);

        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = findViewById(R.id.username);//get username from officer
                password = findViewById(R.id.password);//get password from officer
                u_name = username.getText().toString();
                p_word = password.getText().toString();

                Log.d("State", u_name );
                Log.d("State1", p_word );
                if(TextUtils.isEmpty(u_name)||TextUtils.isEmpty(p_word)){
                    Toast.makeText(officer_login.this, "You must enter both username and password to proceed", Toast.LENGTH_SHORT).show();
                }else {
                    new CheckAllusers().execute();
                }
            }
        });
    }

    class CheckAllusers extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(officer_login.this);
            pDialog.setMessage("Logging In. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
            pDialog.dismiss();
        }


        @Override
        protected String doInBackground(String... strings) {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("username", u_name));
            params.add(new BasicNameValuePair("password", p_word));

            json = jParser.makeHttpRequest(url_check_login,
                    "POST", params);

            return null;
        }

        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            try {
                int success = json.getInt(TAG_SUCCESS);
                String message = json.getString(TAG_MESSAGE);
                if (success == 1) {
                    // successfully created product
                    Toast.makeText(officer_login.this, message , Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), Officer_logged.class);
                    i.putExtra("UserName", username.toString());

                    startActivity(i);
                    // closing this screen
                    finish();
                } else {
                    Toast.makeText(officer_login.this, message , Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            pDialog.dismiss();
        }
    }
}