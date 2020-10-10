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

public class DataViewLand extends AppCompatActivity {

    ListView landListView;

    //Array List
    ArrayList<String> landList;
    ArrayAdapter landAdapter;

    //Database Helper instance
    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_view_land);

        landListView = (ListView) findViewById(R.id.landListView);

        //Initiating database helper instance
        helper = new DatabaseHelper(this);

        //list view
        landList = new ArrayList<>();

        viewLandData();

        landListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String text = landListView.getItemAtPosition(i).toString();
                Toast.makeText(DataViewLand.this,""+text, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void viewLandData(){
        Cursor cursor = helper.viewLandData();
        if(cursor.getCount() == 0){
            Toast.makeText(this,"No records found", Toast.LENGTH_SHORT).show();
        }
        else{
            while(cursor.moveToNext()){
                landList.add(cursor.getString(0));
                landList.add(cursor.getString(1));
            }

            landAdapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, landList);
            landListView.setAdapter(landAdapter);
        }

    }
}