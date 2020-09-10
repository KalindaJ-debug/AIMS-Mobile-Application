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
//        Spinner landType = (Spinner) findViewById(R.id.spinnerLandType);
//        ArrayList<String> land_types_list = db.getLandTypeList();

        //implement land type spinner
//        ArrayAdapter<String> land_types_adapter = new ArrayAdapter<String>(
//                this,
//                R.layout.support_simple_spinner_dropdown_item,
//                land_types_list
//        );//end of adapter
//        land_types_adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
//        landType.setAdapter(land_types_adapter); //set adapter

        //Land Address Spinner
//        Spinner landAddress = (Spinner) findViewById(R.id.spinnerLandAddress);
//        ArrayList<String> land_address_list = db.getLandAddressList();

        //implement adapter
//        ArrayAdapter<String> land_address_adapter = new ArrayAdapter<String>(
//                this,
//                R.layout.support_simple_spinner_dropdown_item,
//                land_address_list
//        );//end of adapter
//        land_address_adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
//        landAddress.setAdapter(land_address_adapter);

        //Land extent value
        EditText land_extent = (EditText) findViewById(R.id.editTextLandExtent);
        String l = land_extent.getText().toString();
        Double landExtent = Double.parseDouble(l);

        //Button impl - Previous
        btnPrevious = (Button) findViewById(R.id.buttonBack);
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPreviousActivity();
            }
        });

        //Button impl - Submit


    } //end of onCreate method

    //Method declaration
    public void openPreviousActivity(){
        Intent intent = new Intent(this, DataEntryActivity.class);
        startActivity(intent);
    }//end of previous method

} //end of activity class