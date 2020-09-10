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

    public static Context getAppContext() {
        return ApprovalMain.context;
    }

    ApprovalDBHelper dbHelper = new ApprovalDBHelper(getAppContext());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//
//        // Create a new map of values, where column names are the keys
//        ContentValues values = new ContentValues();
//        values.put(ApprovalDBHelper.Approval.COLUMN_NAME_FARMER_FIRST_NAME, "Nelaka");
//        values.put(ApprovalDBHelper.Approval.COLUMN_NAME_FARMER_lAST_NAME, "Jayasinghe");
//        values.put(ApprovalDBHelper.Approval.COLUMN_NAME_DISTRICT, "Nawala");
//        values.put(ApprovalDBHelper.Approval.COLUMN_NAME_REGION, "Region 1");
//        values.put(ApprovalDBHelper.Approval.COLUMN_NAME_SUBMITTED_DATE, "01/08/2020");
//        values.put(ApprovalDBHelper.Approval.COLUMN_NAME_HARVEST_AMOUNT, "23.45");
//        values.put(ApprovalDBHelper.Approval.COLUMN_NAME_LAND, "68");
//
//
//        // Insert the new row, returning the primary key value of the new row
//        long newRowId = db.insert(ApprovalDBHelper.Approval.TABLE_NAME, null, values);

        setContentView(R.layout.activity_approval_main);
    }

    public void nextActivity(View view) {
        Intent intent = new Intent(this, ApprovalDescription.class);
        startActivity(intent);
    }

}