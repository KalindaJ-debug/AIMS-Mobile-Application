package com.example.javamobileapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import Classes.Users;
import Database.DBHandler;
import Database.UserHandler;

public class farmer_login extends AppCompatActivity {

    private Button login;

    UserHandler userHandler;
    //DBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_login);

        //dbHandler = new DBHandler(this);//handler for the Database
        userHandler = new UserHandler(this);//handler for users

        //notifications
        /*
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ecology)
                .setContentTitle("My notification")
                .setContentText("Much longer text that cannot fit one line...")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Much longer text that cannot fit one line..."))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        */

        login = findViewById(R.id.nic_submit);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText edit1 = findViewById(R.id.NIC);//get NIC from the farmer
                String nic = edit1.getText().toString();

                if(TextUtils.isEmpty(nic)){
                    Toast.makeText(farmer_login.this, "You must enter a NIC to proceed", Toast.LENGTH_SHORT).show();
                }else if(userCheck(nic)){
                    String username = getName(nic);
                    Toast.makeText(farmer_login.this, "Welcome to AIMS mobile", Toast.LENGTH_SHORT).show();
                    //Intent i = new Intent(getApplicationContext(), level01.class);
                    //i.putExtra("UserName", username);

                    //startActivity(i);
                }else{
                    Toast.makeText(farmer_login.this, "You are not registered", Toast.LENGTH_SHORT).show();
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

}