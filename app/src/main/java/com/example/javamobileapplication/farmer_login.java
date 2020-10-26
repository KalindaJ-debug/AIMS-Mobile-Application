package com.example.javamobileapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Classes.JSONParser;
import Classes.Users;
import Database.DBHandler;
import Database.UserHandler;

public class farmer_login extends AppCompatActivity {

    private Button login;
    private ProgressDialog pDialog;

    String username;

    UserHandler userHandler;
    //json names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    private static final String TAG_ID = "id";

    //json object
    JSONObject json = new JSONObject();

    //make connection with remote database
    JSONParser jParser = new JSONParser();
    private static String url_check_login = "http://192.168.1.8:8000/getFarmers";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_login);

        //dbHandler = new DBHandler(this);//handler for the Database
        userHandler = new UserHandler(this);//handler for users


        //check if the button is clicked
        login = findViewById(R.id.nic_submit);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText edit1 = findViewById(R.id.NIC);//get NIC from the farmer
                username = edit1.getText().toString();

                if(TextUtils.isEmpty(username)){
                    Toast.makeText(farmer_login.this, "You must enter a NIC to proceed", Toast.LENGTH_SHORT).show();
                }else{

                    new CheckAllusers().execute();
                }
            }
        });

    }

    boolean userCheck(String text){
        List<Users> farmers = userHandler.readAllUsers();
        for(Users user : farmers){
            Log.d("STATE",user.getUsername());
            if (user.getPassword().equals(text)) {
                return true;
            }

        }
        return false;
    }

    String getName(String text){
        String name = "";
        List<Users> farmers = userHandler.readAllUsers();
        for(Users user : farmers){
            if(user.getUsername().equals(text)){
                name = user.getUsername();
            }
        }

        return name;
    }

    class CheckAllusers extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(farmer_login.this);
            pDialog.setMessage("Logging In. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
            pDialog.dismiss();
        }


        @Override
        protected String doInBackground(String... strings) {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("username", username));

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
                    Toast.makeText(farmer_login.this, message , Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), Officer_logged.class);
                    i.putExtra("id", json.getString(TAG_ID));

                    startActivity(i);
                    // closing this screen
                    finish();
                } else {
                    Toast.makeText(farmer_login.this, message , Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            pDialog.dismiss();
        }
    }

}