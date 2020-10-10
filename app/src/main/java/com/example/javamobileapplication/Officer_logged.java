package com.example.javamobileapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Officer_logged extends AppCompatActivity {

    Button btnDataEntry;
    Button btnViewData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_officer_logged);

        //declarations
        btnDataEntry = (Button) findViewById(R.id.button_officer_logged_data_entry);
        btnDataEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Officer_logged.this.openDataEntryActivity();
            }
        });

        //Navigation to View Data Activities
        btnViewData = (Button) findViewById(R.id.btnViewData);
        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Officer_logged.this, DataViewMain.class);
                startActivity(intent);
            }
        });
    }//end of on create method

    //button methods
    public void openDataEntryActivity(){
        Intent intent = new Intent(this, DataEntryActivity.class);
        startActivity(intent);
    }//end of method


}