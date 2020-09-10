package com.example.javamobileapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import Classes.Users;
import Database.UserHandler;

public class officer_login extends AppCompatActivity {

    private Button login;
    UserHandler userHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_officer_login);

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
}