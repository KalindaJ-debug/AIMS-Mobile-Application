package com.example.javamobileapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LandDataEntryActivity extends AppCompatActivity {

    //Declarations
    //Variables
    Button btnPrevious; //previous button

    //Database
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_land_data_entry);

        //instantiate database - SQLite database
        db = new DatabaseHelper(this);
        //seed crop information to tables
        boolean success = true;
         success = db.seedCropCategory();

         if(success == false){
             Toast.makeText(getApplicationContext(), "Error: Failed to Seed Crop Category Data", Toast.LENGTH_LONG).show(); //display error message
         }

         success = db.seedCrop();
        if(success == false){
            Toast.makeText(getApplicationContext(), "Error: Failed to Seed Crop Name Data", Toast.LENGTH_LONG).show(); //display error message
        }

        success = db.seedCropVariety();
        if(success == false){
            Toast.makeText(getApplicationContext(), "Error: Failed to Seed Crop Variety Data", Toast.LENGTH_LONG).show(); //display error message
        }

        //Button impl - Previous
        btnPrevious = (Button) findViewById(R.id.buttonBack);
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPreviousActivity();
            }
        });

    } //end of onCreate method

    //Method declaration
    public void openPreviousActivity(){
        Intent intent = new Intent(this, DataEntryActivity.class);
        startActivity(intent);
    }//end of previous method

} //end of activity class