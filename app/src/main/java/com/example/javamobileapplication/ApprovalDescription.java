package com.example.javamobileapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ApprovalDescription extends AppCompatActivity {

    TextView farmerFirst, farmerLast, district, region, submitDate, harvest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approval_description);

        farmerFirst = (TextView) findViewById(R.id.txtFarmerFirst);
        farmerLast = (TextView) findViewById(R.id.txtFarmerLast);
        district = (TextView) findViewById(R.id.txtDistrict);
        region = (TextView) findViewById(R.id.txtRegion);
        submitDate = (TextView) findViewById(R.id.txtSubmitDate);
        harvest = (TextView) findViewById(R.id.txtHarvest);

        Intent intent = getIntent();
//        String message = intent.getStringExtra(MainActivity.test);
        farmerFirst.setText(intent.getStringExtra("test"));
    }
}