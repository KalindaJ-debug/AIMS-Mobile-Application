package com.example.javamobileapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    //Variable Declarations
//    Database
    public static final String DATABASE_NAME = "temp_aims.db";

//    Tables
    public static final String TABLE_DATA_ENTRY = "data_entry_table"; //data entry table
    public static final String TABLE_CROP_CATEGORY = "crop_category_table"; //crop category
    public static final String TABLE_CROP_NAME = "crop_table"; //crop name
    public static final String TABLE_CROP_VARIETY = "crop_variety_table"; //crop variety

//    Columns
    //Table - Data Entry
    public static final String COL_1 = "id";
    public static final String COL_2 = "land_id";
    public static final String COL_3 = "crop_variety_id";
    public static final String COL_4 = "cultivated_land_extent";
    public static final String COL_5 = "land_type";

    //Table - Crop Category
    public static final String CC_COL_1 = "id";
    public static final String CC_COL_2 = "name";

    //Table - Crop Name
    public static final String C_COL_1 = "id";
    public static final String C_COL_2 = "name";
    public static final String C_COL_3 = "crop_category_id";

    //Table - Crop Variety
    public static final String CV_COL_1 = "id";
    public static final String CV_COL_2 = "name";
    public static final String CV_COL_3 = "crop_id";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);//create database

    } //end of constructor

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //create land data entry table
        sqLiteDatabase.execSQL("create table " + TABLE_DATA_ENTRY + " (id INTEGER PRIMARY KEY AUTOINCREMENT, land_id INTEGER NOT NULL, crop_variety_id INTEGER NOT NULL, cultivated_land_extent DOUBLE NOT NULL, land_type TEXT)");//end of execSQL
        //create crop category table
        sqLiteDatabase.execSQL("create table " + TABLE_CROP_CATEGORY + " (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL)");
        //create crop table
        sqLiteDatabase.execSQL("create table " + TABLE_CROP_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, crop_category_id INTEGER NOT NULL, FOREIGN KEY (crop_category_id) REFERENCES " + TABLE_CROP_CATEGORY + "(id))");
        //create crop variety table
        sqLiteDatabase.execSQL("create table " + TABLE_CROP_VARIETY + " (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, crop_id INTEGER NOT NULL, FOREIGN KEY (crop_id) REFERENCES " + TABLE_CROP_NAME + "(id))");

    } //end of on create method

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
     sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_DATA_ENTRY);
     sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CROP_CATEGORY);
     sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CROP_NAME);
     sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CROP_VARIETY);

     onCreate(sqLiteDatabase);

    } //end of on upgrade method

    //data seeder methods
    //crop category table - data seeder method
    public boolean seedCropCategory(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase(); //write to database

        ArrayList<String> crop_categories = new ArrayList<String>();
        crop_categories.add("Vegetables");
        crop_categories.add("Fruits");
        crop_categories.add("Other Field Crops");
        crop_categories.add("Leafy Vegetables");
        crop_categories.add("Roots and Tubers");

        boolean endResult = true;

        for (String item: crop_categories ) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(CC_COL_2, item);
            long result = sqLiteDatabase.insert(TABLE_CROP_CATEGORY, null, contentValues);
            if(result == -1){
                //failed to insert value
                endResult = false;
            }
        } //end of foreach

        return endResult;

    }//end of insert crop category

    //data seeder for crop names
    public boolean seedCrop(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase(); //write to database

        //array - vegetables
        ArrayList<String> crops_vegetables = new ArrayList<String>();
        crops_vegetables.add("Brinjol");
        crops_vegetables.add("Tomato");
        crops_vegetables.add("Snake Gourd");
        crops_vegetables.add("Capsicum");

        //array - fruits
        ArrayList<String> crops_fruits = new ArrayList<String>();
        crops_fruits.add("Apple");
        crops_fruits.add("Orange");
        crops_fruits.add("Mango");
        crops_fruits.add("Pineapple");

        //array - ofc
        ArrayList<String> crops_ofc = new ArrayList<String>();
        crops_ofc.add("Cow Pea");
        crops_ofc.add("Green Gram");
        crops_ofc.add("Red Gram");

        //array - roots and tubers
        ArrayList<String> crops_roots = new ArrayList<String>();
        crops_roots.add("Carrot");
        crops_roots.add("Potato");
        crops_roots.add("Sweet Potato");
        crops_roots.add("Turmeric");

        //array - leafy vegetables
        ArrayList<String> crops_leafy = new ArrayList<String>();
        crops_leafy.add("Kankun");
        crops_leafy.add("Mint");
        crops_leafy.add("Spinach");
        crops_leafy.add("Lettuce");

        boolean result = true;

        //foreach loop - vegetables
        for (String item: crops_vegetables) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(C_COL_2, item);
            contentValues.put(C_COL_3, 1);
            long res = sqLiteDatabase.insert(TABLE_CROP_NAME,null, contentValues);
            if(res == -1){
                result = false; //seeding failed
            } //end of if

        } //end of loop

        //foreach loop - fruits
        for (String item: crops_fruits) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(C_COL_2, item);
            contentValues.put(C_COL_3, 2);
            long res = sqLiteDatabase.insert(TABLE_CROP_NAME,null, contentValues);
            if(res == -1){
                result = false; //seeding failed
            } //end of if

        } //end of loop

        //foreach loop - ofc
        for (String item: crops_ofc) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(C_COL_2, item);
            contentValues.put(C_COL_3, 3);
            long res = sqLiteDatabase.insert(TABLE_CROP_NAME,null, contentValues);
            if(res == -1){
                result = false; //seeding failed
            } //end of if

        } //end of loop

        //foreach loop - leafy
        for (String item: crops_leafy) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(C_COL_2, item);
            contentValues.put(C_COL_3, 4);
            long res = sqLiteDatabase.insert(TABLE_CROP_NAME,null, contentValues);
            if(res == -1){
                result = false; //seeding failed
            } //end of if

        } //end of loop

        //foreach loop - roots
        for (String item: crops_roots) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(C_COL_2, item);
            contentValues.put(C_COL_3, 5);
            long res = sqLiteDatabase.insert(TABLE_CROP_NAME,null, contentValues);
            if(res == -1){
                result = false; //seeding failed
            } //end of if

        } //end of loop

        return result; //return boolean value

    }//end of insert crop

} //end of database helper class
