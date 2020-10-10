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

        //Declarations - Spinners
        Spinner cropCategory = (Spinner) findViewById(R.id.spinnerCropCategory);
        Spinner cropName = (Spinner) findViewById(R.id.spinnerCropName);
        final Spinner cropVariety = (Spinner) findViewById(R.id.spinnerCropVariety);

        //spinner - crop category implementation

        ArrayList<String> crop_category_list = db.getCropCategory();
        ArrayAdapter<String> crop_category_adapter = new ArrayAdapter<String>(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                crop_category_list
        ); //end of adapter
        crop_category_adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        cropCategory.setAdapter(crop_category_adapter);
        //end crop category spinner

        String selectedCropCategory = cropCategory.getSelectedItem().toString();
        int selectedCropCategoryID = db.getCropCategoryID(selectedCropCategory);

        //spinner - crop list implementation
        ArrayList<String> crop_list = db.getCropList(selectedCropCategoryID);
        ArrayAdapter<String> crop_list_adapter = new ArrayAdapter<String>(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                crop_list
        );//end of adapter
        crop_list_adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        cropName.setAdapter(crop_list_adapter);

        //spinner - crop variety implementation
        String selectedCrop = cropName.getSelectedItem().toString();
        int selectedCropID = db.getCropID(selectedCrop);

        ArrayList<String> crop_variety_list = db.getCropVarietyList(selectedCropID);
        ArrayAdapter<String> crop_variety_adapter = new ArrayAdapter<String>(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                crop_variety_list
        );//end of adapter
        crop_variety_adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        cropVariety.setAdapter(crop_variety_adapter);

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