package com.example.javamobileapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    //Variable Declarations
//    Database
    public static final String DATABASE_NAME = "temp_aims.db";

//    Tables
    public static final String TABLE_DATA_ENTRY = "data_entry_table"; //data entry table

//    Columns
    public static final String COL_1 = "id";
    public static final String COL_2 = "land_id";
    public static final String COL_3 = "crop_variety_id";
    public static final String COL_4 = "cultivated_land_extent";
    public static final String COL_5 = "land_type";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);//create database
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    } //end of constructor

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //create land data entry table
        sqLiteDatabase.execSQL("create table " + TABLE_DATA_ENTRY + " (id INTEGER PRIMARY KEY AUTOINCREMENT, land_id INTEGER NOT NULL, crop_variety_id INTEGER NOT NULL, cultivated_land_extent DOUBLE NOT NULL, land_type TEXT)");//end of execSQL
    } //end of on create method

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
     sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_DATA_ENTRY);
     onCreate(sqLiteDatabase);
    } //end of on upgrade method

} //end of database helper class
