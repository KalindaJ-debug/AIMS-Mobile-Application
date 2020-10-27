package com.example.javamobileapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DataEntrySuccessful extends AppCompatActivity {

    //Buttons
    Button btnHome;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry_successful);

        btnHome = (Button) findViewById(R.id.buttonHome);
        btnAdd = (Button) findViewById(R.id.buttonAdd);

        //button clicks
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataEntrySuccessful.this.openHomeActivity();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataEntrySuccessful.this.openAddActivity();
            }
        });

    } //end of on create method

    public void openHomeActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }//end of method

    public void openAddActivity(){
        Intent intent = new Intent(this, Farmer_Land.class);
        startActivity(intent);
    }//end of method

} //end of class