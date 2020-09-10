package com.example.javamobileapplication;
//Data Entry Activity Backend - 20205283
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class DataEntryActivity extends AppCompatActivity {

    private Button btnNext;

    //Database
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);

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

        success = db.seedLandType();
        if(success == false){
            Toast.makeText(getApplicationContext(), "Error: Failed to Seed Land Type Data", Toast.LENGTH_LONG).show(); //display error message
        }
        success = db.seedLandAddress();
        if(success == false){
            Toast.makeText(getApplicationContext(), "Error: Failed to Seed Land Address Data", Toast.LENGTH_LONG).show(); //display error message
        }

        //Declarations - Spinners
        Spinner cropCategory = (Spinner) findViewById(R.id.spinnerCropCategory);
        Spinner cropName = (Spinner) findViewById(R.id.spinnerCropName);
        Spinner cropVariety = (Spinner) findViewById(R.id.spinnerCropVariety);

        //spinner - crop category implementation

        ArrayList<String> crop_category_list = db.getCropCategory();
        ArrayAdapter<String> crop_category_adapter = new ArrayAdapter<String>(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                crop_category_list
        ); //end of adapter
        crop_category_adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        cropCategory.setAdapter(crop_category_adapter);

        //Button - Next implementation
        btnNext = (Button) findViewById(R.id.buttonNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLandDataEntryActivity();
            }
        });
    } //end of onCreate function

    //methods
    public void openLandDataEntryActivity(){
        Intent intent = new Intent(this, LandDataEntryActivity.class);
        startActivity(intent);
    }//end of open land activity

} //end of DataEntryActivity class