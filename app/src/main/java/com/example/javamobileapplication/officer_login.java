package com.example.javamobileapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
    UserHandler userHandler;

    //make connection with remote database
    JSONParser jParser = new JSONParser();
    private static String url_get_users = "http://127.0.0.1:8000/getRegisteredUsers";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_officer_login);

        new ImportAllusers().execute();
        userHandler = new UserHandler(this);

        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText username = findViewById(R.id.username);//get username from officer
                EditText password = findViewById(R.id.password);//get password from officer
                String u_name = username.getText().toString();
                String p_word = password.getText().toString();

                Log.d("State", u_name );
                Log.d("State1", p_word );
                if(TextUtils.isEmpty(u_name)||TextUtils.isEmpty(p_word)){
                    Toast.makeText(officer_login.this, "You must enter both username and password to proceed", Toast.LENGTH_SHORT).show();
                }else if(!userCheck(u_name)){
                    Toast.makeText(officer_login.this, "no such user", Toast.LENGTH_SHORT).show();
                    //startActivity(i);
                }else if(passCheck(u_name , p_word)){
                    Toast.makeText(officer_login.this, "Welcome officer", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), Officer_logged.class);
                    i.putExtra("UserName", u_name);

                    startActivity(i);
                }else{
                    Toast.makeText(officer_login.this, "You are not registered", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //checks if user is present in the database or not
    boolean userCheck(String text){
        List<Users> officers = userHandler.readAllUsers();
        for(Users user : officers){
            Log.d("STATE3",user.getUsername());
            if (user.getUsername().equals(text)) {
                return true;
            }
        }
        return false;
    }

    boolean passCheck(String username, String password){
        List<Users> officers = userHandler.readAllUsers();
        for(Users user : officers){
            if (user.getUsername().equals(username)) {
                if(user.getPassword().equals(password)){
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    class ImportAllusers extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            JSONObject json = jParser.makeHttpRequest(url_get_users, "GET", params);
            // Check your log cat for JSON reponse
            Log.d("All Products: ", json.toString());

            return null;
        }
    }
}