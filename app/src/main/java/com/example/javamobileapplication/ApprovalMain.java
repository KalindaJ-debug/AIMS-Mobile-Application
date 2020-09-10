package com.example.javamobileapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.View;

public class ApprovalMain extends AppCompatActivity {

    private static Context context;
    private SQLiteDatabase db;

    public static Context getAppContext() {
        return ApprovalMain.context;
    }

    ApprovalDBHelper dbHelper = new ApprovalDBHelper(getAppContext());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approval_main);

        //dbHelper.onCreate(db);
        //SQLiteDatabase db = dbHelper.getReadableDatabase();
//
//        // Create a new map of values, where column names are the keys
//        ContentValues values = new ContentValues();
//        values.put(ApprovalDBHelper.Approval.COLUMN_NAME_FARMER_FIRST_NAME, "Nelaka");
//        values.put(ApprovalDBHelper.Approval.COLUMN_NAME_FARMER_lAST_NAME, "Jayasinghe");
//        values.put(ApprovalDBHelper.Approval.COLUMN_NAME_DISTRICT, "Nawala");
//        values.put(ApprovalDBHelper.Approval.COLUMN_NAME_REGION, "Region 1");
//        values.put(ApprovalDBHelper.Approval.COLUMN_NAME_SUBMITTED_DATE, "01/08/2020");
//        values.put(ApprovalDBHelper.Approval.COLUMN_NAME_HARVEST_AMOUNT, "23.45");
//
//        // Insert the new row, returning the primary key value of the new row
//        long newRowId = db.insert(ApprovalDBHelper.Approval.TABLE_NAME, null, values);
    }

    public void proceedActivity1(View view) {
        Intent intent = new Intent(this, ApprovalDescription.class);
        intent.putExtra("farmerFirst", "Nelaka");
        intent.putExtra("farmerLast", "Jayasinghe");
        intent.putExtra("district", "Nawala");
        intent.putExtra("region", "Region 1");
        intent.putExtra("submittedDate", "06/09/2020");
        intent.putExtra("harvest", "45.897");
        startActivity(intent);
    }

    public void proceedActivity2(View view) {
        Intent intent = new Intent(this, ApprovalDescription.class);
        intent.putExtra("farmerFirst", "Kalinda");
        intent.putExtra("farmerLast", "Jayasinghe");
        intent.putExtra("district", "Gampaha");
        intent.putExtra("region", "Region 2");
        intent.putExtra("submittedDate", "05/12/2020");
        intent.putExtra("harvest", "78.123");
        startActivity(intent);
    }

    public void nextActivity(View view) {
        Intent intent = new Intent(this, ApprovalDescription.class);
        intent.putExtra("farmerFirst", "Nelaka");
        intent.putExtra("farmerLast", "Jayasinghe");
        intent.putExtra("district", "Nawala");
        intent.putExtra("region", "Region 1");
        intent.putExtra("submittedDate", "06/09/2020");
        intent.putExtra("harvest", "45.897");
        startActivity(intent);
    }

}