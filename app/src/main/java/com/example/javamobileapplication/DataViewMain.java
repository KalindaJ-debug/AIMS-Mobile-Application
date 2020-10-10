package com.example.javamobileapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class DataViewMain extends AppCompatActivity {

    Button cropButton;
    Button landButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_view_main);

        cropButton = (Button) findViewById(R.id.btnCropData);
        landButton = (Button) findViewById(R.id.btnLandData);

        //Intent for crop details activity
        cropButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(DataViewMain.this, DataViewCrop.class);
                startActivity(intent2);
            }
        });

        //Intent for land details activity
        landButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(DataViewMain.this, DataViewLand.class);
                startActivity(intent3);
            }
        });
    }
}