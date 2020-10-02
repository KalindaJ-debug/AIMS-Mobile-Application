package com.example.javamobileapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DataViewCrop extends AppCompatActivity {

    ListView cropListView;

    //Array List
    ArrayList<String> cropList;
    ArrayAdapter cropAdapter;

    //Database Helper instance
    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_view_crop);

        cropListView = (ListView) findViewById(R.id.cropListView);

        //Initiating database helper instance
        helper = new DatabaseHelper(this);

        //list view
        cropList = new ArrayList<>();

        viewCropData();

        cropListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String text = cropListView.getItemAtPosition(i).toString();
                Toast.makeText(DataViewCrop.this,""+text, Toast.LENGTH_SHORT).show();
            }
        });

    }
    // View crop data method
    private void viewCropData(){

        Cursor cursor = helper.viewCropData();
        if(cursor.getCount() == 0){
            Toast.makeText(this,"No records found", Toast.LENGTH_SHORT).show();
        }
        else{
            while(cursor.moveToNext()){
                cropList.add(cursor.getString(0));
                cropList.add(cursor.getString(1));
            }

            cropAdapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, cropList);
            cropListView.setAdapter(cropAdapter);
        }
    }
}