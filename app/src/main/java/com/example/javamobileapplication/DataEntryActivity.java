package com.example.javamobileapplication;
//Data Entry Activity Backend - 20205283
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class DataEntryActivity extends AppCompatActivity {

    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);

        //Declarations - Spinners
        Spinner cropCategory = (Spinner) findViewById(R.id.spinnerCropCategory);
        Spinner cropName = (Spinner) findViewById(R.id.spinnerCropName);
        Spinner cropVariety = (Spinner) findViewById(R.id.spinnerCropVariety);

        //spinner - crop category implementation


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