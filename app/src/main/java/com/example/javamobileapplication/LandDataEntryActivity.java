package com.example.javamobileapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class LandDataEntryActivity extends AppCompatActivity {

    //Declarations
    //Variables
    //Buttons
    Button btnPrevious; //previous button
    Button btnSubmit; //submit button

    //Database
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_land_data_entry);

        db = new DatabaseHelper(this); //instantiate database

        //seed data

        //Spinner- land measurements
        Spinner landMeasurements = (Spinner) findViewById(R.id.spinnerLand);

        //spinner - land measurements implementation
        ArrayAdapter<String> landMeasurementsArray = new ArrayAdapter<String>(
                LandDataEntryActivity.this,
                R.layout.support_simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.land_measurements)
        ); //end of array adapter
        landMeasurementsArray.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        landMeasurements.setAdapter(landMeasurementsArray);

        //Spinner - Land Types
        Spinner landType = (Spinner) findViewById(R.id.spinnerLandType);
        ArrayList<String> land_types_list = db.getLandTypeList();

        //implement land type spinner
        ArrayAdapter<String> land_types_adapter = new ArrayAdapter<String>(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                land_types_list
        );//end of adapter
        land_types_adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        landType.setAdapter(land_types_adapter); //set adapter

        //Land Address Spinner
        final Spinner landAddress = (Spinner) findViewById(R.id.spinnerLandAddress);
        ArrayList<String> land_address_list = db.getLandAddressList();

        //implement adapter
        ArrayAdapter<String> land_address_adapter = new ArrayAdapter<String>(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                land_address_list
        );//end of adapter
        land_address_adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        landAddress.setAdapter(land_address_adapter);

        //Land extent value
        final EditText land_extent = (EditText) findViewById(R.id.editTextLandExtent);

        final String selected_measurement = landMeasurements.getSelectedItem().toString();

        String selected_land_type = landType.getSelectedItem().toString();

        //set data - dummy data

        String selected_variety = getIntent().getStringExtra("crop_variety");
        final int variety_id = db.GetVarietyId(selected_variety);
        final int lt =  db.GetLandTypeId(selected_land_type);

        //Button impl - Previous
        btnPrevious = (Button) findViewById(R.id.buttonBack);
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPreviousActivity();
            }
        });

        //Button impl - Submit

        btnSubmit = (Button) findViewById(R.id.buttonSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int land_id = landAddress.getSelectedItemPosition();
                String str_landExtent = land_extent.getText().toString();
                final double finalLe = parseDouble(str_landExtent);
                boolean result;
                double converted_le;

                //check for not selected
                if(land_id < 0){
                    Toast.makeText(getApplicationContext(), "Please select land address", Toast.LENGTH_LONG).show(); //show error message
                }
                else if(variety_id == 0){
                    Toast.makeText(getApplicationContext(), "Please enter crop variety details", Toast.LENGTH_LONG).show(); //show error message
                }
                else if(finalLe == 0){
                    Toast.makeText(getApplicationContext(), "Please enter land extent ", Toast.LENGTH_LONG).show(); //show error message
                }
                else if(lt == 0){
                    Toast.makeText(getApplicationContext(), "Please enter land type", Toast.LENGTH_LONG).show(); //show error message
                }
                else{
                    //selected converter
                    if(selected_measurement == "Rood") {
                        converted_le = (float) (finalLe * 0.1012);
                    }
                    else if(selected_measurement == "Acre"){
                        converted_le = (float) (finalLe * 0.404686);
                    }
                    else if(selected_measurement == "Perch"){
                        converted_le = (float) (finalLe * 0.00252929);
                    }
                    else{
                        converted_le = finalLe;
                    }

                    //all data available for data entry
                    result = db.SetDataEntry(land_id + 1, variety_id, converted_le, lt);

                    if(result == false){
                        Toast.makeText(getApplicationContext(), "Failed Submit Land Data. Please Try Again", Toast.LENGTH_LONG).show(); //show error message
                    }//end if
                    else {
                        Toast.makeText(getApplicationContext(), "Successfully Submitted Cultivated Data Entry!", Toast.LENGTH_LONG).show(); //show message
                        openSuccessfulActivity(); //go to success activity
                    }

                }//end of nested if

            }
        });

    } //end of onCreate method

    //Method declaration
    public void openSuccessfulActivity(){
        Intent intent = new Intent(this, DataEntrySuccessful.class);
        startActivity(intent);
    }//end of method

    public void openPreviousActivity(){
        Intent intent = new Intent(this, DataEntryActivity.class);
        startActivity(intent);
    }//end of previous method

    public double parseDouble(String string){
        double result;
        if(string == null || string.isEmpty()){
            result = (float) 0.0;
            return result;
        }
        else{
            result = Double.parseDouble(string);
            return result;
        }
    }//end of method

} //end of activity class