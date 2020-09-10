package com.example.javamobileapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class LandDataEntryActivity extends AppCompatActivity {

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_land_data_entry);

        //instantiate database - SQLite database
        db = new DatabaseHelper(this);

    } //end of onCreate method

} //end of activity class