package com.example.javamobileapplication;
//Data Entry Activity Backend - 20205283
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class DataEntryActivity extends AppCompatActivity {

    private Button btnNext;
    ArrayAdapter<String> crop_list_adapter;
    ArrayAdapter<String> crop_variety_adapter;

    //Database
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);

        //instantiate database - SQLite database
        db = new DatabaseHelper(this);

        //Declarations - Spinners
        final Spinner cropCategory = (Spinner) findViewById(R.id.spinnerCropCategory);
        final Spinner cropName = (Spinner) findViewById(R.id.spinnerCropName);
        final Spinner cropVariety = (Spinner) findViewById(R.id.spinnerCropVariety);

        //spinner - crop category implementation

        ArrayList<String> crop_category_list = db.getCropCategory();
        final ArrayAdapter<String> crop_category_adapter = new ArrayAdapter<String>(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                crop_category_list
        ); //end of adapter
        crop_category_adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        cropCategory.setAdapter(crop_category_adapter);
        //end crop category spinner

        //listen spinner - crop category

        cropCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                ArrayList<String> temp_list = db.getCropList(db.getCropCategoryID(cropCategory.getSelectedItem().toString()));
                crop_list_adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, temp_list);
                cropName.setAdapter(crop_list_adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

                String selectedCropCategory = cropCategory.getSelectedItem().toString();
                int selectedCropCategoryID = db.getCropCategoryID(selectedCropCategory);

                ArrayList<String> crop_list = db.getCropList(selectedCropCategoryID);
                crop_list_adapter = new ArrayAdapter<String>(
                        getApplicationContext(),
                        R.layout.support_simple_spinner_dropdown_item,
                        crop_list
                );//end of adapter
                crop_list_adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                cropName.setAdapter(crop_list_adapter);

            }
        });

        //end of listener spinner

        //spinner - crop variety implementation

        //listener for crop name
        cropName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ArrayList<String> temp_variety_list = db.getCropVarietyList(db.getCropID(cropName.getSelectedItem().toString()));
                crop_variety_adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, temp_variety_list);
                cropVariety.setAdapter(crop_variety_adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //default selection
                String selectedCrop = "Brinjol";
                int selectedCropID = db.getCropID(selectedCrop);

                ArrayList<String> crop_variety_list = db.getCropVarietyList(selectedCropID);
                crop_variety_adapter = new ArrayAdapter<String>(
                        getApplicationContext(),
                        R.layout.support_simple_spinner_dropdown_item,
                        crop_variety_list
                );//end of adapter
                crop_variety_adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                cropVariety.setAdapter(crop_variety_adapter);

            }
        });

        //end of listener

        //Button - Next implementation
        btnNext = (Button) findViewById(R.id.buttonNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String variety = cropVariety.getSelectedItem().toString();
                openLandDataEntryActivity(variety);
            }
        });

    } //end of onCreate function

    //methods
    public void openLandDataEntryActivity(String string){
        Intent intent = new Intent(this, LandDataEntryActivity.class);
        intent.putExtra("crop_variety", string);
        startActivity(intent);
    }//end of open land activity

} //end of DataEntryActivity class