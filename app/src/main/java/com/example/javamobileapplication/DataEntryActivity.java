package com.example.javamobileapplication;
//Data Entry Activity Backend - 20205283
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class DataEntryActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);

        //Declarations - Spinners
        Spinner cropCategory = (Spinner) findViewById(R.id.spinnerCropCategory);
        Spinner cropName = (Spinner) findViewById(R.id.spinnerCropName);
        Spinner cropVariety = (Spinner) findViewById(R.id.spinnerCropVariety);
        Spinner landAddress = (Spinner) findViewById(R.id.spinnerLandAddress);

        //spinner - crop category implementation
        ArrayAdapter<String> cropCategoryAdapter = new ArrayAdapter<String>(
                DataEntryActivity.this,
                R.layout.support_simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.crop_categories)
        ); //end of array adapter
        cropCategoryAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        cropCategory.setAdapter(cropCategoryAdapter); //set adapter list

    } //end of onCreate function

} //end of DataEntryActivity class